package softwaredesign;

import javafx.util.Duration;

import java.util.ArrayList;

public class AnimationGroup {
    ArrayList<Animation> group = new ArrayList<>();

    public AnimationGroup(Pet pet) {
        Animation eggAnimation = new Animation(pet, LifeStage.EGG, State.IDLE, "eggHatch.png",
                7, Duration.seconds(10));
        group.add(eggAnimation);
        switch (pet.getRace()) {
            case KIP:
                Animation babyIdle = new Animation(pet, LifeStage.KID, State.IDLE, "kipIdle_b.png", 2,
                        Duration.seconds(1));
                Animation babySleep = new Animation(pet, LifeStage.KID, State.SLEEP, "kipSleep_b.png", 20,
                        Duration.seconds(10));
                Animation babyAngry = new Animation(pet, LifeStage.KID, State.ANGRY, "kipAngry_b.png", 12,
                        Duration.seconds(6));
                Animation adultIdle = new Animation(pet, LifeStage.ADULT, State.IDLE, "kipIdle_a.png", 2,
                        Duration.seconds(1));
                Animation adultSleep = new Animation(pet, LifeStage.ADULT, State.SLEEP, "kipSleep_a.png", 20,
                        Duration.seconds(10));
                Animation adultAngry = new Animation(pet, LifeStage.ADULT, State.ANGRY, "kipAngry_a.png", 8,
                        Duration.seconds(4));
                group.add(babyIdle);
                group.add(babySleep);
                group.add(babyAngry);
                group.add(adultIdle);
                group.add(adultSleep);
                group.add(adultAngry);
                break;
        }
    }

}
