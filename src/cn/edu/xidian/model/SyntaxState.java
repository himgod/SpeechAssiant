package cn.edu.xidian.model;

import java.util.HashMap;

public class SyntaxState {

	String name;	
	HashMap<String, SyntaxState> transformation;
	boolean isEnd;
	
	public SyntaxState(String name){
		this.name = name;
	}
	
	public SyntaxState(){
		
		transformation = new HashMap<String,SyntaxState>();
		
	}
}
