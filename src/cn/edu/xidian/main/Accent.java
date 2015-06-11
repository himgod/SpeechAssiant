package cn.edu.xidian.main;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Accent {

	LiveSpeechRecognizer recognizer;
	
	public Accent() throws IOException{
		System.out.println("Loading models...");
		
		Configuration configuration = new Configuration();
		
		configuration
		.setAcousticModelPath("file:///f:/model_accent/native_accent.cd_cont_200");
		configuration
		.setDictionaryPath("file:///F:/model_accent/native_accent.dic");
		configuration
		.setLanguageModelPath("file:///F:/model_accent/native_accent.lm.dmp");
//		configuration
//		.setGrammarPath("resource:/cn/edu/xidian/res/");
//		configuration.setUseGrammar(true);
//		configuration.setGrammarName("native_accent");

		recognizer = new LiveSpeechRecognizer(configuration);
	}
	
	public void start() {

		// Simple recognition with generic model
		recognizer.startRecognition(true);
		SpeechResult result;
		while (true) {
			result = recognizer.getResult();
			String line = result.getHypothesis();
			System.out.format("Hypothesis: %s\n", line);
			
			if("关".equals(result.getHypothesis()))
	        {
				break;
			}
		}
		recognizer.stopRecognition();
	}
	
	public static void main(String[] args){
		try {
			Accent m = new Accent();
			m.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
