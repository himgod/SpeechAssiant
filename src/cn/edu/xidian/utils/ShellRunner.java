package cn.edu.xidian.utils;

import java.io.File;
import java.io.IOException;


public class ShellRunner{  

	public static void callCmd(String locationCmd){
		try {
			File dir = new File("bin/cn/edu/xidian/scripts/");
			//Process child = 
			Runtime.getRuntime().exec("cmd.exe /c "+locationCmd,null,dir);//"cmd.exe /c start"+locationCmd,null,dir
//			InputStream in = child.getInputStream();
//			int c;
//			while ((c = in.read()) != -1) {
//			}
//			in.close();
//			try {
//				child.waitFor();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String []arge)throws Exception {
		ShellRunner.callCmd("openQQ.bat");
	} 
}
