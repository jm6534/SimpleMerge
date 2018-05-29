package model;

import junit.framework.*;


public class LineTest extends TestCase {
	private Line testLine = new Line();
	
	public void testSetLineTextTrue() {
		testLine.setLineText("Testing Success");
		assertEquals("Testing Success", testLine.getLineText());
	}
	public void testSetIsShadedTrue() {
		testLine.setIsRealLine(true);
		assertEquals(true, testLine.isRealLine());
		testLine.toggleIsRealLine();
		assertEquals(false, testLine.isRealLine());
	}
	public void testSetLineTextFalse() {
		testLine.setLineText("false");
		assertEquals("Testing Fail", testLine.getLineText());
	}
	public void testSetIsShadedFalse() {
		testLine.setIsRealLine(false);
		assertEquals(true, testLine.isRealLine());
	}
	public static Test suite() {
		return new TestSuite(LineTest.class);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
