package model;

import java.io.File;

/* Represented as 'Model_Panel' in Class diagram
 * Contain ButtonFlag and Text classes to represent each file on left and right
 */
public class SubModel {	
	private ButtonFlag btnFlag;
	private TextPage text;
	
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
}