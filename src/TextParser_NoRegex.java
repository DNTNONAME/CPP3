
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TextParser_NoRegex {
    private boolean containsBrackets(String str){
        char opening = '«', closing = '»';
        return str.indexOf(opening) != -1;
    }

    private boolean areAllBracketsCorrect(String str){
        char opening = '«', closing = '»';
        int counter = 0;
        for (char ch : str.toCharArray()){
            if (ch == opening)
                counter++;
            else if (ch == closing)
                counter--;
            if (counter < 0)
                return false;
        }
        return counter == 0;
    }

    private Integer[] getStringInBrackets(String str){
        char opening = '«', closing = '»';
        Integer opos = str.indexOf(opening);
        Integer count = 0;
        Integer it = 0;
        for (char ch : str.substring(opos).toCharArray()) {
            if ( ch == opening)
                count++;
            else if (ch == closing)
                count--;
            if (count == 0)
                break;
            it++;
        }
        return new Integer[]{opos+1, it+opos};
    }

    private LinkedList<String> _getStringsInBrackets(String str){
        LinkedList<String> list = new LinkedList<String>();
        Integer[] borders;
        String temp = "";
        Integer startPos = 0;
        while (true){
            if (containsBrackets(str.substring(startPos))) {
                borders = getStringInBrackets(str.substring(startPos));
                temp = str.substring(borders[0]+startPos, borders[1]+startPos);
                System.out.println(temp);
                list.addAll(getStringsInBrackets(temp));
                startPos = borders[1]+startPos;
                if (startPos > str.length())
                    break;
                else
                    list.add(temp);
            }
            else
                break;
        }
        return list;
    }

    public LinkedList<String> getStringsInBrackets(String str){
        if(areAllBracketsCorrect(str))
            return _getStringsInBrackets(str);
        else
            return null;
    }

    public String deleteExcessiveSpaces(String str){
        String space = " ";
        char[] chArr = str.toCharArray();
        String[] words = str.split(space);
        String recovered = "";
        for (String word: words){
            if (word.equals(",") || word.equals("."))
                recovered+= word;
            else if(word.equals(""))
                continue;
            else
                recovered += " " + word;
        }
        recovered = recovered.trim();
        return recovered;
    }

    private String getClearText(String str){
        str = str.replace(",", ""); // change for more optimal
        str = str.replace(".", ""); // change for more optimal
        str = str.replace("?", ""); // change for more optimal
        str = str.replace("!", ""); // change for more optimal
        str = str.replace(":", ""); // change for more optimal
        str = str.replace(";", ""); // change for more optimal
        str = str.toLowerCase();
        return str;
    }

    public String getMostCommonWord(String str){
        str = getClearText(str);
        String[] words = str.split(" ");
        HashMap<String, Integer> uniqueWords = new HashMap<String, Integer>();
        for(String word: words){
            if(uniqueWords.containsKey(word))
                uniqueWords.replace(word, uniqueWords.get(word)+1);
            else
                uniqueWords.put(word, 1);
        }
        String maxVal = null;
        Integer maxC = 0;
        for(Map.Entry<String, Integer> pair : uniqueWords.entrySet()){
            if (pair.getValue() > maxC) {
                maxC = pair.getValue();
                maxVal = pair.getKey();
            }
        }
        return maxVal;
    }

    private boolean compareWordWithWords(String word, LinkedList<String> wordsToBeReplaced){
        String wordClearCopy = getClearText(word);
        for (String w : wordsToBeReplaced){
            if(w.equals(wordClearCopy)){
                return true;
            }
        }
        return false;
    }

    private String[] splitWithMarks(String str){
        String[] words = str.split(" ");
        LinkedList<String> wordList = new LinkedList<String>();
        for (String word: words){
            if (word.toCharArray()[word.length()-1] == ','||
                    word.toCharArray()[word.length()-1] == '.' ||
                    word.toCharArray()[word.length()-1] == ':' ||
                    word.toCharArray()[word.length()-1] == ';'){
                wordList.add(word.substring(0, word.length()-1));
                wordList.add(word.substring(word.length()-1));
            }
            else wordList.add(word);
        }
        String[] arr = new String[wordList.size()];
        return wordList.toArray(arr);
    }

    private String gatherWordsInText(String[] words){
        String str=new String();
        for (String word : words){
            if (!word.equals(",") && !word.equals(".") && !word.equals(";") &&
                    !word.equals(":"))
                str += " ";
            str+= word;
        }
        str = str.substring(1);
        return str;
    }

    public String replaceWordsToMostCommon(String str, LinkedList<String> wordsToBeReplaced){
        String mostCommon = getMostCommonWord(str);
        String[] words = splitWithMarks(str);
        String[] wordsCopy = new String[words.length];
        int i = 0;
        for (String word : words){
            if(compareWordWithWords(word, wordsToBeReplaced)){
                wordsCopy[i] = mostCommon;
            }
            else wordsCopy[i] = word;
            i++;
        }
        return gatherWordsInText(wordsCopy);
    }
}
