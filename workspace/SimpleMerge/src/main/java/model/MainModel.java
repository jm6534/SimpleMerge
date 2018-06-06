package model;

import java.util.ArrayList;
import java.util.Stack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;

public class MainModel {
	private BooleanProperty isCompared;	// compare flag, Modified
	private SubModel leftSubModel;
	private SubModel rightSubModel;
	
	public MainModel() {
		this.isCompared = new SimpleBooleanProperty(false);
		this.leftSubModel = new SubModel();
		this.rightSubModel = new SubModel();
	}
	
	public SubModel getLeftSubModel() {
		return this.leftSubModel;
	}
	public void setLeftSubModel(SubModel leftSubModel) {
		this.leftSubModel = leftSubModel;
	}
	public SubModel getRightSubModel() {
		//return this.getRightSubModel(); <- miss?
		return this.rightSubModel;
	}
	
	public void setRightSubModel(SubModel rightSubModel) {
		this.rightSubModel = rightSubModel;
	}
	public boolean isCompared() {
		return this.isCompared.getValue();
	}
	public void setIsCompared(boolean isCompared) {
		this.isCompared.setValue(isCompared);
	}
	
	public void toggleIsCompared() {
		this.isCompared.setValue(!isCompared.getValue());
	}
	
	public void setLeftTextLines(ArrayList<Line> input) {
		this.leftSubModel.setTextLines(input);
	}
	public void setRightTextLines(ArrayList<Line> input) {
		this.rightSubModel.setTextLines(input);
	}
	
	public boolean LCS() {
		boolean result = LCS.doLCS(this); // try lcs
		return result;
	}
	public void copyToLeft() {
		int selectedIdx = (int) this.rightSubModel.getTextPage().getSelectedIndexProperty().getValue();
		int max = this.rightSubModel.getTextPage().getMaxNListProperty();
		if ( selectedIdx < 0 || selectedIdx > max ) {
			return;
		}
		int i = selectedIdx;
		Color selectCol= this.rightSubModel.getTextPage().getLineColor(i);
		if ( selectCol == Color.WHITE || selectCol == Color.LIGHTGRAY ) return;
		int to = i;
		int from = i;
		while (++i < max) {
			if ( this.rightSubModel.getTextPage().getLineColor(i) == selectCol) to = i;
			else break;
		}
		i = selectedIdx;
		while( --i > -1) {
			if (this.rightSubModel.getTextPage().getLineColor(i) == selectCol) from = i;
			else break;
		}
		if ( selectCol == Color.PAPAYAWHIP ) {
			int num = to - from + 1;
			while( num-- > 0) {
				this.leftSubModel.getTextPage().deleteLine(from);
				this.rightSubModel.getTextPage().deleteLine(from);						
			}
			return;
		}
		for ( i = from; i <= to; i++) {
			String str = this.rightSubModel.getTextPage().getLineText(i);
			boolean bool = this.rightSubModel.getTextPage().isRealLine(i);
			this.leftSubModel.getTextPage().setLineText(i, str);
			this.leftSubModel.getTextPage().setRealLine(i, bool);
			this.leftSubModel.getTextPage().setLineWHITE(i);
			this.rightSubModel.getTextPage().setLineWHITE(i);
		}
	}
	public void copyToRight() {
		int selectedIdx = (int) this.leftSubModel.getTextPage().getSelectedIndexProperty().getValue();
		int max = this.leftSubModel.getTextPage().getMaxNListProperty();
		if ( selectedIdx < 0 || selectedIdx > max ) {
			return;
		}
		int i = selectedIdx;
		Color selectCol= this.leftSubModel.getTextPage().getLineColor(i);
		if ( selectCol == Color.WHITE || selectCol == Color.LIGHTGRAY ) return;
		int to = i;
		int from = i;
		while (++i < max) {
			if ( this.leftSubModel.getTextPage().getLineColor(i) == selectCol) to = i;
			else break;
		}
		i = selectedIdx;
		while( --i > -1) {
			if (this.leftSubModel.getTextPage().getLineColor(i) == selectCol) from = i;
			else break;
		}
		if ( selectCol == Color.PAPAYAWHIP ) {
			int num = to - from + 1;
			while( num-- > 0) {
				this.leftSubModel.getTextPage().deleteLine(from);
				this.rightSubModel.getTextPage().deleteLine(from);						
			}
			return;
		}
		for ( i = from; i <= to; i++) {
			String str = this.leftSubModel.getTextPage().getLineText(i);
			boolean bool = this.leftSubModel.getTextPage().isRealLine(i);
			this.rightSubModel.getTextPage().setLineText(i, str);
			this.rightSubModel.getTextPage().setRealLine(i, bool);
			this.rightSubModel.getTextPage().setLineWHITE(i);
			this.leftSubModel.getTextPage().setLineWHITE(i);
		}
	}

	public BooleanProperty getIsComparedProperty() {
		return isCompared;
	}
}
