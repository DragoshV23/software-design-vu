package softwaredesign;

import java.util.Random;

enum Choice { ROCK, PAPER, SCISSORS }
enum Outcome { DRAW, WIN, LOOSE }

public class RPS extends MiniGame{
    Choice userChoice;
    Choice petChoice;
    Outcome score;

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
    public RPS(){
        this.petChoice = randGenChoice();
    }
}
