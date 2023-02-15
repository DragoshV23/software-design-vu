package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;


public class AnimationComponent extends Component {

    private final AnimatedTexture texture;
    private final AnimationChannel animIdle;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("kipIdle.png"), 2, 16 * 4, 16 * 4, Duration.seconds(1), 0, 1);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

}
