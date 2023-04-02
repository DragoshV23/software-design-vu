package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BackgroundUI extends BaseUI {
    private User user;
    private Shop backgroundShop;
    public BackgroundUI(HBox clockBar) {
        super(clockBar);
        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }
    private void addAdditionalComponents() {
        // Add user balance
        String balanceString = "Balance: $" + user.getBalance();
        Label balanceLabel = new Label(balanceString);

        balanceLabel.setTextFill(Color.WHITE);
        balanceLabel.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 20));
        getBottomBar().getChildren().add(balanceLabel);

        //go back button
        Button goBackButton = createIconButton("back.png", getTopBar());
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
            }
        });
    }

    void foodButtonHoverEffect(Button button, Background background) {
        Node balanceDisplay = getTopBar().getChildren().get(0);
        button.setOnMouseEntered(event -> {
            getTopBar().getChildren().clear();
            String backgroundLabel = background.getName() + "\nPrice: $" + background.getPrice();
            Label backgroundProperty = new Label(backgroundLabel);

            backgroundProperty.setTextFill(Color.WHITE);
            backgroundProperty.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 20));
            getBottomBar().getChildren().add(backgroundProperty);
        });

        button.setOnMouseExited(event -> {
            getBottomBar().getChildren().clear();
            getBottomBar().getChildren().add(balanceDisplay);
        });
    }

    private void addBackgroundButton(String imageName, Background background) {
        Button button = createIconButton(imageName, getBottomBar());
        foodButtonHoverEffect(button, background);
        button.setOnAction(event -> {
            FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
        });
    }

}
