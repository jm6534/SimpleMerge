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
	private boolean copyFromTo (SubModel fromModel, SubModel toModel) {
		if ( fromModel.isEditable() ) return false;
		if ( toModel.isEditable() ) return false;
		int selectedIdx = (int) fromModel.getTextPage().getSelectedIndexProperty().getValue();
		int max = fromModel.getTextPage().getMaxNListProperty();
		if ( max == 0 ) return false;
		if ( selectedIdx < 0 || selectedIdx > max ) {
			return false;
		}
		int i = selectedIdx;
		Color selectCol= fromModel.getTextPage().getLineColor(i);
		if ( selectCol == Color.WHITE || selectCol == Color.LIGHTGRAY ) return false;
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
				Color fromLastLineC;
				Color toLastLineC;
				fromLastLineC = fromModel.getTextPage().getLineColor(max - 1);
				toLastLineC = toModel.getTextPage().getLineColor(max - 1);
				toModel.getTextPage().setLineWHITE(max - 1);
				fromModel.getTextPage().setLineWHITE(max - 1);				
				toModel.getTextPage().setLineWHITE(from);
				fromModel.getTextPage().setLineWHITE(from);
				fromModel.getTextPage().deleteLine(from);
				toModel.getTextPage().deleteLine(from);
				--max;
				setLastLineColor(fromModel, max - 1, fromLastLineC);
				setLastLineColor(toModel, max - 1, toLastLineC);
			}
			return true;
		}
		for ( i = from; i <= to; i++) {
			String str = fromModel.getTextPage().getLineText(i);
			fromModel.getTextPage().setLineWHITE(i);
			toModel.getTextPage().setLineText(i, str);
			toModel.getTextPage().setRealLine(i, true);
			toModel.getTextPage().setLineWHITE(i);
		}
		return true;
	}
	private void setLastLineColor(SubModel sModel, int lineN, Color color) {
		if ( color == Color.LIGHTGRAY ) sModel.getTextPage().setLineLIGHTGRAY(lineN);
		if ( color == Color.PINK ) sModel.getTextPage().setLinePINK(lineN);
		if ( color == Color.PAPAYAWHIP ) sModel.getTextPage().setLinePAPAYA(lineN);
		if ( color == Color.LIGHTGOLDENRODYELLOW ) sModel.getTextPage().setLineYELLOW(lineN);
	}
	public boolean copyToLeft() {
		if ( !isCompared.get() ) return false;
		return copyFromTo(rightSubModel, leftSubModel);
	}
	public boolean copyToRight() {
		if ( !isCompared.get() ) return false;
		return copyFromTo(leftSubModel, rightSubModel);
	}
	public boolean resetTextPages() {
		resetTextPage ( leftSubModel.getTextPage() );
		resetTextPage ( rightSubModel.getTextPage() );
		return true;
	}
	private boolean resetTextPage( TextPage reset ) {
		int max = reset.getMaxNListProperty();
		int lineN = 0;
		while ( max > 0 ) {
			Color selectedColor = reset.getLineColor(lineN);
			if ( selectedColor == Color.LIGHTGOLDENRODYELLOW || selectedColor == Color.PINK ) {
				reset.setLineWHITE(lineN);
			}
			if ( selectedColor == Color.LIGHTGRAY || selectedColor == Color.PAPAYAWHIP ) {
				reset.setLineWHITE(lineN);
				reset.deleteLine(lineN);
				lineN--;
			}
			lineN++;
			max--;
		}
		return true;
	}

	public BooleanProperty getIsComparedProperty() {
		return isCompared;
	}
}
