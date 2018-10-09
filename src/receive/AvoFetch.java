package receive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import process.AvoPlan;
import process.AvoSort;
import view.AvoTable;

public class AvoFetch {
	private URL url = null;
	private InputStream istream = null;
	private BufferedReader buffer = null;
	
	public AvoFetch(String url) {
		try {
			this.url = new URL(url);
			istream = this.url.openStream();
			buffer = new BufferedReader(new InputStreamReader(istream));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadOVP() {
		AvoSort sort = new AvoSort();
		AvoPlan plan = sort.extractInformation(buffer);
		AvoTable.initData(plan.getData());
		try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
