import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.*;

public class TextParser_Regex {

    public LinkedList<String> getQuestionSenteces(String str){
        LinkedList<String> questionSentences = new LinkedList<String>();
        Pattern pattern = Pattern.compile("[^.?!]+\\?");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            questionSentences.add(matcher.group().trim());
        }
        return questionSentences;
    }
    public HashSet<String> getWordsWithDefinedLength(int length, String str){
        HashSet<String> words = new HashSet<String>();
        String spattern = String.format("\\b[^\\s]{%d}+\\b", length);//String.format("[\\b[^\\s]+\\b]", length);
        Pattern pattern = Pattern.compile(String.format(spattern), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            words.add(matcher.group().toLowerCase());
        }
        return words;
    }

}