package debug;
import receive.AvoFetch;
import view.AvoTable;

public class Test {
	public static void main(String[] args) {
		AvoFetch fetch = new AvoFetch("http://rudolph-brandes-gymnasium.de/ovp/heute/subst_001.htm");
		fetch.loadOVP();
		fetch = new AvoFetch("http://rudolph-brandes-gymnasium.de/ovp/heute/subst_002.htm");
		fetch.loadOVP();
		AvoTable.start(new String[] {});
	}
}
