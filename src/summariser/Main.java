package summariser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import opennlp.tools.util.StringUtil;

public class Main {

	public static ArrayList<Sentence> sentences = new ArrayList<Sentence>();
	public static Map<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();
	public static List<String> globalClueWords = Arrays.asList(Constants.key_wrd);
	public static List<String> globalClueWordsNegative = Arrays.asList(Constants.key_wrd_neg);

	public static void main(String args[]) throws Exception {
		// Creating objects

		SentenceDetectionME sd = new SentenceDetectionME();
		Tokenizer tkn = new Tokenizer();

		// Getting file path from args
		String filePath = "";
		if(args.length > 0) {
			filePath = args[0];
		}
		if (StringUtil.isEmpty(filePath)) {
			filePath = "data\\Arsenal 4-1 Fulham Hosts start New Year with victory to close gap on top four(Tue 1st January).txt";
		}
		
		//txt extension necessary
		String text = FileUtils.readFileToString(new File(filePath));

		// Array lists and toString

		sentences = sd.Sentence(text);
		wordFrequencyMap = tkn.Words(text, true);

		sentences.stream().forEach(sent -> {
			sent.calculateScores();
		});

		//Sorting the sentences on the basis of their score
		sentences.sort(Comparator.comparing(Sentence::getScore).reversed());

		// Filter top 40% sentences
		int numOfElements = (int) (sentences.size() * 0.4);
		List<Sentence> topScoredSenteces = sentences.stream().limit(numOfElements).collect(Collectors.toList());

		// Ordering them according to their start position
		topScoredSenteces.sort(Comparator.comparing(Sentence::getSentenceStartPos));

		//Printing the summary
		topScoredSenteces.stream().forEach(sent -> System.out.println(sent.getValue().trim()));

	}
}
