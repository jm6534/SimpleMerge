package model;

import javafx.scene.paint.Color;
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
		filePath = new String("");
	}
	public TextPage(File file) {
		lineList = new ArrayList<Line>();
		filePath = new String(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 lineList.add(new Line(new String(newLine)));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setFilePath(File file) { //Set filePath and new lineContents
		lineList = new ArrayList<Line>();
		filePath = new String(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 lineList.add(new Line(new String(newLine)));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getFilePath() {
		return new String(filePath);
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
	public void addFakeLines(int from, int to) {
		for ( int i = from; i <= to; i++) {
			lineList.add(i, new Line(false));
		}
	}
	
	//To control Line class variables in TextPage Class level with line number
	public boolean isRealLine(int lineN) {
		return lineList.get(lineN).isRealLine();
	}
	public void setRealLine(int lineN, boolean bool) {
		lineList.get(lineN).setIsRealLine(bool);
	}
	public void toogleIsRealLine(int lineN) {
		lineList.get(lineN).toggleIsRealLine();
	}
	public Color getLineColor(int lineN) {
		return lineList.get(lineN).getLineColor();
	}
	public void setLineColor(int lineN, Color color) {
		lineList.get(lineN).setLineColor(color);
	}
	public String getLineText(int lineN) {
		return lineList.get(lineN).getLineText();
	}
	public void setLineText(int lineN, String str) {
		lineList.get(lineN).setLineText(str);
	}
}
