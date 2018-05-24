package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class TextPage {
	private ArrayList<Line> lineList;
	private String filePath;
	
	public TextPage() {
		lineList = new ArrayList<Line>();
	}
	public TextPage(File file) {
		filePath = file.getAbsolutePath() + file.getName();
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 lineList.add(new Line(newLine));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTextField(String line, int lineN) {
		lineList.get(lineN).setLineText(line);
	}
	public String getTextField() {
		Iterator<Line> it = lineList.iterator();
		String ret = new String();
		while(it.hasNext()) {
			ret += it.next().getLineText() + "\n";
		}
		return ret;
	}
	public ArrayList<Line> getTextLines() {
		ArrayList<Line> ret =  lineList;
		return ret;
	}
	
}
