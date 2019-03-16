package summariser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import summariser.Constants;
import org.apache.commons.io.FileUtils;

import opennlp.tools.sentdetect.SentenceDetectorME; 
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span; 

public class SentenceDetectionME { 
	

	public ArrayList<String> Sentence (String filePath) {

		ArrayList<String> sent_output = new ArrayList<String>();
		try {
			String text = FileUtils.readFileToString(new File(filePath));
			 //txt extension necessary

			/*String stopWordsPattern = String.join("|", Constants.stopWords);
			Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(text);
			text = matcher.replaceAll("");*/
			List<String> stopWords = Arrays.asList(Constants.stopWords);

			//Loading the Tokenizer model 

			InputStream inputStreamToken = new FileInputStream("data\\en-token.bin"); 
			TokenizerModel tokenModel = new TokenizerModel(inputStreamToken); 

			//Loading a sentence model 
			InputStream inputStream = new FileInputStream("data\\en-sent.bin"); 
			SentenceModel model = new SentenceModel(inputStream); 

			//Instantiating the SentenceDetectorME class 
			SentenceDetectorME detector = new SentenceDetectorME(model);  

			//Detecting the position of the sentences in the paragraph  
			String sentences[] = detector.sentDetect(text); 
			Span[] spans = detector.sentPosDetect(text);
			
			//Printing the sentences and their spans of a paragraph
			int sentenceNo = 0;
			for(String sent : sentences) {
				//Instantiating the TokenizerME class 
				TokenizerME tokenizer = new TokenizerME(tokenModel); 

				//Tokenizing the given raw text 
				String tokens[] = tokenizer.tokenize(sent); 
				if(tokens != null && tokens.length > 0) {
					ArrayList<String> words = new ArrayList<String>();
					for(int i = 0; i < tokens.length; i++) {
						String word = tokens[i];
						if (stopWords.contains(tokens[i]))
							continue;
						
						if (Main.wordFrequencyMap.containsKey(word)) {
							Integer count = Main.wordFrequencyMap.get(word);
							Main.wordFrequencyMap.replace(word, ++count);
						} else {
							Main.wordFrequencyMap.put(word, 1);
						}
						
						words.add(word);
					}
				}
				//System.out.println(text.substring(span.getStart(), span.getEnd())+" "+ span);
				Main.sentences.add(new Sentence(Arrays.asList(tokens), spans[sentenceNo].getStart(), spans[sentenceNo].getEnd(), sent.trim()));
				sent_output.add(" \n" + sent);
				sentenceNo++;
			}       
		}

			catch (IOException e) {  
				e.printStackTrace();
		}
		
		return sent_output; 

	}
	
}
	
