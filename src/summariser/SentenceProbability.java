package summariser;

import java.util.List;

public class SentenceProbability {
	
	public static int calculateSentenceProbabilityScore(List<String> words) {
		if(words != null && words.size() > 0) {
			int wordsFrequencyScore = 0;
			int sentenceProbabilityScore = 0;
			//Calculating wordFrequency Score
			for(String word : words) {
				//text frequency score 
				wordsFrequencyScore += Main.wordFrequencyMap.get(word) == null? 0 : Main.wordFrequencyMap.get(word);
			}
			//calculating sentence probability score
			int frequencyScore = wordsFrequencyScore / words.size();
			if(frequencyScore > 50) {
				sentenceProbabilityScore = 3;
			} else if(frequencyScore > 20 && frequencyScore <=50) {
				sentenceProbabilityScore = 2;
			} else if(frequencyScore <= 20 ) {
				sentenceProbabilityScore = 1;
			}
			
			return sentenceProbabilityScore;
		} else {
			return 0;
		}
	}

}
