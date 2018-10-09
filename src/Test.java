
public class Test {
	public static void main(String[] args) {
		AvoFetch fetch = new AvoFetch("url");
		fetch.loadOVP();
		fetch = new AvoFetch("url");
		fetch.loadOVP();
	}
}
