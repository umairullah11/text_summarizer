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
	public static int beginningIndex = 0;
	public static int endingIndex = 0;

	public static void main(String args[]) throws Exception {
		// Creating objects

		SentenceDetectionME sd = new SentenceDetectionME();
		Tokenizer tkn = new Tokenizer();
		SentencePosition sp = new SentencePosition();
		WordFrequencyCorpus wrdfrq = new WordFrequencyCorpus();

		// Getting file path from args
		String filePath = "";
		if(args.length > 0) {
			filePath = args[0];
		}
		if (StringUtil.isEmpty(filePath)) {
			filePath = "data\\data corpus.txt";
		}

		// Array lists and toString

		ArrayList<String> sentenceDetectionOutput = sd.Sentence(filePath);
		Sentence lastSentence = sentences.get(sentences.size() - 1);
		int sentenceEndPos = lastSentence.getSentenceEndPos();
		beginningIndex = (int) (sentenceEndPos * 0.333);
		endingIndex = (int) (sentenceEndPos * 0.666);

		sentences.stream().forEach(sent -> {
			sent.calculateScores();
		});

		//Sorting the sentences on the basis of their score
		sentences.sort(Comparator.comparing(Sentence::getScore));

		// Filter top 20% sentences
		int numOfElements = (int) (sentences.size() * 0.20);
		List<Sentence> topScoredSenteces = sentences.stream().limit(numOfElements).collect(Collectors.toList());

		// Ordering them according to their start position
		topScoredSenteces.sort(Comparator.comparing(Sentence::getSentenceStartPos));

		//Printing the summary
		topScoredSenteces.stream().forEach(sent -> System.out.println(sent.getValue().trim()));

	}
}
