package debug;


import receive.HTMLFetcher;

public class Test {
	public static void main(String[] args) {
		HTMLFetcher.initializeFetcher("url", "username", "password");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HTMLFetcher.staticSchedule.log();
	}
}
