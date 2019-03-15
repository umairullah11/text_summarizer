package summariser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.tokenize.TokenizerME; 
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.io.FileUtils;


public class Tokenizer {



	public ArrayList<String> Words () {

		ArrayList<String> words_output = new ArrayList<String>();


		try {

			String text = FileUtils.readFileToString(new File("data\\Arsenal 4-1 Fulham Hosts start New Year with victory to close gap on top four(Tue 1st January).txt"));

			String stopWordsPattern = String.join("|", Constants.stopWords);
			Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(text);
			text = matcher.replaceAll("");

			//Loading the Tokenizer model 

			InputStream inputStream = new FileInputStream("data\\en-token.bin"); 
			TokenizerModel tokenModel = new TokenizerModel(inputStream); 



			//Instantiating the TokenizerME class 
			TokenizerME tokenizer = new TokenizerME(tokenModel); 

			//Tokenizing the given raw text 
			String tokens[] = tokenizer.tokenize(text);       
			

			//Printing the tokens  
			for (String a : tokens) 
				words_output.add(("\n" + a)); 
		}   
		catch (IOException e) {  
			e.printStackTrace();
		}
		return words_output; 

	}
} 

