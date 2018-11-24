package debug;

import receive.HTMLFetcher;

public class Test {
	public static void main(String[] args) {
		HTMLFetcher fetch = new HTMLFetcher("url");
		fetch.loadHTML();
	}
}
