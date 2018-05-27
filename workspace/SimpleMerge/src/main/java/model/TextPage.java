package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class TextPage {
	private ArrayList<String> lineList;
    private ListProperty<String> listProperty = new SimpleListProperty<>();
    private StringProperty filePathProperty = new SimpleStringProperty();
		
	public TextPage() {
		lineList = new ArrayList<String>();
        listProperty.set(FXCollections.observableArrayList(lineList));
	}
	public TextPage(File file) {
		filePathProperty.setValue(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			 String newLine;
			 while( (newLine = br.readLine()) != null ) {
				 lineList.add(new String(newLine));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}/*
	public void setTextField(String line, int lineN) { 
		lineList.get(lineN).setLineText(line);
	}*/
	public String getTextField() {
		Iterator<String> it = lineList.iterator();
		String ret = new String();
		while(it.hasNext()) {
			ret += it.next() + "\n";
		}
		return ret;
	}
	
	public ArrayList<String> getTextLines() {
		ArrayList<String> ret =  lineList;
		return ret;
	}
	
	public void setListProperty(String line, int lineN) {
		
	}
	public ListProperty<String> ListProperty() {
		return listProperty;
	}
	public StringProperty FilePathProperty() {
		return filePathProperty;
	}
	
}
