package softwaredesign;
import java.util.Random;

enum Race { KIP, GOAT, DINO, CAT }
enum LifeStage { EGG, KID, ADULT }

public class Pet {
    private Race race;
    private LifeStage stage;
    private double health;
    private double energy;
    private double mood;
    private double hunger;
    private int age;
    private boolean alive;

    public Pet() {
        race = randGenRace();
        stage = LifeStage.KID;
        health = 100.0;
        energy = 100.0;
        mood = 100.0;
        hunger = 100.0;
        age = 0;
        alive = true;
    }

    static Race randGenRace() {
        Race[] races = Race.values();
        Random random = new Random();
        int randomIndex = random.nextInt(races.length);
        return races[randomIndex];
    }

    public Race getRace() {return race; }

}
