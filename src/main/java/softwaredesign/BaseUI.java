package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class BaseUI extends VBox {
    protected VBox topUi;
    protected HBox topBar;
    protected HBox bottomBar;

    public BaseUI(HBox clockBar) {
        setupBaseUI(clockBar);
    }

    protected void setupBaseUI(HBox clockBar) {
        // Set up the top ui
        topUi = new VBox();
        topUi.setPrefSize(FXGL.getAppWidth(), 14 * 16);
        topUi.setStyle("-fx-background-color: #1a1a1a;");
        topUi.setAlignment(Pos.CENTER);
        topUi.getChildren().add(clockBar);

        // Set up the top bar
        topBar = new HBox();
        topBar.setPrefSize(FXGL.getAppWidth(), 6 * 16);
        topBar.setStyle("-fx-background-color: #1a1a1a;");
        topBar.setAlignment(Pos.CENTER);
        topUi.getChildren().add(topBar);

        // Set up the bottom bar
        bottomBar = new HBox();
        bottomBar.setPrefSize(FXGL.getAppWidth(), 6 * 16);
        bottomBar.setStyle("-fx-background-color: #1a1a1a;");
        bottomBar.setAlignment(Pos.CENTER);

        // Add the bars to the UI
        this.getChildren().addAll(topUi, bottomBar);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(FXGL.getAppHeight() - topUi.getPrefHeight() - bottomBar.getPrefHeight());
    }
    protected VBox createUI(VBox topUi, HBox bottomBar) {
        VBox ui = new VBox();
        ui.getChildren().addAll(topUi, bottomBar);
        ui.setAlignment(Pos.CENTER);
        ui.setSpacing(FXGL.getAppHeight() - topUi.getPrefHeight() - bottomBar.getPrefHeight());
        return ui;
    }

}

// SLEEP, FOOD, PLAY, MAIN, DEATH, BG



