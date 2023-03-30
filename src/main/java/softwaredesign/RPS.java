package softwaredesign;

import java.util.Random;

enum Choice { ROCK, PAPER, SCISSORS }
enum Outcome { DRAW, WIN, LOSE }

public class RPS extends MiniGame{
    Choice userChoice;
    Choice petChoice;
    Outcome score;
    public RPS(){
        this.petChoice = randGenChoice();
    }
    public Outcome calculateOutcome() {
        // outcome draw
        if (userChoice == petChoice) {
            return Outcome.DRAW;
        // outcome win
        } else if (userChoice == Choice.ROCK && petChoice == Choice.SCISSORS ||
                   userChoice == Choice.SCISSORS && petChoice == Choice.PAPER ||
                   userChoice == Choice.PAPER && petChoice == Choice.ROCK) {
                return Outcome.WIN;
        // outcome lose
        } else {
            return Outcome.LOSE;
        }
    }

    public void setUserChoice(Choice userChoice) {
        this.userChoice = userChoice;
    }
    public Choice getUserChoice() {return userChoice; }
    public Choice getPetChoice() {return petChoice; }

    private static Choice randGenChoice() {
        Choice[] choices = Choice.values();
        Random random = new Random();
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }
}
