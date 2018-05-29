package model;

import java.awt.Color;

public class Line {

	private String lineText;	// String in a single line
	private Color lineColor;	// Represent the color of each line
	private boolean isRealLine;	// for representing the line not going to save	

	public Line() {
		this.lineText = new String();
		this.lineColor = Color.WHITE;
		this.isRealLine = true;
	}
	public Line(String lineText) {
		this.lineText = lineText;
		this.lineColor = Color.WHITE;
		this.isRealLine = true;
	}
	public Line(String lineText, Color lineColor) {
		this.lineText = lineText;
		this.lineColor = lineColor;
		this.isRealLine = true;
	}
	
	public String getLineText() {
		String ret = new String(lineText);
		return ret;
	}
	public void setLineText(String lineText) {
		this.lineText = lineText;
	}
	public Color getLineColor() {
		Color ret = new Color(lineColor.getRGB());
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
	}
	
	public String toString() {
		return getLineText();
	}
	
	public void toggleIsRealLine() {
		this.isRealLine = !this.isRealLine;
	}
}
