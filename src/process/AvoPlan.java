package process;
import java.util.ArrayList;
import java.util.Arrays;

public class AvoPlan {
	private String title = "";
	private ArrayList<String[]> data = null;
	private ArrayList<String[]> infos = null;
	
	public AvoPlan(ArrayList<String[]> data, ArrayList<String[]> infos, String title) {
		this.data = data;
		this.title = title;
		this.infos = infos;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String[]> getData() {
		return data;
	}

	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
	public void log() {
		System.out.println(title);
		infos.forEach(x -> System.out.println(Arrays.toString(x)));
		data.forEach(x -> System.out.println(Arrays.toString(x)));
		System.out.println("---------\n\n\n---------");
	}

	public ArrayList<String[]> getInfos() {
		return infos;
	}

	public void setInfos(ArrayList<String[]> infos) {
		this.infos = infos;
	}
	
}
