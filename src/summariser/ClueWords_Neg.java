package summariser;
import java.util.List;

public class ClueWords_Neg {

	public static int calculateNegativeClueWordsScore(List<String> words) {
		if(words != null && words.size() > 0) {
			int negativeWordsCount = 0;
			int negativeKeywordsScore = 0;
			//Calculating wordFrequency Score
			for(String word : words) {
				if(Main.globalClueWordsNegative.contains(word)) {
					negativeWordsCount++;
				}
			}
			//calculating negative keywords score
			if(negativeWordsCount >= 2) {
				negativeKeywordsScore = -2;
			} else if(negativeWordsCount == 1) {
				negativeKeywordsScore = -1;
			} else if( negativeWordsCount <= 0) {
				negativeKeywordsScore = 0;
			}
			return negativeKeywordsScore;
		} else {
			return 0;
		}
	}
}