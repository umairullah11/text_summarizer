package summariser;
import java.util.List;

public class ClueWords {
	
	public static int calculateClueWordsScore(List<String> words) {
		if(words != null && words.size() > 0) {
			int cluewordsCount = 0;
			int clueWordsScrore = 0;
			//Calculating wordFrequency Score
			for(String word : words) {
				if(Main.globalClueWords.contains(word)) {
					cluewordsCount++;
				}
			}
			//calculating clueWords score
			if(cluewordsCount >= 2) {
				clueWordsScrore = 2;
			} else if(cluewordsCount == 1) {
				clueWordsScrore = 1;
			} else if( cluewordsCount <= 0) {
				clueWordsScrore = 0;
			}
			
			return clueWordsScrore;
		} else {
			return 0;
		}
	}
}
