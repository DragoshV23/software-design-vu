package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static softwaredesign.Main.uiFactory;

public class DeadUI extends BaseUI {
    private Pet pet = Pet.getInstance();
    public DeadUI(HBox clockBar) {
        super(clockBar);
        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }
    private void addAdditionalComponents() {
        String deadMessage = pet.getName() + " " + checkCauseDead();
        Label label = new Label(deadMessage);
        String restartMessage = "close game to restart with new pet";
        Label label2 = new Label(restartMessage);

        label.setTextFill(Color.WHITE);
        label.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 15));
        getTopBar().getChildren().add(label);

        label2.setTextFill(Color.WHITE);
        label2.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 15));
        getBottomBar().getChildren().add(label2);
    }
     String checkCauseDead() {
        if (pet.getHealth() <= 0) {
            return "wasn't cleaned \nin a long time";
        } else if (pet.getMood() <= 0) {
            return "felt too lonely";
        } else if (pet.getEnergy() <= 0) {
            return "suffered from \nsleep deprivation";
        } else {
            return "starved to death";
        }
    }
}
