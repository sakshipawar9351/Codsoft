 
//Number Guessing Game

import java.util.Random;
import java.util.Scanner;
public class NumberGuessGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int TotalScore = 0;
        boolean KeepPlaying = true ;

        System.out.println("Welcome to the 'Number Guessing Game'  !!");
        System.out.println("\n");
        System.out.println("Hey !");
        System.out.println("I will pick a random number between 1 to 100 , and you have to guess it.");
        System.out.println("You have only 5 tries in each round.");
        System.out.println("Let's begin !\n\n");

        while(KeepPlaying){
            int OriginalNum = random.nextInt(100)+1;    //get num betn 1 to 100
            int attempts = 0;
            int MaxTry = 5;
            boolean GuessCorrect = false;

                  while(attempts <= MaxTry){
                    System.out.println("Enter yous guessed number : ");
                    int UserGuess = sc.nextInt();
                    attempts++;

                    if(UserGuess == OriginalNum){
                        System.out.println("Congratulations ! You guessed the number in "+attempts+" attempts. ");
                        GuessCorrect = true;
                        break;
                    }
                    else if(UserGuess < OriginalNum){
                        System.out.println("Your guessed number is low .  Try higher number. ");
                    }
                    else{
                         System.out.println("Your guessed number is high .  Try lower number. ");
                    }

                    int TryLeft = MaxTry - attempts;
                    if(TryLeft > 0){
                        System.out.println("You have "+TryLeft+ " attempts left .\n");
                    }
 
                  }
                   
                   //know the the OriginalNumber from the system
                  if(!GuessCorrect){
                    System.out.println("Sorry! You've used all attempts. The correct number was: " + OriginalNum);
                    }

                  // if user wants to play another round 
                  System.out.println("\n Do you want to play another round ? (yes/no) : ");
                  sc.nextLine();
                  String choice = sc.nextLine();
                  choice = choice.trim().toLowerCase();  //for lowercase identify

                      if(!choice.equals("yes")){
                        KeepPlaying = false;
                      }
        }

        System.out.println("\nThanks for playing ! Your final score is : "+TotalScore + " rounds won.");
        sc.close();

    }
}