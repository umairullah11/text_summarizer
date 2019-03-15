package summariser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Main {
	
	public static ArrayList<Sentence> sentences = new ArrayList<Sentence>();
	public static Map<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();
	public static List<String> globalClueWords = Arrays.asList(Constants.key_wrd);
	public static List<String> globalClueWordsNegative = Arrays.asList(Constants.key_wrd_neg);
	public static void main(String args[]) throws Exception{

		

		// Creating objects
		
		SentenceDetectionME sd = new SentenceDetectionME();
		Tokenizer tkn = new Tokenizer();
		SentencePosition sp = new SentencePosition();
		WordFrequencyCorpus wrdfrq = new WordFrequencyCorpus();

		// Array lists and toString
		
		ArrayList<String> sentenceDetectionOutput = sd.Sentence();
		
		for(Sentence sent : sentences) {
			sent.calculateScores();
		}
		
		
		/*String sentenceDetection = sentenceDetectionOutput.toString();

		ArrayList<String> wordsDetectionOutput = tkn.Words();
		String wordsDetection = wordsDetectionOutput.toString();
		
		ArrayList<String> sentencePositionOutput = sp.Sentence();
		String sentencePosition = sentencePositionOutput.toString();

		Map<String, Integer> FrequencyDetectionOutput = wrdfrq.WordFrequency();
		String frequencyDetection = wordsDetectionOutput.toString();
		
		

		// Printing

		System.out.println("Sentence Segmentation");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println(sentenceDetectionOutput);
		System.out.println("");

		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Tokenization");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println(wordsDetectionOutput);
		System.out.println("");

		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Sentence Position");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println(sentencePositionOutput);
		System.out.println("");
		
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Word Frequency Corpus Data");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println(FrequencyDetectionOutput);*/

	}
}
