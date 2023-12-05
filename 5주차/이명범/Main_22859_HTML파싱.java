package boj;

import java.io.IOException;

public class Main_22859_HTML파싱 {

	static String[] attributes;

	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();

		do {
			char ch = (char) System.in.read();

			if (ch == '<')
				attributes = getAttributes();

			if (attributes[0].equals("div")) {
				result.append("title : ").append(extractTitle(attributes)).append("\n");
				do {
					ch = (char) System.in.read();

					if (ch == '<')
						attributes = getAttributes();

					if (attributes[0].equals("p"))
						result.append(extractParagraph()).append("\n");

				} while (!attributes[0].equals("/div"));
			}
		} while (!attributes[0].equals("/main"));

		System.out.print(result);
	}

	private static String[] getAttributes() throws IOException {
		StringBuilder tag = new StringBuilder();
		char ch;
		while ((ch = (char) System.in.read()) != '>')
			tag.append(ch);

		return tag.toString().split(" ", 2);
	}

	private static String extractTitle(String[] attributes) {
		return attributes[1].split("=")[1].replaceAll("\"", "").trim();
	}

	private static String extractParagraph() throws IOException {
		String[] attributes;
		char ch;
		StringBuilder paragraph = new StringBuilder();
		while (true) {
			ch = (char) System.in.read();

			if (ch == '<') {
				attributes = getAttributes();

				if (attributes[0].equals("/p")) {
					return paragraph.toString().trim();
				} else {
					continue;
				}
			}

			if (ch == ' ') {
				String changed = paragraph.toString().trim();
				paragraph.setLength(0);
				paragraph.append(changed);
			}
			paragraph.append(ch);
		}
	}
}
