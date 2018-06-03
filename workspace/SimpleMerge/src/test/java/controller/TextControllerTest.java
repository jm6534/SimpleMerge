package controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.event.ActionEvent;
import junit.framework.TestCase;
import model.Line;
import model.MainModel;
import model.SubModel;
import model.TextPage;

public class TextControllerTest extends TestCase{
	TextController testController;
	private SubModel subModel;
	@Before
	public void setUp() {
		testController = new TextController();
		subModel = new SubModel();
		testController.setSubModel(subModel);
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
