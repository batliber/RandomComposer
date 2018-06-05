package io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class FileOutputManager {

	private static FileOutputManager instance = null;
	
	private String buffer = "";
	
	private FileOutputManager() {
		
	}
	
	public static FileOutputManager getInstance() {
		if (instance == null) {
			instance = new FileOutputManager();
		}
		return instance;
	}
	
	public void append(String string) {
		buffer += string;
	}
	
	public void saveBufferToFile() {
		BufferedWriter bufferedWriter = null;
		
		Date now = GregorianCalendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			bufferedWriter = 
				new BufferedWriter(
					new OutputStreamWriter(
						new FileOutputStream("C:\\Users\\lbatalla\\Documents\\MuseScore2\\Partituras\\" + format.format(now) + ".xml"),
						StandardCharsets.UTF_8
					)
				);
			
			bufferedWriter.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void saveExternalBufferToFile(String buffer) {
		BufferedWriter bufferedWriter = null;
		
		Date now = GregorianCalendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			bufferedWriter = 
				new BufferedWriter(
					new OutputStreamWriter(
						new FileOutputStream(
							"C:\\Users\\lbatalla\\Documents\\MuseScore2\\Partituras\\" + format.format(now) + ".xml"
						),
						StandardCharsets.UTF_8
					)
				);
			
			bufferedWriter.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}