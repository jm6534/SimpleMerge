package model;

import javafx.scene.paint.Color;

public class Line {

	private String lineText;	// String in a single line
	private Color lineColor;	// Represent the color of each line
	private boolean isRealLine;	// for representing the line not going to save	

	public Line() {
		this.lineText = new String();
		this.lineColor = Color.WHITE;
		this.isRealLine = true;
	}
	public Line(boolean bool) {
		this.lineText = new String();
		this.isRealLine = bool;
		if (this.isRealLine) this.lineColor = Color.WHITE;
		else this.lineColor = Color.LIGHTGRAY;
	}
	public Line(String text, boolean bool) {
		this.lineText = text;
		this.isRealLine = bool;
		if (this.isRealLine) this.lineColor = Color.WHITE;
		else this.lineColor = Color.LIGHTGRAY;
	}
	public Line(String lineText) {
		this.lineText = lineText;
		this.lineColor = Color.WHITE;
		this.isRealLine = true;
	}
	public Line(String lineText, Color lineColor) {
		this.lineText = lineText;
		this.lineColor = lineColor;
		if (lineColor == Color.LIGHTGRAY) this.isRealLine = false;
		else this.isRealLine = true;
	}
	public void setFakeLine() {
		this.lineText = new String();
		this.lineColor = Color.LIGHTGRAY;
		this.isRealLine = false;		
	}
	public void setFakeLine(String content) {
		this.lineText = new String(content);
		this.lineColor = Color.LIGHTGRAY;
		this.isRealLine = false;		
	}
	
	public String getLineText() {
		String ret = new String(lineText);
		return ret;
	}
	public void setLineText(String lineText) {
		this.lineText = lineText;
	}
	public Color getLineColor() {
		Color ret = Color.web(lineColor.toString());
		return ret;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	public boolean isRealLine() {
		return this.isRealLine;
	}
	
	public void setIsRealLine(boolean isRealLine) {
		this.isRealLine = isRealLine;
		if (this.isRealLine) this.lineColor = Color.WHITE;
		else this.lineColor = Color.LIGHTGRAY;
	}
	
	public String toString() {
		return getLineText();
	}
	
	public void toggleIsRealLine() {
		this.isRealLine = !this.isRealLine;
	}
}
