package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;


public class AnimationComponent extends Component {

    public static final int frameW = 16 * 16;
    public static final int frameH = 16 * 16;


    private final AnimatedTexture texture;
    private AnimationChannel animIdle;

    public AnimationComponent(Pet pet) {
        AnimationGroup petAG = new AnimationGroup(pet);
        setAnim(pet, petAG);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    private void setAnim(Pet pet, AnimationGroup animGroup) {
        switch (pet.getAge()) {
            case -1:
                int index = searchAnimIndex(pet, animGroup, LifeStage.EGG, State.IDLE);
                animIdle = new AnimationChannel(FXGL.image(animGroup.group.get(index).getFileName()),
                        animGroup.group.get(index).getFramesPerRow(), frameW, frameH, animGroup.group.get(index).getChannelDuration(),
                        animGroup.group.get(index).getStartFrame(), animGroup.group.get(index).getEndFrame());
                break;
            case 0:
                int ix = searchAnimIndex(pet, animGroup, LifeStage.KID, State.IDLE);
                animIdle = new AnimationChannel(FXGL.image(animGroup.group.get(ix).getFileName()),
                        animGroup.group.get(ix).getFramesPerRow(), frameW, frameH, animGroup.group.get(ix).getChannelDuration(),
                        animGroup.group.get(ix).getStartFrame(), animGroup.group.get(ix).getEndFrame());
                break;
        }
    }

    private int searchAnimIndex(Pet pet, AnimationGroup animGroup, LifeStage lifeStage, State state) {
        for (int i = 0; i < animGroup.group.size(); i++) {
            if (animGroup.group.get(i).getStage() == lifeStage &&  animGroup.group.get(i).getState() == state) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

}
