package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

public class MainUI extends BaseUI {
    private Pet pet = Pet.getInstance();
    public MainUI(HBox clockBar) {
        super(clockBar);
        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }

    private void addAdditionalComponents() {
        // Add additional components and their functionalities here

        //TOP BUTTONS
        Button shopButton = createIconButton("shopping-cart.png", getTopBar());
        Button statsButton = createIconButton("graph.png", getTopBar());
        Button resetClockButton = createIconButton("resetClock.png", getTopBar());
        Button saveButton = createIconButton("diskette.png", getTopBar());

        //BOTTOM BUTTONS
        Button foodButton = createIconButton("knife-and-fork.png", getBottomBar());
        Button rpsButton = createIconButton("games.png", getBottomBar());
        Button sleepButton = createIconButton("sleep.png", getBottomBar());
        Button showerButton = createIconButton("shower.png", getBottomBar());

        //Popup
        Popup popup = new Popup();
        Label popupLabel = new Label();

        popupLabel.setMinWidth(300);
        popupLabel.setMinHeight(380);
        popupLabel.setStyle("-fx-background-color:#FAF9F6; -fx-font-size:25");
        popupLabel.setPadding(new Insets(20));
        popup.getContent().add(popupLabel);

        shopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(Main.uiFactory.getUi("BACKGROUND", getClockBar()));
            }
        });

        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!popup.isShowing()){
                    popupLabel.setText(pet.getName() + " stats " +
                            "\nMood: " + pet.getMood() +
                            "\nHealth: " + pet.getHealth() +
                            "\nHunger: " + pet.getHunger() +
                            "\nEnergy: " + pet.getEnergy());
                    popup.show(FXGL.getPrimaryStage());
                }
                else{
                    popup.hide();
                }
            }
        });

        showerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.setHealth(100);
            }
        });

        resetClockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox newClockBar = Main.createClock();
                FXGL.getGameScene().addUINode(Main.uiFactory.getUi("MAIN", newClockBar));
            }
        });
        // save/creates saveFile.txt
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.saveUser();
                if (pet.getStage() != LifeStage.EGG)
                    Main.savePet();
            }
        });

        foodButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(Main.uiFactory.getUi("FOOD", getClockBar()));
            }
        });

        rpsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(new RPSUI(getClockBar()));
            }
        });

        sleepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pet.sleep();
                Main.animatePet();
                getGameTimer().runAtInterval(() -> {
                    pet.sleep();
                }, Duration.seconds(10));
                 FXGL.getGameScene().addUINode(Main.uiFactory.getUi("SLEEP", getClockBar()));
            }
        });

    }

}

