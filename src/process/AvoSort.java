package process;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvoSort {
	private static Pattern div = Pattern.compile("<(div)(\\b[^>]*>)(.*?)</\\1>");
	private static Pattern tr = Pattern.compile("<(tr)(\\b[^>]*>)(.*?)</\\1>");
	private static Pattern td = Pattern.compile("<(td)(\\b[^>]*>)(.*?)</\\1>");
	private static Pattern th = Pattern.compile("<(th)(\\b[^>]*>)(.*?)</\\1>");

	private Matcher matcher = null;
	private Boolean isImportant = false;
	private String title = null;

	public AvoPlan extractInformation(BufferedReader br) {
		ArrayList<String[]> ovp = new ArrayList<String[]>();
		ArrayList<String[]> infos = new ArrayList<String[]>();
		String line = "";

		try {
			while ((line = br.readLine()) != null) {
				if (line.contains("<center>")) {
					isImportant = true;
					continue;
				} else if (line.contains("</center>")) {
					isImportant = false;
				}
				if (isImportant) {
					if (title == null) {
						matcher = div.matcher(line);
						title = matcher.replaceAll("$3");
					} else if (line.contains("<th")) {

					} else if (line.contains("<tr")) {
						matcher = tr.matcher(line);
						line = matcher.replaceAll("$3").replaceAll("&nbsp;", " ");
						matcher = td.matcher(line);
						String[] data = matcher.replaceAll("$3#").split("#");
						if(data.length >= 5) {
							ovp.add(data);
						} else {
							infos.add(data);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AvoPlan(ovp, infos, title);
	}

}
