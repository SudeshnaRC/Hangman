import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman{

    String mysteryWord;
    String mysteryWordBlank = "";
    String guess;
    int playNumber;
    ArrayList<String> allGuesses = new ArrayList<String>();
    ArrayList<String> wrongGuess = new ArrayList<String>();
    int wrongTry;

    public String blankWordGenerator() throws FileNotFoundException {
        File wordFile = new File("/Path/of/downloaded/wordFile/words_alpha.txt");
        //Save words_alpha file, switch string argument passed to File() with path on your computer

        Scanner scan = new Scanner(wordFile);

        ArrayList<String> fileContents = new ArrayList<>();

        while (scan.hasNextLine()) {
            fileContents.add(scan.nextLine());
        }

        Random rand = new Random();
        int n = rand.nextInt(fileContents.size());
        mysteryWord = fileContents.get(n);


        for (int i = 0; i < mysteryWord.length(); i++) {
            mysteryWordBlank = mysteryWordBlank.concat("-");
        }
        return mysteryWordBlank;
    }

    public void playGame() {
        playNumber += 1;
        Scanner userInput = new Scanner(System.in);
        while (mysteryWordBlank.contains("-")) {
            System.out.println("Guess a letter:");
            guess = userInput.next();
            letterChecker();

        }
    }

    public void letterChecker() {
        if (mysteryWord.contains(guess)) {
            //String tempWord = "";
            allGuesses.add(guess);
            System.out.println("Correct!");
            ArrayList<Integer> guessIndices = new ArrayList<Integer>();

            int index = 0;
            while(index != -1){
                index = mysteryWord.indexOf(guess,index);
                if (index != -1) {
                    guessIndices.add(index);
                    index++;
                }
            }
            System.out.println(guessIndices);
            ArrayList<String> mysteryWordArray = new ArrayList<String>(Arrays.asList(mysteryWordBlank.split("")));

            for (int i: guessIndices){
                mysteryWordArray.set(i, guess);
            }
        
            String mysteryWordBlankTemp = String.join("", mysteryWordArray);

            mysteryWordBlank = mysteryWordBlankTemp;
            System.out.println(mysteryWordBlank);
        }
        else{
            allGuesses.add(guess);
            wrongGuess.add(guess);
            System.out.println("Better luck next time!");
            wrongTry += 1;
            hangmanBuild();
            System.out.println("The following letters are not in the word:" + wrongGuess);

        }
    }

    //Took this from somewhere

    public void hangmanBuild() {
        switch(wrongTry){

            case 1:
                System.out.println("_____________________");
                break;
            case 2:
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 3:
                System.out.println("==============");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 4:
                System.out.println("==============");
                System.out.println(" |  //");
                System.out.println(" | //");
                System.out.println(" |//");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 5:
                System.out.println("==============");
                System.out.println(" |  //      |");
                System.out.println(" | //");
                System.out.println(" |//");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 6:
                System.out.println("==============");
                System.out.println(" |  //      |");
                System.out.println(" | //       O");
                System.out.println(" |//");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 7:
                System.out.println("==============");
                System.out.println(" |  //      |");
                System.out.println(" | //       O");
                System.out.println(" |//        |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 8:
                System.out.println("==============");
                System.out.println(" |  //      |");
                System.out.println(" | //       O");
                System.out.println(" |//       _|_ ");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            case 9:
                System.out.println("==============");
                System.out.println(" |  //      |");
                System.out.println(" | //       O");
                System.out.println(" |//       _|_ ");
                System.out.println(" |          ^");
                System.out.println(" |         /\\");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
                break;
            default:
                System.out.println("==!HANGMAN!==");
                System.out.println(" |  //      |");
                System.out.println(" | //       O");
                System.out.println(" |//       _|_ ");
                System.out.println(" |          ^");
                System.out.println(" |         /\\");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_____________________");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Hangman hangman = new Hangman();
        System.out.println(hangman.blankWordGenerator());
        hangman.playGame();
    }
}
