package com.edu.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public final class ConsoleUtil {
	public static void clear(){
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.mousePress(InputEvent.BUTTON3_MASK);
		r.mouseRelease(InputEvent.BUTTON3_MASK);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_R);
		r.keyRelease(KeyEvent.VK_R);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.delay(100);
	}
	public static void lodaing() throws InterruptedException {
		Thread.sleep(2000);
		clear();
	}
}
