package softwaredesign;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings){
        settings.setTitle("Office Pets");
        settings.setHeight(37 * 16);
        settings.setWidth(32 * 16);
    }

    private Entity pet;

    @Override
    protected void initGame() {
        pet = FXGL.entityBuilder()
                .at((32 * 8) - 16 * 8, (37 * 8) - 16 * 8)
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}