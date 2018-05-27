package model;

import junit.framework.*;


public class LineTest extends TestCase {
	private Line testLine = new Line();
	
	public void testSetLineTextTrue() {
		testLine.setLineText("Testing Success");
		assertEquals("Testing Success", testLine.getLineText());
	}
	public void testSetIsShadedTrue() {
		testLine.setIsShaded(true);
		assertEquals(true, testLine.getIsShaded());
		testLine.setIsShaded(false);
		assertEquals(false, testLine.getIsShaded());
	}
	public void testSetLineTextFalse() {
		testLine.setLineText("false");
		assertEquals("Testing Fail", testLine.getLineText());
	}
	public void testSetIsShadedFalse() {
		testLine.setIsShaded(false);
		assertEquals(true, testLine.getIsShaded());
	}
	public static Test suite() {
		return new TestSuite(LineTest.class);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
