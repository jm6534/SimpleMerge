package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class TextPage {
    private ListProperty<Line> listProperty = new SimpleListProperty<>();
    private StringProperty filePathProperty = new SimpleStringProperty();
    
	public TextPage() {
		listProperty.set(FXCollections.observableArrayList(new ArrayList<Line>()));
		filePathProperty.setValue(new String(""));
	}
	public TextPage(File file) {
		this();
		
		filePathProperty.set(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 listProperty.add(new Line(new String(newLine)));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setFilePath(File file) { //Set filePath and new lineContents
		filePathProperty.set(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 listProperty.add(new Line(new String(newLine)));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getFilePath() {
		return new String(filePathProperty.get());
	}
	
	public void setTextField(String line, int lineN) { 
		listProperty.get(lineN).setLineText(line);
	}
	public String getTextField() {
		Iterator<Line> it = listProperty.iterator();
		String ret = new String();
		while(it.hasNext()) {
			ret += it.next().getLineText() + (it.hasNext()? System.lineSeparator():"");
		}
		return ret;
	}
	public ArrayList<Line> getTextLines() {
		ArrayList<Line> ret =  new ArrayList<Line>(listProperty);
		return ret;
	}
	public void addFakeLines(int from, int to) {
		for ( int i = from; i <= to; i++) {
			listProperty.add(i, new Line(false));
		}
	}
	
	//To control Line class variables in TextPage Class level with line number
	public boolean isRealLine(int lineN) {
		return listProperty.get(lineN).isRealLine();
	}
	public void setRealLine(int lineN, boolean bool) {
		listProperty.get(lineN).setIsRealLine(bool);
	}
	public void toogleIsRealLine(int lineN) {
		listProperty.get(lineN).toggleIsRealLine();
	}
	public Color getLineColor(int lineN) {
		return listProperty.get(lineN).getLineColor();
	}
	public void setLineColor(int lineN, Color color) {
		listProperty.get(lineN).setLineColor(color);
	}
	public String getLineText(int lineN) {
		return listProperty.get(lineN).getLineText();
	}
	public void setLineText(int lineN, String str) {
		listProperty.get(lineN).setLineText(str);
	}
	public void setTextLines(Stack<Line> input) {
		listProperty.clear();
		while(!input.isEmpty()) {
			listProperty.add(input.pop());
		}
	}
	public Property<ObservableList<Line>> getListProperty() {
		return listProperty;
	}
	public Property<String> getFilePathProperty() {
		return filePathProperty;
	}
}
