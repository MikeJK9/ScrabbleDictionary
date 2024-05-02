import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //gets all valid scrabble words that contain all letters provided
    public static void main(String[] args) {
        //int size = 6;
        String filename = "dictionary.txt";
        String letterFile = "letters.txt";

        String outfile = "validWords.txt";
        ArrayList<String> words = new ArrayList<String>();

        ArrayList<String> letters = new ArrayList<String>();


        File f = new File(filename);
        File l = new File(letterFile);

        Scanner input = null;
        //sets input to the letter file
        try {
            input = new Scanner(l);
        } catch ( java.io.FileNotFoundException ex ) {
            System.exit( 1 );
        }

        //adds letters to the arrayList
        while (input.hasNext() ) {
            letters.add(input.next().toUpperCase());
        }

        //sets input to the dictionary file
        try {
            input = new Scanner(f);
        } catch ( java.io.FileNotFoundException ex ) {
            System.exit( 1 );
        }
        boolean hasAll;
        int i;
        String word;
        int longestLength=-1;

        //looks through every word in the dictionary to find
        //the ones containing all of the letters given
        while (input.hasNext() ) {
            hasAll = true;
            word = input.next();
            for(i = 0; i < letters.size(); i++){
                if(!word.contains(letters.get(i).toUpperCase())){
                    hasAll = false;
                    break;
                }
            }
            if(hasAll) {
                words.add(word);
                if(word.length()>longestLength){
                    longestLength=word.length();
                }
            }
        }

        //initializes the arrayList to sort words by size
        ArrayList<ArrayList<String>> sortedWords = new ArrayList<ArrayList<String>>();
        for(i = 0; i < longestLength; i++){
            sortedWords.add(new ArrayList<String>());
        }

        //sorts the words by size
        for(i = 0; i < words.size(); i++){
            sortedWords.get(words.get(i).length()-1).add(words.get(i));
        }

        //outputs the words by size to the designated file
        boolean printedAny = false;
        try {
            FileWriter output = new FileWriter(outfile);
            for(i = 0; i < sortedWords.size(); i++){
                if(sortedWords.get(i).size()==0){
                    continue;
                }
                output.write("Valid Scrabble Words of Size "+(i+1)+":\n");
                for (int j = 0; j < sortedWords.get(i).size(); j++) {
                    output.write(sortedWords.get(i).get(j)+"\n");
                    printedAny = true;
                }
            }
            if(!printedAny){
                output.write("There is no word that uses all of the given letters.");
            }
            output.close();
        } catch(IOException e){


        }

    }
}