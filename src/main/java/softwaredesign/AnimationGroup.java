package softwaredesign;

import javafx.util.Duration;

import java.util.ArrayList;

public class AnimationGroup {
    ArrayList<Animation> group = new ArrayList<>();

    public AnimationGroup(Pet pet) {
        addToGroup(pet, LifeStage.EGG, State.IDLE, "eggHatch.png", 7, Duration.seconds(10));
        switch (pet.getRace()) {
            case KIP:
                addToGroup(pet, LifeStage.KID, State.IDLE, "kipIdle_b.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.KID, State.SLEEP, "kipSleep_b.png", 20, Duration.seconds(10));
                addToGroup(pet, LifeStage.KID, State.ANGRY, "kipAngry_b.png", 12, Duration.seconds(6));
                addToGroup(pet, LifeStage.ADULT, State.IDLE, "kipIdle_a.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.ADULT, State.SLEEP, "kipSleep_a.png", 20, Duration.seconds(10));
                addToGroup(pet, LifeStage.ADULT, State.ANGRY, "kipAngry_a.png", 8, Duration.seconds(2));
                break;
            case DINO:
                addToGroup(pet, LifeStage.KID, State.IDLE, "dinoIdle_b.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.KID, State.SLEEP, "dinoSleep_b.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.KID, State.ANGRY, "dinoAngry_b.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.ADULT, State.IDLE, "dinoIdle_a.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.ADULT, State.SLEEP, "dinoSleep_a.png", 2, Duration.seconds(1));
                addToGroup(pet, LifeStage.ADULT, State.ANGRY, "dinoAngry_a.png", 2, Duration.seconds(1));
        }
    }

    private void addToGroup(Pet pet, LifeStage lifestage, State state, String pathName, int frames, Duration duration) {
        Animation animation = new Animation(pet, lifestage, state, pathName, frames,
                duration);
        this.group.add(animation);
    }

}
