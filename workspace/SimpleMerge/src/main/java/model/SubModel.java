package model;

import java.io.File;

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
	
	//To control Button flags at SubModel level
	public boolean isEditButtonPressed() {
		return this.btnFlag.isEditButtonPressed();
	}
	public void setIsEditButtonPressed(boolean bool) {
		this.btnFlag.setIsEditButtonPressed(bool);
	}
	public void toggleIsEditButtonPressed() {
		this.btnFlag.toggleIsEditButtonPressed();
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
}