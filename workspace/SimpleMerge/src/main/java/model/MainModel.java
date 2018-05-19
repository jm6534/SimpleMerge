package model;

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
		return this.getRightSubModel();
	}
	public void setRightSubModel(SubModel rightSubModel) {
		this.rightSubModel = rightSubModel;
	}
	
	public void LCS() {
		// leftSubModel.getText()통해서  ArrayList 받아와서 하기
		// Implement here!!!
	}
}
