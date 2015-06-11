package cn.edu.xidian.impl;

import java.io.IOException;
import java.util.List;

import cn.edu.xidian.interfaces.AdvancedController;
import cn.edu.xidian.interfaces.Closer;
import cn.edu.xidian.interfaces.Minimizer;
import cn.edu.xidian.interfaces.Opener;
import cn.edu.xidian.interfaces.SematicAnalyzer;
import cn.edu.xidian.interfaces.UrlOpener;
import edu.cmu.sphinx.linguist.dictionary.Dictionary;
import edu.cmu.sphinx.linguist.dictionary.Word;
import edu.cmu.sphinx.result.WordResult;

public class SematicAnalyzerPutong implements SematicAnalyzer{
	
	int index;
	List<WordResult> tokenList;

	public SematicAnalyzerPutong(){
		
	}
	
	public SematicAnalyzerPutong(List<WordResult> list){
		tokenList = list;
		index = 0;
	}
	
	public void setTokenList(List<WordResult> list){
		tokenList = list;
		index = 0;
	}
	
	private Word getNextWord(){
		if(index<tokenList.size()){
			WordResult rs = tokenList.get(index);
			index++;
			return rs.getWord();
		}else{
			return new Word(Dictionary.SENTENCE_END_SPELLING,null,true);
		}
	}
	
	private void executeOpenCommand(){
		Opener opener = new WindowsOpener();
		UrlOpener urlOpener = new SimpleUrlOpener();
		
		Word word = getNextWord();
		if("qq".equals(word.getSpelling())){
			opener.openQQ();
		}else if("酷狗".equals(word.getSpelling())){
			opener.openKuGou();
		}else if("谷歌".equals(word.getSpelling())){
			
			word = getNextWord();
			if("浏览器".equals(word.getSpelling())){
				opener.openBrowser();
			}else{
				
			}
		}else if("记事本".equals(word.getSpelling())){
			opener.openNotepad();			
		}else if("回收站".equals(word.getSpelling())){
			opener.openTrash();
		}else if("我的电脑".equals(word.getSpelling())){
			opener.openMyPC();
		}else if("淘宝".equals(word.getSpelling())){
			
			word = getNextWord();
			if("首页".equals(word.getSpelling())){
				urlOpener.openUrl("http://www.taobao.com/");
			}
		}else if("百度".equals(word.getSpelling())){
			word = getNextWord();
			urlOpener.openUrl("http://www.baidu.com/");
			if("首页".equals(word.getSpelling())){
				word = getNextWord();
			}
		}else if("京东".equals(word.getSpelling())){
			word = getNextWord();
			if("首页".equals(word.getSpelling())){
				urlOpener.openUrl("http://www.jd.com/");
			}
		}else if("开始菜单".equals(word.getSpelling())){
			AdvancedController c = new WindowsAdvancedController();
			c.clickStartMenu();
		}
	}
	
	private void executeCloseCommand(){
		Closer closer = new WindowsCloser();
		
		Word word = getNextWord();
		if("当前".equals(word.getSpelling())){
			
			word = getNextWord();
			if("窗口".equals(word.getSpelling())){
				closer.closeCurrentWindow();
			}else{
				
			}
		}else{
			
		}
	}
	
	private void executeMinimizeCommand(){
		Minimizer minimizer = new WindowsMinimizer();
		
		Word word = getNextWord();
		if("当前".equals(word.getSpelling())){
			word = getNextWord();
			if("窗口".equals(word.getSpelling())){
				minimizer.minimize();
			}else{
				
			}
		}else if("所有".equals(word.getSpelling())){
			word = getNextWord();
			if("的".equals(word.getSpelling())){
				word = getNextWord();
			}
			
			if("窗口".equals(word.getSpelling())){
				minimizer.minimizeAll();
			}
		}else{
			
		}
	}
	
	public void executeConfig(String current){
		AdvancedController controller = new WindowsAdvancedController();
		
		Word word = getNextWord();
		if("电脑".equals(word.getSpelling())){
			word = getNextWord();
		}
		
		if("静音".equals(word.getSpelling())){
			if(current.equals("设置")){
				controller.setMute();
			}else if(current.equals("取消")){
				controller.cancelMute();
			}else{
				
			}
		}else{
			
		}
	}
	
	public void executeQuery(){
		Word word = getNextWord();
		UrlOpener opener = new SimpleUrlOpener();
		
		if("下".equals(word.getSpelling())){
			word = getNextWord();
		}
		
		if("明天".equals(word.getSpelling())){
			word = getNextWord();
			if("的".equals(word.getSpelling())){
				word = getNextWord();
			}
		}
		
		if("天气预报".equals(word.getSpelling()) || "天气".equals(word.getSpelling())){
			
			opener.openUrl("https://www.baidu.com/s?wd=天气预报");
		}
		
		if("日历".equals(word.getSpelling())){
			opener.openUrl("https://www.baidu.com/s?wd=日历");
		}
	}
	
	private void executeClean(){
		Word word = getNextWord();
		
		if("回收站".equals(word.getSpelling())){
			AdvancedController c = new WindowsAdvancedController();
			c.emptyTrash();
		}
	}
	
	private void executeLocalSearch(){
		Word word = getNextWord();
		
		if("文件".equals(word.getSpelling())){
			word = getNextWord();
			
			AdvancedController c = new WindowsAdvancedController();
			String keyword = word.getSpelling();
			if(word.isFiller()){
				keyword = "";
			}
			c.searchFile(keyword);
		}
	}
	
	public void execute(){
		Word word;
		while((word = getNextWord())!=null && !word.isSentenceEndWord()){
			if("打开".equals(word.getSpelling())){
				executeOpenCommand();
			}else if("关闭".equals(word.getSpelling())){				
				executeCloseCommand();
			}else if("最小化".equals(word.getSpelling())){				
				executeMinimizeCommand();
			}else if("设置".equals(word.getSpelling()) || "取消".equals(word.getSpelling())){
				executeConfig(word.getSpelling());
			}else if("查询".equals(word.getSpelling()) || "查下".equals(word.getSpelling())){
				executeQuery();
			}else if("清空".equals(word.getSpelling())){
				executeClean();
			}else if("搜索".equals(word.getSpelling())){
				executeLocalSearch();
			}else if("显示".equals(word.getSpelling())){
				word = getNextWord();
				if("桌面".equals(word.getSpelling())){
					Minimizer minimizer = new WindowsMinimizer();
					minimizer.minimizeAll();
				}
			}else if("点击".equals(word.getSpelling())){
				word = getNextWord();
				if("开始菜单".equals(word.getSpelling())){
					AdvancedController c = new WindowsAdvancedController();
					c.clickStartMenu();
				}
			}else{
				continue;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
	}
}
