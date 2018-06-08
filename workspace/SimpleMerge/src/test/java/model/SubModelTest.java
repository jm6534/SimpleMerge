package model;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junit.framework.TestCase;

public class SubModelTest {
	private SubModel testsModel;
	private TextPage mockTextPage;
	private ButtonFlag mockbtnFlag;
	private File mockFile;
	private static final String FILE_NAME = "test_file.txt";

	@Rule
	public final TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException{
		testsModel = new SubModel();
		mockTextPage = EasyMock.createMock(TextPage.class);
		mockbtnFlag = EasyMock.createMock(ButtonFlag.class);
    	mockFile = tempFolder.newFile(FILE_NAME	);
	}
	
	@Test
	public void testSubModelConstructor() {
		try {
			new SubModel();
			new SubModel(mockFile);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
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
