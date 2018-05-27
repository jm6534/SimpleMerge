package model;

import java.util.ArrayList;
import java.util.Iterator;

public class MainModel {
	private boolean CF;	// compare flag, Modified역할
	private SubModel leftSubModel;
	private SubModel rightSubModel;
	
	public MainModel() {
		this.CF = false;
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
	/*
	public void LCS() {
		// leftSubModel.getText()통해서  ArrayList 받아와서 하기
		// Implement here!!!
		
		Iterator<Line> left = leftSubModel.getTextPage().getTextLines().iterator();
		Iterator<Line> right = rightSubModel.getTextPage().getTextLines().iterator();
		// SubModel class didn't have getTextPage method
		
		while (left.hasNext()&&right.hasNext()) { // if both has next
			Line leftside = left.next();
			Line rightside = right.next();
			if (!leftside.getLineText().equals(rightside.getLineText())) { // if diff
				leftside.setIsShaded(true);
				rightside.setIsShaded(true);
			}
		}
		
		while(left.hasNext()||right.hasNext()) { // if one side has next
			// later
		}
		
	}
	*/
}
