package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;

/* Contain info about save, load, edit buttons if the button is clicked
*/
public class ButtonFlag {
	private boolean isModified; // Modified flag
	private BooleanProperty isEditable = new SimpleBooleanProperty(false);	// Edit flag
	
	public ButtonFlag() {
		this.isEditable.setValue(false);
		this.isModified = false;
	}
	public boolean isEditable() {
		return this.isEditable.getValue();
	}	
	public void setIsEditable(boolean bool) {
		this.isEditable.setValue(bool);
	}
	public boolean isModified() {
		return this.isModified;
	}	
	public void setIsModified(boolean bool) {
		this.isModified = bool;
	}
	public void toggleIsEditable() {
		this.isEditable.setValue(!isEditable.getValue());
	}
	public void toggleIsModified() {
		this.isModified = !isModified;
	}

	public Property<Boolean> getEditableProperty() {
		return isEditable;
	}
}