package receive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import process.SubstitutionSchedule;
import process.DataExtractor;
import process.SimpleBase64Encoder;

public class HTMLFetcher {
	private static boolean fetchOnInitialization = true;
	public static int phase = 0;
	public static boolean initialized = false;
	public static HTMLFetcher staticFetcher = null;
	public static SubstitutionSchedule staticSchedule = null;

	public static final void initializeFetcher(final String url, final String username, final String password) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					staticFetcher = new HTMLFetcher(url, username, password);
					phase++;
					if(staticFetcher != null)
						System.out.println("static fetcher initialized!");
					else
						System.out.println("failed to initialize static fetcher");
					staticSchedule = staticFetcher.getSubstitutionSchedule();
					phase++;
					if(staticSchedule != null)
						System.out.println("static schedule initialized!");
					else
						System.out.println("failed to initialize static schedule");
					System.out.println("Initialized!");
					initialized = true;
					phase++;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR in Phase: "+phase);
				}
			}
			
		});

		thread.start();
	}

	private URL url = null;
	private InputStream istream = null;
	private BufferedReader buffer = null;
	private String wrapper = "center";
	private String username = "";
	private String password = "";
	private String Token = getToken();
	private boolean requiresAuthentication = true;

	public HTMLFetcher(String url) {
		requiresAuthentication = false;
		if (fetchOnInitialization)
			fetch(url);
		else
			try {
				this.url = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}

	public HTMLFetcher(String url, String username, String password) {
		setAuthentication(username, password);
		if (fetchOnInitialization)
			fetch(url);
		else
			try {
				this.url = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}
	
	public static ArrayList<String[]> getData(String Identifier) {
        if(!initialized)
            return null;

        return staticSchedule.getData(Identifier);
    }

	public final void fetch() {
		try {
			HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();

			if (requiresAuthentication) {
				connection.setRequestProperty("Authorization", "Basic " + Token);
			}

			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// everything ok
				istream = connection.getInputStream();
				buffer = new BufferedReader(new InputStreamReader(istream));
				// process stream
			} else {
				System.out.println("RESPONSE CODE: " + connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final void fetch(String url) {
		try {
			this.url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();

			if (requiresAuthentication) {
				connection.setRequestProperty("Authorization", "Basic " + Token);
			}

			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// everything ok
				istream = connection.getInputStream();
				buffer = new BufferedReader(new InputStreamReader(istream));
				// process stream
			} else {
				System.out.println("RESPONSE CODE: " + connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAuthentication(String username, String password) {
		this.username = username;
		this.password = password;
		Token = getToken();
	}

	public void setUsername(String username) {
		this.username = username;
		Token = getToken();
	}

	public void setPassword(String password) {
		this.password = password;
		Token = getToken();
	}

	public void enableAuthentication() {
		requiresAuthentication = true;
	}

	public void disableAuthentication() {
		requiresAuthentication = false;
	}

	public void loadHTML() {
		DataExtractor sort = new DataExtractor();
		SubstitutionSchedule plan = sort.extractInformation(buffer, wrapper);
		plan.log();

		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fetch();
	}

	public void loadHTML(String Class) {
		DataExtractor sort = new DataExtractor();
		SubstitutionSchedule plan = sort.extractInformation(buffer, wrapper);
		plan.log(Class);

		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fetch();
	}

	public SubstitutionSchedule getSubstitutionSchedule() {
		DataExtractor sort = new DataExtractor();
		SubstitutionSchedule schedule = sort.extractInformation(buffer, wrapper);
		fetch();
		return schedule;
	}

	private final String getToken() {
		String token = SimpleBase64Encoder.encode(username + ":" + password);
		token = token.replaceAll("\n", "");
		token = token.replaceAll("\t", "");
		token = token.replaceAll(" ", "");
		return token;
	}
}
