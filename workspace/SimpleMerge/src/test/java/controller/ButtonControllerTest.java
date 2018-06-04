package controller;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import junit.framework.TestCase;
import model.MainModel;

public class ButtonControllerTest extends TestCase{
	ButtonController testController;
	private MainModel mainModel;
	@Before
	public void setUp() {
		JFXPanel a = new JFXPanel();
		testController = new ButtonController();
		mainModel = new MainModel();
		testController.setMainModelForTest(mainModel);
	}

	@Test
	public void testCopyToRight() {
		testController.copyToRightClick(new ActionEvent());
		assertTrue(mainModel.getRightSubModel().isModified() && mainModel.isCompared());
	}
	@Test
	public void testCopyToLeft() {
		testController.copyToLeftClick(new ActionEvent());
		assertTrue(mainModel.getLeftSubModel().isModified() && mainModel.isCompared());
	}
	@Test
	public void testCompareEqual() {
		mainModel.getLeftSubModel().addLineText(0, "aaa");
		mainModel.getLeftSubModel().addLineText(0, "aaa");
		assertTrue(!mainModel.isCompared());
	}
	@Test
	public void testCompareNonEqual() {
		mainModel.getLeftSubModel().addLineText(0, "aaa");
		mainModel.getLeftSubModel().addLineText(0, "bbb");
		assertTrue(mainModel.isCompared());
	}
}