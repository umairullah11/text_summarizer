package summariser;

public class SentencePosition { 
	public static int beginningIndex = 0;
	public static int endingIndex = 0;
	
	static {
		Sentence lastSentence = Main.sentences.get(Main.sentences.size() - 1);
		int sentenceEndPos = lastSentence.getSentenceEndPos();
		beginningIndex = (int) (sentenceEndPos * 0.333);
		endingIndex = (int) (sentenceEndPos * 0.666);
	}
	
	public static int calculateSentencePostionScore(int startPos) {
		int sentencePostionScore = 0;
		if(startPos < beginningIndex) {
			sentencePostionScore = 3;
		} else if(startPos >= beginningIndex && startPos <= endingIndex) {
			sentencePostionScore = 2;
		} else if(startPos > endingIndex) {
			sentencePostionScore = 1;
		}
		return sentencePostionScore;
	}
}
	
