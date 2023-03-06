package softwaredesign;
import java.util.Random;


enum Race { ALL, KIP, GOAT, DINO, CAT }
enum LifeStage { EGG, KID, ADULT }

public class Pet {
    private Race race;
    private LifeStage stage;
    private int health;
    private int energy;
    private int mood;
    private int hunger;
    private int age;
    private boolean alive;

    public Pet() {
        race = Race.KIP; // randGenRace();
        stage = LifeStage.KID;
        health = 100;
        energy = 100;
        mood = 100;
        hunger = 100;
        age = -1;
        alive = true;
    }

    static Race randGenRace() {
        Race[] races = Race.values();
        Random random = new Random();
        int randomIndex = random.nextInt(races.length);
        return races[randomIndex];
    }

    public Race getRace() {return race; }
    public int getAge() {return age; }

}
