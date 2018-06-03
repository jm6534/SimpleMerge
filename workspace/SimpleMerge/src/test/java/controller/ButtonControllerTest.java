package controller;

import org.junit.Before;

import javafx.event.ActionEvent;
import junit.framework.TestCase;
import model.MainModel;

public class ButtonControllerTest extends TestCase{
	ButtonController testController;
	private MainModel mainModel;
	@Before
	public void setUp() {
		testController = new ButtonController();
		mainModel = new MainModel();
		testController.setMainModel(mainModel);
	}

	public void copyToRight() {
		testController.copyToRightClick(new ActionEvent());
		assertTrue(mainModel.getRightSubModel().isModified() && mainModel.isCompared());
	}
	public void copyToLeft() {
		testController.copyToLeftClick(new ActionEvent());
		assertTrue(mainModel.getLeftSubModel().isModified() && mainModel.isCompared());
	}
	public void compareEqual() {
		mainModel.getLeftSubModel().setLineText(0, "aaa");
		mainModel.getLeftSubModel().setLineText(0, "aaa");
		assertTrue(!mainModel.isCompared());
	}
	public void compareNonEqual() {
		mainModel.getLeftSubModel().setLineText(0, "aaa");
		mainModel.getLeftSubModel().setLineText(0, "bbb");
		assertTrue(mainModel.isCompared());
	}
}