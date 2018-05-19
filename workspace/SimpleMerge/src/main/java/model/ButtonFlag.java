package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean SF;	// Save flag
	private boolean EF;	// Edit flag
	
	public ButtonFlag() {
		this.SF = false;
		this.EF = false;
	}
	
	public boolean getSaveFlag() {
		return this.SF;
	}	
	public void setSaveFlag(boolean SF) {
		this.SF = SF;
	}

	public boolean getEditFlag() {
		return this.EF;
	}	
	public void setEditFlag(boolean EF) {
		this.EF = EF;
	}
}