package model;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean isSaved;	// Save flag
	private boolean isEdited;	// Edit flag
	
	public ButtonFlag() {
		this.isSaved = false;
		this.isEdited = false;
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}	
	public void setIsSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public boolean isEdited() {
		return this.isEdited;
	}	
	public void setIsEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
	
	public void toggleIsEdited() {
		this.isEdited = !isEdited;
	}
	
	public void toggleIsSaved() {
		this.isSaved = !isSaved;
	}
}