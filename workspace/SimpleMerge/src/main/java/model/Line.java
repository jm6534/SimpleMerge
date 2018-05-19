package model;

public class Line {
	private String lineText;	// String in a single line
	private boolean isShaded;	// flag for checking if it's shaded
	
	public Line(String lineText) {
		this.lineText = lineText;
		this.isShaded = false;
	}
	
	public Line(String lineText, boolean isShaded) {
		this.lineText = lineText;
		this.isShaded = isShaded;		
	}
	
	public boolean isShaded() {
		return this.isShaded;
	}
}
