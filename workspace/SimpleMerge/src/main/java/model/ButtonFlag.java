package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean SF;	// Save flag
	private boolean LF; // Load flag
	private boolean EF;	// Edit flag
	
	public ButtonFlag() {
		this.SF = false;
		this.SF = false;
		this.SF = false;
	}
	
	// Called by controller when the save button is clicked
	public void saveBtnClicked() {
		
	}
	public boolean getSaveFlag() {
		return this.SF;
	}	
	public void setSaveFlag(boolean SF) {
		this.SF = SF;
	}

	// Called by controller when the load button is clicked
	public void loadBtnClicked() {
		
	}
	public boolean getLoadFlag() {
		return this.LF;
	}	
	public void setLoadFlag(boolean LF) {
		this.LF = LF;
	}

	// Called by controller when the edit button is clicked
	public void editBtnClicked() {
		this.EF = true;
	}	
	public boolean getEditFlag() {
		return this.EF;
	}	
	public void setEditFlag(boolean EF) {
		this.EF = EF;
	}
}
