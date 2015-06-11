package cn.edu.xidian.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class VBSUtils {

	public static String SF_ALLUSERSDESKTOP    = "AllUsersDesktop";
	public static String SF_ALLUSERSSTARTMENU  = "AllUsersStartMenu";
	public static String SF_ALLUSERSPROGRAMS   = "AllUsersPrograms";
	public static String SF_ALLUSERSSTARTUP    = "AllUsersStartup";
	public static String SF_DESKTOP            = "Desktop";
	public static String SF_FAVORITES          = "Favorites";
	public static String SF_MYDOCUMENT         = "MyDocuments";
	public static String SF_PROGRAMS           = "Programs";
	public static String SF_RECENT             = "Recent";
	public static String SF_SENDTO             = "SendTo";
	public static String SF_STARTMENU          = "StartMenu";
	public static String SF_STARTUP            = "Startup";

	private VBSUtils() {  }

	public static String getSpecialFolder(String folder) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto",".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
					+ "wscript.echo WshShell.SpecialFolders(\"" + folder + "\")\n"            
					+ "Set WSHShell = Nothing\n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			result = input.readLine();
			input.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args){
		System.out.println(VBSUtils.getSpecialFolder(VBSUtils.SF_ALLUSERSDESKTOP));
		System.out.println(VBSUtils.getSpecialFolder(VBSUtils.SF_DESKTOP));
		System.out.println(VBSUtils.getSpecialFolder(VBSUtils.SF_PROGRAMS));
	}
}
