package Test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.Recruitments;

public class TestRecruiments extends TestTemplate {
	@Test
	public void testHoverRec() {
		Recruitments tRec = new Recruitments(driver);
		String text = tRec.Hover();
		assertEquals(text, "");
	}

	@Test
	public void testHoverAndClickRec() {
		Recruitments tHaC = new Recruitments(driver);
		String text = tHaC.hoverAndClick();
		assertEquals(text, "Môi trường làm việc");

	}
}
