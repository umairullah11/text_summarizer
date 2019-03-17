package summariser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span; 

public class SentenceDetectionME { 
	

	public ArrayList<Sentence> Sentence (String text) {

		ArrayList<Sentence> sent_output = new ArrayList<Sentence>();
		try {

			//Loading a sentence model 
			InputStream inputStream = new FileInputStream("data\\en-sent.bin"); 
			SentenceModel model = new SentenceModel(inputStream); 

			//Instantiating the SentenceDetectorME class 
			SentenceDetectorME detector = new SentenceDetectorME(model);  

			//First Detecting the position of the sentences in the paragraph without stop words removal  
			String sentences[] = detector.sentDetect(text); 
			
			//Removing stop words
			String stopWordsPattern = String.join("|", Constants.stopWords);
			Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(text);
			text = matcher.replaceAll("");
			
			//Now Detecting the position of the sentences in the paragraph without stop words removal  
			String sentencesWithStopWordsRemoved[] = detector.sentDetect(text); 
			Span[] spans = detector.sentPosDetect(text);
			
			//Printing the sentences and their spans of a paragraph
			int sentenceNo = 0;
			for(String sent : sentencesWithStopWordsRemoved) {
				String[] tokens = sent.split(" ");
				ArrayList<String> words = new ArrayList<String>();
				if(tokens != null && tokens.length > 0) {
					for(int i = 0; i < tokens.length; i++) {
						String word = tokens[i];
						words.add(word);
					}
				}
				//System.out.println(text.substring(span.getStart(), span.getEnd())+" "+ span);
				sent_output.add(new Sentence(words, spans[sentenceNo].getStart(), spans[sentenceNo].getEnd(), sentences[sentenceNo].trim()));
				sentenceNo++;
			}       
		}

			catch (IOException e) {  
				e.printStackTrace();
		}
		
		return sent_output; 

	}
	
}
	
