package cn.edu.xidian.impl;

import cn.edu.xidian.interfaces.Opener;
import cn.edu.xidian.utils.ShellRunner;

public class WindowsOpener implements Opener {

	@Override
	public void openQQ() {
		ShellRunner.callCmd("openQQ.bat");
	}

	@Override
	public void openKuGou() {
		ShellRunner.callCmd("openKuGou.bat");
	}

	@Override
	public void openTrash() {
		ShellRunner.callCmd("openTrash.bat");
	}

	@Override
	public void openMyPC() {
		ShellRunner.callCmd("openMyPC.bat");
	}

	@Override
	public void openBrowser() {
		ShellRunner.callCmd("openChrome.bat");
	}

	@Override
	public void openNotepad() {
		ShellRunner.callCmd("openNotepad.bat");
	}

}
