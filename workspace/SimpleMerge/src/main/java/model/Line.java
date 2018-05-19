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
	
	public String getLineText() {
		String ret = new String(lineText);
		return ret;
	}
	public void setLineText(String lineText) {
		this.lineText = lineText;
	}
	public boolean getIsShaded() {
		boolean ret = new Boolean(isShaded);
		return ret;
	}
	public void setIsShaded(boolean isShaded) {
		this.isShaded = isShaded;
	}
}
