import java.util.ArrayList;
import java.util.Arrays;

public class AvoPlan {
	private String title = "";
	private ArrayList<String[]> data = null;
	private ArrayList<String[]> head = null;
	
	public AvoPlan(ArrayList<String[]> data, ArrayList<String[]> head, String title) {
		this.data = data;
		this.head = head;
		this.title = title;
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
		data.forEach(x -> System.out.println(Arrays.toString(x)));
		System.out.println("---------\n\n\n---------");
	}
	
}
