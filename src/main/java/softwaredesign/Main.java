package softwaredesign;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends GameApplication {

    private static final int BUTTON_COUNT = 4;

    @Override
    protected void initSettings(GameSettings settings){

        // Config window size
        settings.setTitle("Office Pets");
        settings.setHeight(37 * 16);
        settings.setWidth(32 * 16);
    }

    private Entity pet;

    @Override
    protected void initGame() {

        // Spawn pet
        pet = FXGL.entityBuilder()
                .at((32 * 8) - 16 * 8, (37 * 8) - 16 * 8)
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    @Override
    protected  void initInput() {}

    @Override
    protected void initUI() {

        // Set up the top bar
        HBox topBar = new HBox();
        topBar.setPrefSize(FXGL.getAppWidth(), 96);
        topBar.setStyle("-fx-background-color: #1a1a1a;");
        topBar.setAlignment(Pos.CENTER);

        // Add buttons to the top bar
        for (int i = 0; i < BUTTON_COUNT; i++) {
            Button button = new Button("Button " + (i+1));
            button.setPrefSize(FXGL.getAppWidth() / BUTTON_COUNT, 64);
            button.setStyle("-fx-background-color: #3f3f3f; -fx-text-fill: white;");
            button.setAlignment(Pos.CENTER);
            topBar.getChildren().add(button);
        }

        // Set up the bottom bar
        HBox bottomBar = new HBox();
        bottomBar.setPrefSize(FXGL.getAppWidth(), 96);
        bottomBar.setStyle("-fx-background-color: #1a1a1a;");
        bottomBar.setAlignment(Pos.CENTER);

        // Add buttons to the bottom bar
        for (int i = 0; i < BUTTON_COUNT; i++) {
            Button button = new Button("Button " + (i+1));
            button.setPrefSize(FXGL.getAppWidth() / BUTTON_COUNT, 64);
            button.setStyle("-fx-background-color: #3f3f3f; -fx-text-fill: white;");
            button.setAlignment(Pos.CENTER);
            bottomBar.getChildren().add(button);
        }

        // Add the bars to the UI
        VBox ui = new VBox();
        ui.getChildren().addAll(topBar, bottomBar);
        ui.setAlignment(Pos.CENTER);
        ui.setSpacing(FXGL.getAppHeight() - topBar.getPrefHeight() - bottomBar.getPrefHeight());

        // Add the UI to the game scene
        FXGL.getGameScene().addUINode(ui);
    }

    public static void main(String[] args) {
        launch(args);
    }
}