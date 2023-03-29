package softwaredesign;
import java.util.Random;
import java.io.Serializable;


enum Race { ALL, KIP, GOAT, DINO, CAT }
enum LifeStage { EGG, KID, ADULT }
enum Gender {MALE, FEMALE}

public class Pet implements Serializable {
    private String name;
    private Race race;
    private LifeStage stage;
    private State state;
    private Gender gender;
    private int health;
    private int energy;
    private int mood;
    private int hunger;
    private int age;
    private boolean alive;

    public Pet() {
        race = Race.KIP; // randGenRace();
        state = State.IDLE;
        gender = randGenGender();
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
    private static Gender randGenGender() {
        Gender[] gender = Gender.values();
        Random random = new Random();
        int randomIndex = random.nextInt(gender.length);
        return gender[randomIndex];
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
        setAge(this.age + 1);
        setStage(calcLifeStage(this.age));
    }

    public Race getRace() {return race; }
    public int getAge() {return age; }
    public State getState() {return state; }
    public LifeStage getStage() {return stage; }
    public Gender getGender() {return gender; }
    public String getName() {return name; }
    private void setStage(LifeStage stage) {this.stage = stage; }

    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) { this.name = name; }



}
