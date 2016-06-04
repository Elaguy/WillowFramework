package schultz.personal.willowFramework;

import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Log {
	
	private ArrayList<String> eventList;
	
	public Log() {
	
	}
	
	public void initLog() {
		eventList = new ArrayList<String>();
	}
	
	public void addEvent(String event) {
		eventList.add(event);
	}
	
	public void exportEventList() {
		String dir = "C:/Users/Elaguy/Documents/WillowSUD Event Logs/events.log";
		File exportFile = new File(dir);
		if(!(exportFile.exists()))
		exportFile.getParentFile().mkdirs();
		
		try {
			FileWriter fileWriter = new FileWriter(exportFile, true);
			printEventList(fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void printEventList(FileWriter fileWriter) throws IOException {
		Date dateObj = new Date();
		DateFormat dateFormatObj = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		fileWriter.write(dateFormatObj.format(dateObj) + "\r\n");
		for(int i = 0; i < eventList.size(); i++) {
			fileWriter.write((i+1) + ". " + eventList.get(i) + "\r\n"); // *Will it work on OSX and Linux?*
		}
		
		try {
			fileWriter.write("\r\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<String> getEventList() {
		return eventList;
	}
}
