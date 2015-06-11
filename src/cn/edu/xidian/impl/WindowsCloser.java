package cn.edu.xidian.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import cn.edu.xidian.interfaces.Closer;

public class WindowsCloser implements Closer {

	Robot robot;
	
	public WindowsCloser(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void closeCurrentWindow() {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_F4);
	}

	
	public static void main(String[] args){
		new WindowsCloser().closeCurrentWindow();
	}
}
