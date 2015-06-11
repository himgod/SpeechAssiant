package cn.edu.xidian.main;

import java.io.IOException;

import cn.edu.xidian.impl.SematicAnalyzerPutian;
import cn.edu.xidian.impl.SematicAnalyzerPutong;
import cn.edu.xidian.interfaces.SematicAnalyzer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Main {

	LiveSpeechRecognizer recognizer;
	SematicAnalyzer analyzer;

	public Main() throws IOException{
		System.out.println("Loading models...");

		putonghua();
	}
	
	void putonghua() throws IOException{
		Configuration configuration = new Configuration();

		// Load model from the jar
		configuration
		.setAcousticModelPath("resource:/cn/edu/xidian/resource/zh-cn/speech_assistant.cd_cont_200");
		configuration
		.setDictionaryPath("resource:/cn/edu/xidian/resource/zh-cn/speech_assistant.dic");
		//configuration
		//.setLanguageModelPath("resource:/cn/edu/xidian/resource/zh-cn/speech_assistant.lm.dmp");
		configuration
		.setGrammarPath("resource:/cn/edu/xidian/resource/zh-cn/");
		configuration.setUseGrammar(true);
		configuration.setGrammarName("speech_assistant");

		recognizer = new LiveSpeechRecognizer(configuration);
		analyzer = new SematicAnalyzerPutong();
	}
	
	void putianhua() throws IOException{
		Configuration configuration = new Configuration();

		// Load model from the jar
		configuration
		.setAcousticModelPath("resource:/cn/edu/xidian/resource/putian/native_assistant.cd_cont_30");
		configuration
		.setDictionaryPath("resource:/cn/edu/xidian/resource/putian/native_assistant.dic");
		//configuration
		//.setLanguageModelPath("resource:/cn/edu/xidian/resource/zh-cn/speech_assistant.lm.dmp");
		configuration
		.setGrammarPath("resource:/cn/edu/xidian/resource/putian/");
		configuration.setUseGrammar(true);
		configuration.setGrammarName("native_assistant");

		recognizer = new LiveSpeechRecognizer(configuration);
		analyzer = new SematicAnalyzerPutian();
	}

	public void start() {

		// Simple recognition with generic model
		recognizer.startRecognition(true);
		SpeechResult result;
		while (true) {
			result = recognizer.getResult();
			String line = result.getHypothesis();
			System.out.format("Hypothesis: %s\n", line);
			
			if("关".equals(result.getHypothesis())){
				break;
			}

			analyzer.setTokenList(result.getWords());
			analyzer.execute();
		}
		recognizer.stopRecognition();
	}

	public static void main(String[] args){
		try {
			Main m = new Main();
			m.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
