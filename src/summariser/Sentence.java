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
	int score;
	int noOfWords;
	String value;

	Sentence(int number, String value, int stringLength, int paragraphNumber){
		this.number = number;
		this.value = new String(value);
		this.stringLength = stringLength;
		noOfWords = value.split("\\s+").length;
		score = 0;
		this.paragraphNumber=paragraphNumber;
	}
	
	Sentence(List<String> words, int sentenceStartPos, int sentenceEndPos, String value) {
		this.words = words;
		this.sentenceStartPos = sentenceStartPos;
		this.sentenceEndPos = sentenceEndPos;
		this.value = value;
	}

	public void calculateScores() {
		if(this.words != null && this.words.size() > 0) {
			int wordsFrequencyScore = 0;
			int cluewordsCount = 0;
			int negativeWordsCount = 0;
			this.noOfWords = this.words.size();
			
			//Calculating wordFrequency Score, cluewords Count, negative words count
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
			
			//calculating sentence probability score
			int frequencyScore = wordsFrequencyScore / this.words.size();
			if(frequencyScore > 50) {
				this.textFrequencyScore = 3;
			} else if(frequencyScore > 20 && frequencyScore <=50) {
				this.textFrequencyScore = 2;
			} else if(frequencyScore <= 20 ) {
				this.textFrequencyScore = 1;
			}
			
			//calculating sentence position score
			int sum = this.sentenceStartPos + this.sentenceEndPos;
			if(sum < Main.beginningIndex) {
				this.sentencePostionScore = 3;
			} else if(sum >= Main.beginningIndex && sum <= Main.endingIndex) {
				this.sentencePostionScore = 2;
			} else if(sum > Main.endingIndex) {
				this.sentencePostionScore = 1;
			}
			
			//calculating clueWords score
			if(cluewordsCount >= 2) {
				this.clueWordsScrore = 2;
			} else if(cluewordsCount == 1) {
				this.clueWordsScrore = 1;
			} else if( cluewordsCount <= 0) {
				this.clueWordsScrore = 0;
			}
			
			//calculating negative keywords score
			if(negativeWordsCount >= 2) {
				this.negativeKeywordsScore = -2;
			} else if(negativeWordsCount == 1) {
				this.negativeKeywordsScore = -1;
			} else if( negativeWordsCount <= 0) {
				this.negativeKeywordsScore = 0;
			}
			
			// calculate the final score by multiplying their individual scores with weights
			this.score = (2 * textFrequencyScore) + (3 * sentencePostionScore) + (4 * clueWordsScrore) + (4 * negativeKeywordsScore);
			System.out.println( this.toString());
		}
		
	}

	public int getSentenceStartPos() {
		return sentenceStartPos;
	}

	public int getSentenceEndPos() {
		return sentenceEndPos;
	}


	public int getScore() {
		return score;
	}

	public int getNoOfWords() {
		return noOfWords;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Sentence [sentenceStartPos=" + sentenceStartPos + ", sentenceEndPos=" + sentenceEndPos
				+ ", textFrequencyScore=" + textFrequencyScore + ", sentencePostionScore=" + sentencePostionScore
				+ ", clueWordsScrore=" + clueWordsScrore + ", negativeKeywordsScore=" + negativeKeywordsScore
				+ ", noOfWords=" + noOfWords + ", score=" + score + "]";
	}
}