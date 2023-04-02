package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class BaseUI extends VBox {
    private VBox topUi;
    private HBox topBar;
    private HBox bottomBar;
    private HBox clockBar;

    public BaseUI(HBox clockBar) {
        this.clockBar = clockBar;
        setupBaseUI();
    }

    private void setupBaseUI() {
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

    protected VBox getTopUi() { return this.topUi; }
    protected HBox getTopBar() { return this.topBar; }
    protected HBox getBottomBar() { return this.bottomBar; }
    protected HBox getClockBar() { return this.clockBar; }

}

// SLEEP, FOOD, PLAY, MAIN, DEATH, BG



