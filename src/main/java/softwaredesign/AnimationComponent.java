package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;


public class AnimationComponent extends Component {

    public static final int frameW = 16 * 16;
    public static final int frameH = 16 * 16;


    private AnimatedTexture texture;
    private AnimationChannel curAnim;
    private AnimationGroup petAG;

    public AnimationComponent(Pet pet) {
        petAG = new AnimationGroup(pet);
        selectAnim(pet, petAG);
        texture = new AnimatedTexture(curAnim);
        texture.loopAnimationChannel(curAnim);
    }

    public void setAnim(Pet pet) {
        selectAnim(pet, petAG);
        texture.set(new AnimatedTexture(curAnim));
        texture.loopAnimationChannel(curAnim);

    }
    private int searchAnimIndex(Pet pet, AnimationGroup animGroup) {
        LifeStage lifeStage = pet.getStage();
        State state = pet.getState();
        for (int i = 0; i < animGroup.group.size(); i++) {
            if (animGroup.group.get(i).getStage() == lifeStage &&  animGroup.group.get(i).getState() == state) {
                return i;
            }
        }
        return -1;
    }
    private void selectAnim(Pet pet, AnimationGroup animGroup) {
        int index = searchAnimIndex(pet, animGroup);
        Animation anim = animGroup.group.get(index);
        curAnim = new AnimationChannel(FXGL.image(anim.getFileName()), anim.getFramesPerRow(), frameW, frameH,
                        anim.getChannelDuration(), anim.getStartFrame(), anim.getEndFrame());
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }
}
