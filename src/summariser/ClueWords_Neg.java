package summariser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.*;

public class ClueWords_Neg {



    public static void main(String [] args){
        try {
        	
        	
         BufferedReader br1 = new BufferedReader(new FileReader("data\\Arsenal 4-1 Fulham Hosts start New Year with victory to close gap on top four(Tue 1st January).txt"));
         
    
            String word_re = Constants.key_wrd_neg[0];   
            String str="";

            for (int i = 1; i < Constants.key_wrd_neg.length; i++)
                word_re += "|" + Constants.key_wrd_neg[i];
            word_re = "[^.]*\\b(" + word_re + ")\\b[^.]*[.]";
            while(br1.ready()) { str += br1.readLine(); }
            Pattern re = Pattern.compile(word_re, 
                    Pattern.MULTILINE | Pattern.COMMENTS | 
                    Pattern.CASE_INSENSITIVE);
            Matcher match = re.matcher(str);
            String sentenceString="";
            while (match .find()) {
                sentenceString = match.group(0);
                System.out.println(sentenceString);
            }
        } catch (Exception e) {}
        
        
        
    }

}