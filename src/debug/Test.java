package debug;


import receive.HTMLFetcher;

public class Test {
	public static void main(String[] args) {
		HTMLFetcher fetcher = new HTMLFetcher("url");
		fetcher.loadHTML();
	}
}
