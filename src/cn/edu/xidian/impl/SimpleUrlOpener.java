package cn.edu.xidian.impl;

import cn.edu.xidian.interfaces.UrlOpener;

public class SimpleUrlOpener implements UrlOpener {

	@Override
	public void openUrl(String url) {
		
        try {
        	java.net.URI uri = new java.net.URI(url);
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new SimpleUrlOpener().openUrl("http://www.baidu.com");
	}
}
