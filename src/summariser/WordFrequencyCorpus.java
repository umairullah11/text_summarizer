package summariser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class WordFrequencyCorpus {

	public Map<String, Integer> WordFrequency() {

		ArrayList<String> frequency_output = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();

		try {

			// Scanner scanner = new Scanner(new File("PUT YOUR PATH HERE\\data
			// corpus.txt")).useDelimiter("[^a-zA-Z]+");
			Scanner scanner = new Scanner(new File("data\\data corpus.txt")).useDelimiter("[^a-zA-Z]+");

			String stopWordsPattern = String.join("|", Constants.stopWords);
			Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
			List<String> stopWords = Arrays.asList(Constants.stopWords);

			while (scanner.hasNext()) {
				String word = scanner.next();

				if (stopWords.contains(word))
					continue;

				if (map.containsKey(word)) {
					Integer count = map.get(word);
					map.replace(word, ++count);
				} else {
					map.put(word, 1);
				}
			}

			List<Map.Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());

			Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
					return a.getValue().compareTo(b.getValue());
				}
			});

			for (int i = 0; i < map.size(); i++) {
				frequency_output.add("\n" + entries.get(entries.size() - i - 1).getKey() + " "
						+ entries.get(entries.size() - i - 1).getValue() + " time(s)");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}