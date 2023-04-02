package softwaredesign;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DeadUI extends BaseUI {
    private Pet pet = Pet.getInstance();
    public DeadUI(HBox clockBar) {
        super(clockBar);
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }
}
