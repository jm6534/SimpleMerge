package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean isSaved;	// Save flag
	private boolean isModified; // Modified flag
	private boolean isEditButtonPressed;	// Edit flag
	
	public ButtonFlag() {
		this.isSaved = false;
		this.isEditButtonPressed = false;
		this.isModified = false;
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}	
	public void setIsSaved(boolean bool) {
		this.isSaved = bool;
	}

	public boolean isEditButtonPressed() {
		return this.isEditButtonPressed;
	}	
	public void setIsEditButtonPressed(boolean bool) {
		this.isEditButtonPressed = bool;
	}
	
	public boolean isModified() {
		return this.isModified;
	}	
	public void setIsModified(boolean bool) {
		this.isModified = bool;
	}
	
	public void toggleIsEditButtonPressed() {
		this.isEditButtonPressed = !isEditButtonPressed;
	}
	public void toggleIsModified() {
		this.isModified = !isModified;
	}
	public void toggleIsSaved() {
		this.isSaved = !isSaved;
	}
}