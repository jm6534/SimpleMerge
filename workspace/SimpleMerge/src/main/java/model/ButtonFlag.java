package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean isModified; // Modified flag
	private boolean isEditButtonPressed;	// Edit flag
	
	public ButtonFlag() {
		this.isEditButtonPressed = false;
		this.isModified = false;
	}
	
	public boolean isEditButtonPressed() {
		return this.isEditButtonPressed;
	}	
	public void setIsEditButtonPressed(boolean bool) {
		this.isEditButtonPressed = bool;
	}
	public void toggleIsEditButtonPressed() {
		this.isEditButtonPressed = !isEditButtonPressed;
	}
	
	public boolean isModified() {
		return this.isModified;
	}	
	public void setIsModified(boolean bool) {
		this.isModified = bool;
	}
	public void toggleIsModified() {
		this.isModified = !isModified;
	}
}