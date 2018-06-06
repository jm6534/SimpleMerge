package model;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class Line {

	private String lineText;	// String in a single line
	private ColorPicker lineColor;	// Represent the color of each line
	private boolean isRealLine;	// for representing the line not going to save	

	public Line() {
		this.lineText = new String();
		this.lineColor = new ColorPicker();
		this.lineColor.setValue(Color.WHITE);
		this.isRealLine = true;
	}
	public Line(boolean bool) {
		this();
		this.isRealLine = bool;
		if (!this.isRealLine) this.lineColor.setValue(Color.LIGHTGRAY);
	}
	public Line(String text, boolean bool) {
		this();
		this.isRealLine = bool;
		this.lineText = text;
		if (!this.isRealLine) this.lineColor.setValue(Color.LIGHTGRAY);
	}
	public Line(String lineText) {
		this();
		this.lineText = lineText;
	}
	public Line(String lineText, Color lineColor) {
		this();
		this.lineText = lineText;
		if (lineColor != Color.WHITE && lineColor != Color.PINK
				&& lineColor != Color.LIGHTGRAY & lineColor != Color.PAPAYAWHIP
				&& lineColor != Color.LIGHTGOLDENRODYELLOW ) {
			this.setLineWHITE();
		}
		else this.lineColor.setValue(lineColor);
		if (lineColor == Color.LIGHTGRAY) this.isRealLine = false;
	}
	public void setFakeLine() {
		this.lineText = new String();
		this.lineColor.setValue(Color.LIGHTGRAY);
		this.isRealLine = false;
	}
	public void setFakeLine(String content) {
		this.lineText = new String(content);
		this.lineColor.setValue(Color.LIGHTGRAY);
		this.isRealLine = false;		
	}
	
	public String getLineText() {
		String ret = new String(lineText);
		return ret;
	}
	public void setLineText(String lineText) {
		this.lineText = lineText;
		this.setIsRealLine(true);
	}
	public Color getLineColor() {
		Color ret = lineColor.getValue();
		return ret;
	}
	public void setLineWHITE() {
		this.lineColor.setValue(Color.WHITE);
	}
	public void setLineLIGHTGRAY() {
		this.lineColor.setValue(Color.LIGHTGRAY);
	}
	public void setLineYELLOW() {
		this.lineColor.setValue(Color.LIGHTGOLDENRODYELLOW);
	}
	public void setLinePINK() {
		this.lineColor.setValue(Color.PINK);
	}
	public void setLinePAPAYA() {
		this.lineColor.setValue(Color.PAPAYAWHIP);
	}
	public ColorPicker getLineColorProperty() {
		return lineColor;
	}
	
	public boolean isRealLine() {
		return this.isRealLine;
	}
	
	public void setIsRealLine(boolean isRealLine) {
		this.isRealLine = isRealLine;
		if (this.isRealLine) this.lineColor.setValue(Color.WHITE);
		this.lineColor.setValue(Color.LIGHTGRAY);
	}
	
	public String toString() {
		return getLineText();
	}
	
	public void toggleIsRealLine() {
		this.isRealLine = !this.isRealLine;
	}
	
}
