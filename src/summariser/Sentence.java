package summariser;

import java.util.List;

class Sentence{
	List<String> words;
	int sentenceStartPos;
	int sentenceEndPos;
	int number;
	int score;
	int noOfWords;
	String value;

	Sentence(List<String> words, int sentenceStartPos, int sentenceEndPos, String value) {
		this.words = words;
		this.sentenceStartPos = sentenceStartPos;
		this.sentenceEndPos = sentenceEndPos;
		this.value = value;
	}

	public void calculateScores() {
		if(this.words != null && this.words.size() > 0) {
			this.noOfWords = this.words.size();
			// calculate the final score by multiplying their individual scores with weights
			this.score = (2 * SentenceProbability.calculateSentenceProbabilityScore(words))
					+ (3 * SentencePosition.calculateSentencePostionScore(sentenceStartPos))
					+ (4 * ClueWords.calculateClueWordsScore(words)) + (4 * ClueWords_Neg.calculateNegativeClueWordsScore(words));
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
				+ ", textFrequencyScore=" + SentenceProbability.calculateSentenceProbabilityScore(words)
				+ ", sentencePostionScore="
				+ SentencePosition.calculateSentencePostionScore(sentenceStartPos)
				+ ", clueWordsScrore=" + ClueWords.calculateClueWordsScore(words) + ", negativeKeywordsScore="
				+ ClueWords_Neg.calculateNegativeClueWordsScore(words) + ", noOfWords=" + noOfWords + ", score=" + score
				+ "]";
	}
}