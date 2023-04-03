package softwaredesign;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EggUI extends BaseUI {
    private Pet pet = Pet.getInstance();
    public EggUI(HBox clockbar) {
        super(clockbar);
        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }
    private void addAdditionalComponents() {
        String hatchingMessage = "hatching";
        Label label = new Label(hatchingMessage);
        String loadingMessage = "...";
        Label label2 = new Label(loadingMessage);

        label.setTextFill(Color.WHITE);
        label.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 15));
        getTopBar().getChildren().add(label);

        label2.setTextFill(Color.WHITE);
        label2.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 15));
        getBottomBar().getChildren().add(label2);
    }
}
