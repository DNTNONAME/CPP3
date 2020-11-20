import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        int ch;
        String str;
        while(true){
            printMainMenu();
            ch = getChoice();
            if (ch == 0) break;
            else if (ch == -1) continue;
            else if (ch == 1){
                while(true){
                    printNoRegexMenu();
                    ch = getChoice();
                        if (ch == 0) break;
                        else if (ch == -1) continue;
                        else if (ch == 1) {
                            printInputChoices();
                            ch = getChoice();
                            str = getString(ch, "E:\\intellij_projects\\cpp\\lab_3\\src\\noregex_brackets.txt");
                            TextParser_NoRegex tp = new TextParser_NoRegex();
                            printList(tp.getStringsInBrackets(str));
                            break;
                        }
                        else if (ch == 2) {
                            printInputChoices();
                            ch = getChoice();
                            str = getString(ch, "E:\\intellij_projects\\cpp\\lab_3\\src\\textWithRepetitions.txt");
                            LinkedList<String> wordsToBeChanged = getWordsToBeChanged();
                            TextParser_NoRegex tp = new TextParser_NoRegex();
                            System.out.println("words to be changed:");
                            printList(wordsToBeChanged);
                            System.out.println("incoming string:");
                            System.out.println(str);
                            System.out.println("most common word:");
                            System.out.println(tp.getMostCommonWord(str));
                            System.out.println("string with replaced words:");
                            System.out.println(tp.replaceWordsToMostCommon(str, getWordsToBeChanged()));
                            break;
                        }

                }
            }
            else if (ch == 2) {
                while(true){
                    printRegexMenu();
                    ch = getChoice();
                    if(ch == -1) continue;
                    else if( ch == 0) break;
                    else if(ch == 1) {
                        printInputChoices();
                        ch = getChoice();
                        str = getString(ch, "E:\\intellij_projects\\cpp\\lab_3\\src\\regex_questions.txt");
                        TextParser_Regex tp = new TextParser_Regex();
                        System.out.println(tp.getQuestionSenteces(str));
                        break;
                    }
                    else if(ch == 2) {
                        printInputChoices();
                        ch = getChoice();
                        str = getString(ch, "E:\\intellij_projects\\cpp\\lab_3\\src\\regex_questions.txt");
                        System.out.println("Type length");
                        ch = getChoice();
                        TextParser_Regex tp = new TextParser_Regex();
                        System.out.println(tp.getWordsWithDefinedLength(ch, str));
                        break;
                    }
                }

                }
            }
        }

    private static final Scanner console_scanner = new Scanner(System.in);

    public static void printMainMenu(){
        System.out.println("1 - No regex");
        System.out.println("2 - Regex");
        System.out.println("0 - Exit");
    }

    public static void printRegexMenu(){
        System.out.println("1 - Get question sentences");
        System.out.println("2 - Get words of certain length");
        System.out.println("0 - Back");
    }

    public static void printNoRegexMenu(){
        System.out.println("1 - Get strings in brackets");
        System.out.println("2 - Replace most common word");
        System.out.println("0 - Back");
    }

    public static void printInputChoices(){
        System.out.println("1 - Keyboard");
        System.out.println("2 - File");
    }

    public static String getString( int ch, String filePath){
        String str = "";
        if(ch ==1){
            str = console_scanner.nextLine();
            str = console_scanner.nextLine();
            return str;
        }
        else if(ch == 2){
            try{
                File fr = new File(filePath);
                Scanner fileScanner = new Scanner(fr);
                while (fileScanner.hasNextLine()){
                    str += fileScanner.nextLine();
                }
                return str;
            } catch (FileNotFoundException exc){
                System.out.println(exc.getMessage());
            }
        }
        return null;
    }

    public static void printList(List<String> list){
        for (Object obj : list){
            System.out.println(obj.toString());
        }
    }

    public static int getChoice(){
        int ch;
        try{
            ch = console_scanner.nextInt();
            return ch;
        } catch (InputMismatchException exc){
            System.out.println("Wrong input");
            return -1;
        }
    }

    public static LinkedList<String> getWordsToBeChanged(){
        LinkedList<String> list = new LinkedList<String>();
        try{
            Scanner scanner = new Scanner(new File("E:\\intellij_projects\\cpp\\lab_3\\src\\wordsToBeChanged.txt"));
            while(scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
            return list;
        } catch(FileNotFoundException exc){
            System.out.println(exc.getMessage());
        }
        return null;
    }
    }

