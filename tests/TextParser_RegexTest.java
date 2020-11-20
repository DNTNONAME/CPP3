import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TextParser_RegexTest {
    String[] wordsWithLength5 = {"Doing", "Hello", "plans", "today"};
    String[] questions = {"How are you?", String.format("%s well, and you?", wordsWithLength5[0]), "Have you got any plans for today?"};
    String str = String.format("%s! Hi! %s %s Me too. It was nice to meet you! %s Yeah, i have some",
            wordsWithLength5[1], questions[0], questions[1], questions[2]);

    String[] collectionToArray(Collection<String> collection){
        String[] arr = new String[collection.size()];
        int i = 0;
        for (String obj : collection){
            arr[i] = obj;
            i++;
        }
        return arr;
    }

    String[] toLowerCase(Object[] collection){
        String[] lowerCasedArr = new String[collection.length];
        for(int i =0; i < collection.length; i++){
            lowerCasedArr[i] = ((String)collection[i]).toLowerCase();
        }
        return lowerCasedArr;
    }

    @Test
    void getQuestionSentecesTest_1() {
        TextParser_Regex tp = new TextParser_Regex();
        LinkedList<String> questionList = tp.getQuestionSenteces(str);
        String[] questionsArray = collectionToArray(questionList);
        assertTrue(questionsArray.length == questions.length);
    }

    @Test
    void getQuestionSentecesTest_2() {
        TextParser_Regex tp = new TextParser_Regex();
        LinkedList<String> questionList = tp.getQuestionSenteces(str);
        String[] questionsArray = collectionToArray(questionList);
        for (int i = 0; i < questions.length; i++)
            assertTrue(questionsArray[i].equals(questions[i]));
    }

    @Test
    void getWordsWithDefinedLength_Test1() {
        int length = 5;
        TextParser_Regex tp = new TextParser_Regex();
        HashSet<String> wordsSet = tp.getWordsWithDefinedLength(length, str);
        assertEquals(wordsWithLength5.length, wordsSet.size());
    }

    @Test
    void getWordsWithDefinedLength_Test2() {
        int length = 5;
        TextParser_Regex tp = new TextParser_Regex();
        HashSet<String> wordsSet = tp.getWordsWithDefinedLength(length, str);
        String[] words = collectionToArray(wordsSet);
        Object[] wordsSorted = Arrays.stream(words).sorted().toArray();
        wordsWithLength5 = toLowerCase(wordsWithLength5);
        for (int  i =0; i < wordsWithLength5.length; i++){
            assertArrayEquals(wordsWithLength5, wordsSorted);
        }
    }
}