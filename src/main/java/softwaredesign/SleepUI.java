package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class SleepUI extends BaseUI {
    private Pet pet = Pet.getInstance();
    public SleepUI(HBox clockBar) {
        super(clockBar);
        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }

    private void addAdditionalComponents() {
        Button wakeButton = createIconButton("wake-up.png", getBottomBar());
        Button statsButton = createIconButton("graph.png", getBottomBar());

        //popup
        Popup popup = new Popup();
        Label popupLabel = new Label();

        popupLabel.setMinWidth(300);
        popupLabel.setMinHeight(380);
        popupLabel.setStyle("-fx-background-color:#FAF9F6; -fx-font-size:25");
        popupLabel.setPadding(new Insets(20));
        popup.getContent().add(popupLabel);

        wakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(popup.isShowing()){
                    popup.hide();
                }
                pet.wake();
                Main.animatePet();
                FXGL.getGameScene().addUINode(Main.uiFactory.getUi("MAIN", getClockBar()));
                Main.checkIfDead(getClockBar());
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
    }


}
