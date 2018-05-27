package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean saveFlag;	// Save flag
	private boolean editFlag;	// Edit flag
	
	public ButtonFlag() {
		this.saveFlag = false;
		this.editFlag = false;
	}
	
	public boolean getSaveFlag() {
		boolean ret = new Boolean(saveFlag);
		return ret;
	}	
	public void setSaveFlag(boolean SF) {
		this.saveFlag = SF;
	}

	public boolean getEditFlag() {
		boolean ret = new Boolean(editFlag);
		return ret;
	}	
	public void setEditFlag(boolean EF) {
		this.editFlag = EF;
	}
}