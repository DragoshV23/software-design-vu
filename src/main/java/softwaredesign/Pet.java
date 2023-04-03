package softwaredesign;
import java.util.Random;
import java.io.Serializable;



enum Race { ALL, KIP, LLAMA, DINO, CAT }
enum LifeStage { EGG, KID, ADULT }
enum Gender {MALE, FEMALE}

public class Pet implements Serializable {
    static volatile Pet instance;
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

    private Pet() {
        race = randGenRace();
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
    public static Pet getInstance() {
        if (instance == null) {
            synchronized (Pet.class) {
                if (instance == null) {
                    instance = new Pet();
                }
            }
        }
        return instance;
    }
    private static Race randGenRace() {
        Race[] races = Race.values();
        Random random = new Random();
        int randomIndex = random.nextInt(races.length);
        if (races[randomIndex] != Race.ALL) { return races[randomIndex]; }
        else {return randGenRace(); }
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
    public void sleep() {
        this.state = State.SLEEP;
        if (energy < 95) {
            this.energy += 5;
        } else {
            this.energy = 100;
        }

    }

    public void wake() {
        this.state = State.IDLE; // TODO: check if every stat above 50 else angry
        hungry();
        hungry();
        dirty();
        if (mood < 95) {
            this.mood += 5;
        } else {
            this.mood = 100;
        }
    }

    public void improveMood() {
        if (this.mood > 75) {
            setMood(100);
        } else {
            setMood(this.mood + 25);
        }
    }
    public void feed (Food foodItem) {
        if (this.hunger > 75 ) {
            if (foodItem.getName() == "Burger" || foodItem.getName() == "Roasted Kip") {
                setHunger(100);
            } else {
                setHunger(this.hunger + foodItem.getNutritionVal());
            }
        } else {
            setHunger(this.hunger + foodItem.getNutritionVal());
        }
    }

    public void hungry() {
        this.hunger = this.hunger - 10;
    }

    public void tired() {
        this.energy = this.energy - 10;
    }

    public void bored() {
        this.mood = this.mood - 10;
    }

    public void dirty() {
        this.health = this.health - 10;
    }

    public void die() {
        this.alive = false;
        this.state = State.DEAD;
    }

    public Race getRace() {return race; }

    public int getAge() {return age; }

    public State getState() {return state; }

    public LifeStage getStage() {return stage; }

    public Gender getGender() {return gender; }

    public String getName() {return name; }

    public int getHealth() {return health; }

    public int getEnergy() {return energy; }

    public int getMood() {return mood; }

    public int getHunger() {return hunger; }

    private void setStage(LifeStage stage) { this.stage = stage; }

    public void setAge(int age) { this.age = age; }

    public void setName(String name) { this.name = name; }

    public void setMood(int boost) { this.mood = boost; }

    public void setEnergy(int energy) { this.energy = energy; }

    public void setHunger(int boost) { this.hunger = boost; }

    public void setHealth(int boost) { this.health = boost; }
}
