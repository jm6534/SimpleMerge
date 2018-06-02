package model;

import java.io.File;

import javafx.beans.property.Property;

/* Represented as 'Model_Panel' in Class diagram
 * Contain ButtonFlag and Text classes to represent each file on left and right
 */
public class SubModel {	
	private ButtonFlag btnFlag;
	private TextPage text;
	
	//Binding is needed!
	
	public SubModel() {
		text = new TextPage();
		btnFlag = new ButtonFlag();
	}
	public SubModel(File file) {
		text = new TextPage(file);
		btnFlag = new ButtonFlag();
	}
	public TextPage getTextPage() {
		TextPage ret = this.text;
		return ret;
	}	
	public void setTextPage(TextPage newTP) {
		this.text = newTP;
	}
	
	public ButtonFlag getButtonFlag() {
		ButtonFlag ret = this.btnFlag;
		return ret;
	}
	public void setButtonFlag(ButtonFlag newBtnFlag) {
		this.btnFlag = newBtnFlag;
	}
	
	public void setIsSaved(boolean bool) {
		this.btnFlag.setIsSaved(bool);
	}
	public void toggleIsSaved() {
		this.btnFlag.toggleIsSaved();
	}
	public boolean isEditable() {
		return this.btnFlag.isEditable();
	}
	public void setIsEditable(boolean bool) {
		this.btnFlag.setIsEditable(bool);
	}
	public void toggleIsEditable() {
		this.btnFlag.toggleIsEditable();
	}
	public boolean isModified() {
		return this.btnFlag.isModified();
	}
	public void setIsModified(boolean bool) {
		this.btnFlag.setIsModified(bool);
	}
	public void toggleIsModified() {
		this.btnFlag.toggleIsModified();
	}
	//To control Text Pages at SubModel Level
	public String getFilePath() {
		return new String(this.text.getFilePath());
	}
	public void setFilePath(File file) {
		this.text.setFilePath(file);
		this.btnFlag = new ButtonFlag();
	}
	public String getTextPageContent() {
		return new String(this.text.getTextField());
	}
	public void setTextPageContent(File file) {
		this.setFilePath(file);
	}
	public String getLineText(int lineN) {
		return this.text.getLineText(lineN);
	}
	public void setLineText(int lineN, String str) {
		this.text.setLineText(lineN, str);
	}
	public boolean isRealLine(int lineN) {
		return this.text.isRealLine(lineN);
	}
	public void setIsRealLine(int lineN, boolean bool) {
		this.text.setRealLine(lineN, bool);
	}
	public Property<Boolean> getEditableProperty() {
		return getButtonFlag().getEditableProperty();
	}
}