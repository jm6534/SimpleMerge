package model;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

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
	public void testButtonFlag() { 
		testsModel.setButtonFlag(mockbtnFlag);
		assertEquals(mockbtnFlag, testsModel.getButtonFlag());
	}
}
