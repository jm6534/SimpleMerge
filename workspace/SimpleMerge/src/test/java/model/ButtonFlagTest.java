package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ButtonFlagTest {
	private ButtonFlag buttonFlag;
	
	@Before
	public void setUp() {
		buttonFlag = new ButtonFlag();
	}

	@Test
	public void testButtonFlag() {
		assertFalse(buttonFlag.isEditable());
		assertFalse(buttonFlag.isModified());
	}

	@Test
	public void testSetIsEditable() {
		buttonFlag.setIsEditable(true);
		assertTrue(buttonFlag.isEditable());
	}

	@Test
	public void testSetIsModified() {
		buttonFlag.setIsModified(true);
		assertTrue(buttonFlag.isModified());
	}

	@Test
	public void testToggleIsEditable() {
		buttonFlag.setIsEditable(true);
		buttonFlag.toggleIsEditable();
		assertFalse(buttonFlag.isEditable());
	}

	@Test
	public void testToggleIsModified() {
		buttonFlag.setIsModified(true);
		buttonFlag.toggleIsModified();
		assertFalse(buttonFlag.isModified());
	}
}
