package summariser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokenizer {

	public Map<String, Integer> Words(String text, boolean useCompleteCorpusFile) {

		Map<String, Integer> words_output = new HashMap<String, Integer>();

		try {
			if(useCompleteCorpusFile) {
				text = FileUtils.readFileToString(new File("data\\data corpus.txt"));
			}

			String stopWordsPattern = String.join("|", Constants.stopWords);
			Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(text);
			text = matcher.replaceAll("");
			
			Scanner scanner = new Scanner(text).useDelimiter("[^a-zA-Z]+");

			// Loading the Tokenizer model

			InputStream inputStream = new FileInputStream("data\\en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(inputStream);

			// Instantiating the TokenizerME class
			TokenizerME tokenizer = new TokenizerME(tokenModel);

			while (scanner.hasNext()) {
				String word = scanner.next();

				if (words_output.containsKey(word)) {
					Integer count = words_output.get(word);
					words_output.replace(word, ++count);
				} else {
					words_output.put(word, 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words_output;

	}
}
