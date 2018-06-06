package model;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
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
	private void copyFromTo (SubModel fromModel, SubModel toModel) {
		if ( fromModel.isEditable() ) return;
		if ( toModel.isEditable() ) return;
		int selectedIdx = (int) fromModel.getTextPage().getSelectedIndexProperty().getValue();
		int max = fromModel.getTextPage().getMaxNListProperty();
		if ( max == 0 ) return;
		if ( selectedIdx < 0 || selectedIdx > max ) {
			return;
		}
		int i = selectedIdx;
		Color selectCol= fromModel.getTextPage().getLineColor(i);
		if ( selectCol == Color.WHITE || selectCol == Color.LIGHTGRAY ) return;
		int to = i;
		int from = i;
		while (++i < max) {
			if ( fromModel.getTextPage().getLineColor(i) == selectCol) to = i;
			else break;
		}
		i = selectedIdx;
		while( --i > -1) {
			if (fromModel.getTextPage().getLineColor(i) == selectCol) from = i;
			else break;
		}
		if ( selectCol == Color.PAPAYAWHIP ) {
			int num = to - from + 1;
			while( num-- > 0) {
				toModel.getTextPage().setLineWHITE(from);
				fromModel.getTextPage().setLineWHITE(from);
				fromModel.getTextPage().deleteLine(from);
				toModel.getTextPage().deleteLine(from);
				--max;
				toModel.getTextPage().setLineWHITE(max - 1);
				fromModel.getTextPage().setLineWHITE(max - 1);				
			}
			return;
		}
		for ( i = from; i <= to; i++) {
			String str = fromModel.getTextPage().getLineText(i);
			boolean bool = fromModel.getTextPage().isRealLine(i);
			fromModel.getTextPage().setLineWHITE(i);
			toModel.getTextPage().setLineText(i, str);
			toModel.getTextPage().setRealLine(i, bool);
			toModel.getTextPage().setLineWHITE(i);
		}
	}
	public void copyToLeft() {
		copyFromTo(rightSubModel, leftSubModel);
	}
	public void copyToRight() {
		copyFromTo(leftSubModel, rightSubModel);
	}

	public BooleanProperty getIsComparedProperty() {
		return isCompared;
	}
}
