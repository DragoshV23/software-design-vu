package softwaredesign;
import java.util.Random;


enum Race { ALL, KIP, GOAT, DINO, CAT }
enum LifeStage { EGG, KID, ADULT }

public class Pet {
    private Race race;
    private LifeStage stage;
    private State state;
    private int health;
    private int energy;
    private int mood;
    private int hunger;
    private int age;
    private boolean alive;

    public Pet() {
        race = Race.KIP; // randGenRace();
        state = State.IDLE;
        health = 100;
        energy = 100;
        mood = 100;
        hunger = 100;
        age = -1;
        stage = calcLifeStage(age);
        alive = true;
    }

    private static Race randGenRace() {
        Race[] races = Race.values();
        Random random = new Random();
        int randomIndex = random.nextInt(races.length);
        return races[randomIndex];
    }
    private static LifeStage calcLifeStage(int age) {
        switch (age) {
            case -1:
                return LifeStage.EGG;
            case 0:
                return LifeStage.KID;
            case 1:
                return LifeStage.KID;
            case 2:
                return LifeStage.KID;
            default:
                return LifeStage.ADULT;
        }
    }

    public void birthday() {
        setAge(age + 1);
    }


    public Race getRace() {return race; }
    public int getAge() {return age; }
    public State getState() {return state; }
    public LifeStage getStage() {return stage; }

    public void setAge(int age) {
        this.age = age;
    }



}
