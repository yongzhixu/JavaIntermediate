package ExceptionsProgramming;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import Unit1.TextIO;

public class StreamIO {

	public static void main(String[] argsStrings) {
		FileCheck fileCheck = new FileCheck();
		String stringUrl = "http://www.matchbooklitmag.com/ryan.html";
		InputStream inputStream;
		URL url;
		OutputStream outputStream;
		try {
			// declare URL and open the stream
			url = new URL(stringUrl);
			inputStream = url.openStream();

			// decide file for output
			TextIO.putln("name a file to store the web content: ");
			String fileName = TextIO.getln();
			String filePath = System.getProperty("user.dir") + "\\garage\\" + fileName + ".html";
			stringUrl = filePath;
			fileCheck.isFileExists(new File(filePath));

			// declare output stream and copy the web content
			outputStream = new FileOutputStream(filePath);
			copyStream(inputStream, outputStream);

		} catch (MalformedURLException urlException) {
			// TODO: handle exception
			TextIO.put("MalformedURLException " + urlException.getMessage());
		} catch (FileNotFoundException fileNotFoundException) {
			// TODO: handle exception
			TextIO.put("fileNotFoundException " + fileNotFoundException.getMessage());
		} catch (IOException ioException) {
			// TODO: handle exception
			TextIO.put("IOException " + ioException.getMessage());
		} finally {
			TextIO.putln("web content copy finished!");
			TextIO.putln("the file path is: " + stringUrl);
		}

	}

	private static void copyStream(InputStream in, OutputStream out) throws IOException {
		int oneByte = in.read();
		while (oneByte >= 0) { // negative value indicates end-of-stream
			out.write(oneByte);
			oneByte = in.read();
		}
		in.close();
		out.close();
	}

}
