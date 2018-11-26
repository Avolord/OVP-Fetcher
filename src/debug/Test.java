package debug;


import receive.HTMLFetcher;

public class Test {
	public static void main(String[] args) {
		HTMLFetcher.initializeFetcher("http://rudolph-brandes-gymnasium.de/ovp/heute/subst_001.htm", "schueler", "Did1VP-PW");
		HTMLFetcher.staticSchedule.log();
	}
}
