package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import org.apache.commons.io.*;

public class TextPage {
    private ListProperty<Line> listProperty = new SimpleListProperty<>();
    private StringProperty filePathProperty = new SimpleStringProperty();
	private IntegerProperty selectedIndexProperty = new SimpleIntegerProperty();
    
	public TextPage() {
		listProperty.set(FXCollections.observableArrayList(new ArrayList<Line>()));
		filePathProperty.setValue(new String(""));
	}
	public TextPage(File file) {
		this();
		
		filePathProperty.set(file.getAbsolutePath());
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
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
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			 String newLine;
			 for(int i=0; (newLine = br.readLine()) != null; i++) {
				 setLineText(i, new String(newLine));
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getFilePath() {
		return new String(filePathProperty.get());
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
	
	public String getTextFieldForSave() {
		Iterator<Line> it = listProperty.iterator();
		String ret = new String();
		Line tmp;
		while(it.hasNext()) {
			tmp = it.next();
			if (tmp.isRealLine()) {
				ret += tmp.getLineText() + (it.hasNext()? System.lineSeparator():"");
			}
		}
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
	public void setLineWHITE(int lineN) {
		listProperty.get(lineN).setLineWHITE();
	}
	public void setLineLIGHTGRAY(int lineN) {
		listProperty.get(lineN).setLineLIGHTGRAY();
	}
	public void setLineYELLOW(int lineN) {
		listProperty.get(lineN).setLineYELLOW();
	}
	public void setLinePINK(int lineN) {
		listProperty.get(lineN).setLinePINK();
	}
	public void setLinePAPAYA(int lineN) {
		listProperty.get(lineN).setLinePAPAYA();
	}
	public String getLineText(int lineN) {
		return listProperty.get(lineN).getLineText();
	}
	public void setLineText(int lineN, String str) {
		if(listProperty.size() > lineN && lineN >= 0) listProperty.remove(lineN);
		listProperty.add(lineN, new Line(str));
	}
	public void addLineText(String str) {
		listProperty.add(new Line(str));
	}
	public void addFakeLine() {
		listProperty.add(new Line(false));
	}
	public void deleteLine(int lineN) {
		if ( lineN < 0 || lineN > listProperty.getSize() ) return;
		listProperty.remove(lineN);
	}
	public void setTextLines(ArrayList<Line> input) {
		listProperty.clear();
		for (int i = 0 ; i < input.size() ; i ++) {
			listProperty.add(input.get(i));
		}
	}
	public Property<ObservableList<Line>> getListProperty() {
		return listProperty;
	}
	public int getMaxNListProperty() {
		int ret = listProperty.size();
		return ret;
	}
	public Property<String> getFilePathProperty() {
		return filePathProperty;
	}
	public void clearBackground() {
		for(Line line : listProperty) {
			line.setLineWHITE();
		}
	}
	public Property<Number> getSelectedIndexProperty() {
		return selectedIndexProperty;
	}
	public void setSelectedLineColor(int selectedIndex) {
		if(selectedIndex < 0 || selectedIndex >= listProperty.size()) return;
		if(getLineColor(selectedIndex).equals(Color.WHITE)) return;
		for(Line line : listProperty) {
			if(line.getLineColor().equals(Color.WHITE)) continue;
			else if(line.isRealLine()) line.setLineYELLOW();
			else line.setLineLIGHTGRAY();
		}
		if(listProperty.get(selectedIndex).isRealLine()) {
			listProperty.get(selectedIndex).setLinePINK();
			int i = selectedIndex;
			int max = listProperty.getSize();
			while (++i < max) {
				if (listProperty.get(i).getLineColor() == Color.LIGHTGOLDENRODYELLOW) {
					listProperty.get(i).setLinePINK();
				}
				else break;
			}
			i = selectedIndex;
			while( --i > -1) {
				if (listProperty.get(i).getLineColor() == Color.LIGHTGOLDENRODYELLOW) {
					listProperty.get(i).setLinePINK();					
				}
				else break;
			}
		}
		else {
			listProperty.get(selectedIndex).setLinePAPAYA();
			int i = selectedIndex;
			int max = listProperty.getSize();
			while (++i < max) {
				if (listProperty.get(i).getLineColor() == Color.LIGHTGRAY) {
					listProperty.get(i).setLinePAPAYA();
				}
				else break;
			}
			i = selectedIndex;
			while( --i > -1) {
				if (listProperty.get(i).getLineColor() == Color.LIGHTGRAY) {
					listProperty.get(i).setLinePAPAYA();
				}
				else break;
			}
		}
	}
	public void clearContents() {
		clearBackground();
		while(listProperty.size() > 0) {
			listProperty.remove(0);
		}
		filePathProperty.setValue("");
	}
}
