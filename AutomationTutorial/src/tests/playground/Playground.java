package tests.playground;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import utils.KeyboardUtils;

public class Playground {

	public static void main(String[] args){

		try {
			//Open notepad and it should type on it
			
			String filePath1 = "C:\\Users\\sanga\\Pictures\\go2meeting.png";
			String filePath = "\\Users\\sanga\\Pictures\\go2meeting.png";

			new KeyboardUtils().Key_Any(KeyEvent.VK_C);
			new KeyboardUtils().Key_Colon();

		//	new KeyboardUtils().Key_Colon();
			KeyboardUtils.typeString(filePath);

			Robot robot = new Robot();
			//		robot.keyPress(KeyEvent.VK_TAB);
			//		robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		} 


	}

}
