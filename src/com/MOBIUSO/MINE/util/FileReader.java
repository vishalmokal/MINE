package com.MOBIUSO.MINE.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {
	
	public String readFile(String filepath) throws IOException {
		File f = new File(filepath);
		FileInputStream in = new FileInputStream(f);
		int size = in.available();
		byte c[] = new byte[size];
		for (int i = 0; i < size; i++) {
			c[i] = (byte) in.read();
		}
		String filedata = new String(c, "utf-8");
		return filedata;
	}

	public String readResourceFile(InputStream is) {

		InputStream in = is;
		InputStreamReader isr = new InputStreamReader(in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		String read = null;
		try {
			read = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (read != null) {
			sb.append(read);
			try {
				read = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			}

		}

		return sb.toString();
	}
}
