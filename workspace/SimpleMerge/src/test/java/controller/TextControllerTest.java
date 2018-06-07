package controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import junit.framework.TestCase;
import model.MainModel;
import model.SubModel;

public class TextControllerTest{
	TextController testController;
	private SubModel subModel;
	private MainModel mainModel;
	@Before
	public void setUp() {
		JFXPanel a = new JFXPanel();
		testController = new TextController();
		subModel = new SubModel();
		mainModel = new MainModel();
		testController.setModelsForTest(mainModel, subModel);
	}
	
	@Test
	public void testEdit() {
		testController.editClick(new ActionEvent());
		assertTrue(subModel.isEditable());
	}
	
	/* 정상적인 상황 */
	@Test
	public void save1() {
		subModel.setLineText(0, "aaaaaa");
		testController.saveClick(new ActionEvent());
	}
	@Test
	public void save2() {
		
	}
	@Test
	public void save3() {
		
	}
	@Test
	public void load1() {
		
	}
}
