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
        Button button1 = createIconButton("shopping-cart.png", getTopBar());
        Button button2 = createIconButton("", getTopBar());
        Button button3 = createIconButton("resetClock.png", getTopBar());
        Button button4 = createIconButton("diskette.png", getTopBar());

        //BOTTOM BUTTONS
        Button button5 = createIconButton("knife-and-fork.png", getBottomBar());
        Button button6 = createIconButton("games.png", getBottomBar());
        Button button7 = createIconButton("sleep.png", getBottomBar());
        Button button8 = createIconButton("graph.png", getBottomBar());

        //Popup
        Popup popup = new Popup();
        Label popupLabel = new Label();

        popupLabel.setMinWidth(300);
        popupLabel.setMinHeight(380);
        popupLabel.setStyle("-fx-background-color:#FAF9F6; -fx-font-size:25");
        popupLabel.setPadding(new Insets(20));
        popup.getContent().add(popupLabel);

        button8.setOnAction(new EventHandler<ActionEvent>() {
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

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.checkIfDead(getClockBar());
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        // save/creates saveFile.txt
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (pet.getStage() != LifeStage.EGG)
                    Main.save();
            }
        });

        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // FXGL.getGameScene().addUINode(foodUI(getClockBar()));
            }
        });

        button7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pet.sleep();
                Main.animatePet();
                getGameTimer().runAtInterval(() -> {
                    pet.sleep();
                }, Duration.seconds(10));
                 FXGL.getGameScene().addUINode(new SleepUI(getClockBar()));
            }
        });

    }

}

