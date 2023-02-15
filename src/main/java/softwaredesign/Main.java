package softwaredesign;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings){ }

    private Entity pet;

    @Override
    protected void initGame() {
        pet = FXGL.entityBuilder()
                .at(200,200)
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}