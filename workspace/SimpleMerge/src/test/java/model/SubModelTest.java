package model;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import junit.framework.TestCase;

public class SubModelTest extends TestCase {
	private SubModel testsModel;
	private TextPage mockTextPage;
	private ButtonFlag mockbtnFlag;
	@Before
	public void setUp(){
		testsModel = new SubModel();
		mockTextPage = EasyMock.createMock(TextPage.class);
		mockbtnFlag = EasyMock.createMock(ButtonFlag.class);
	}
	@Test
	public void testTextPage() { 
		testsModel.setTextPage(mockTextPage);
		assertEquals(mockTextPage, testsModel.getTextPage());
	}
	@Test
	public void testButtonFlagTrue() { 
		testsModel.setButtonFlag(mockbtnFlag);
		assertEquals(mockbtnFlag, testsModel.getButtonFlag());
	}
	@Test
	public void testButtonFlagFalse() {
		mockbtnFlag.setIsEditable(true);
		mockbtnFlag.setIsModified(true);
		testsModel.setButtonFlag(mockbtnFlag);

		ButtonFlag cmpFlag = new ButtonFlag();
		cmpFlag.setIsEditable(false);
		cmpFlag.setIsModified(false);
		assertThat(cmpFlag, not(testsModel.getButtonFlag()));	
	}
	@Test
	public void testIsModified() {
		assertFalse(testsModel.isModified());
		testsModel.toggleIsModified();
		assertTrue(testsModel.isModified());
	}
	
}
