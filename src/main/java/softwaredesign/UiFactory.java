package softwaredesign;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UiFactory {
    public VBox getUi(String uiType, HBox clockBar){
        switch (uiType){
            case "MAIN":
                return new MainUI(clockBar);
            case "FOOD":
                return new FoodUI(clockBar);
            case "SLEEP":
                return new SleepUI(clockBar);
            case "DEAD":
                return new DeadUI(clockBar);
            case "BACKGROUND":
                return new BackgroundUI(clockBar);
            case "EGG":
                return new EggUI(clockBar);
            default:
                return null;
        }
    }
}
