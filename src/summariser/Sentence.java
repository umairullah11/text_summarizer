package summariser;

import java.util.ArrayList;
import java.util.List;

class Sentence{
	List<String> words;
	int sentenceStartPos;
	int sentenceEndPos;
	int textFrequencyScore;
	int sentencePostionScore;
	int clueWordsScrore;
	int negativeKeywordsScore;
	int paragraphNumber;
	int number;
	int stringLength; //Dont need this 
	double score;
	int noOfWords;
	String value;

	Sentence(int number, String value, int stringLength, int paragraphNumber){
		this.number = number;
		this.value = new String(value);
		this.stringLength = stringLength;
		noOfWords = value.split("\\s+").length;
		score = 0.0;
		this.paragraphNumber=paragraphNumber;
	}
	
	Sentence(List<String> words, int sentenceStartPos, int sentenceEndPos) {
		this.words = words;
		this.sentenceStartPos = sentenceStartPos;
		this.sentenceEndPos = sentenceEndPos;
	}

	public void calculateScores() {
		if(this.words != null && this.words.size() > 0) {
			int wordsFrequencyScore = 0;
			int cluewordsCount = 0;
			int negativeWordsCount = 0;
			for(String word :this.words) {
				//text frequency score 
				wordsFrequencyScore += Main.wordFrequencyMap.get(word) == null? 0 : Main.wordFrequencyMap.get(word);
				
				if(Main.globalClueWords.contains(word)) {
					cluewordsCount++;
				}
				if(Main.globalClueWordsNegative.contains(word)) {
					negativeWordsCount++;
				}
			}
			
			int frequencyScore = wordsFrequencyScore / this.words.size();
			if(frequencyScore > 50) {
				this.textFrequencyScore = 3;
			} else if(frequencyScore > 20 && frequencyScore <=50) {
				this.textFrequencyScore = 2;
			} else if(frequencyScore <= 20 ) {
				this.textFrequencyScore = 1;
			}
			
			int sum = this.sentenceStartPos + this.sentenceEndPos;
			if(sum < 1200) {
				this.sentencePostionScore = 3;
			} else if(sum >= 1200 && sum <= 1900) {
				this.sentencePostionScore = 2;
			} else if(sum > 1900) {
				this.sentencePostionScore = 1;
			}
			
			if(cluewordsCount >= 2) {
				this.clueWordsScrore = 2;
			} else if(cluewordsCount == 1) {
				this.clueWordsScrore = 1;
			} else if( cluewordsCount <= 0) {
				this.clueWordsScrore = 0;
			}
			
			if(negativeWordsCount >= 2) {
				this.clueWordsScrore = -2;
			} else if(negativeWordsCount == 1) {
				this.clueWordsScrore = -1;
			} else if( negativeWordsCount <= 0) {
				this.clueWordsScrore = 0;
			}
			
			System.out.println( this.toString());
		}
		
	}

	@Override
	public String toString() {
		return "Sentence [sentenceStartPos=" + sentenceStartPos + ", sentenceEndPos=" + sentenceEndPos
				+ ", textFrequencyScore=" + textFrequencyScore + ", sentencePostionScore=" + sentencePostionScore
				+ ", clueWordsScrore=" + clueWordsScrore + ", negativeKeywordsScore=" + negativeKeywordsScore
				+ ", stringLength=" + stringLength + ", noOfWords=" + noOfWords + "]";
	}
}