package cn.edu.xidian.interfaces;

import java.util.List;

import edu.cmu.sphinx.result.WordResult;

public interface SematicAnalyzer {

	public void setTokenList(List<WordResult> list);
	
	public void execute();
}
