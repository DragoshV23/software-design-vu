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

import java.util.ArrayList;

public class BackgroundUI extends BaseUI {
    private User user;
    private Shop backgroundShop;
    public BackgroundUI(HBox clockBar) {
        super(clockBar);
        this.user = User.getInstance();

        Background retroBackground = new Background("retro", 50, "ogbg.png");
        ArrayList<Item> stock = new ArrayList<>();
        stock.add(retroBackground);
        this.backgroundShop = new Shop("Background Shop", stock);

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

        addBackgroundButton("ogbg.png", (Background) backgroundShop.getStock().get(0));
    }

    void backgroundButtonHoverEffect(Button button, Background background) {
        Node balanceDisplay = getBottomBar().getChildren().get(0);
        button.setOnMouseEntered(event -> {
            FXGL.getGameScene().setBackgroundRepeat(background.getBackgroundImage());
            getBottomBar().getChildren().clear();
            String backgroundLabel = background.getName() + "\nPrice: $" + background.getPrice();
            Label backgroundProperty = new Label(backgroundLabel);

            backgroundProperty.setTextFill(Color.WHITE);
            backgroundProperty.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 20));
            getBottomBar().getChildren().add(backgroundProperty);
        });

        button.setOnMouseExited(event -> {
            FXGL.getGameScene().setBackgroundColor(Color.WHITE);
            getBottomBar().getChildren().clear();
            getBottomBar().getChildren().add(balanceDisplay);
        });
    }

    private void addBackgroundButton(String iconPath, Background background) {
        Button button = createIconButton(iconPath, getTopBar());
        backgroundButtonHoverEffect(button, background);
        button.setOnAction(event -> {
            FXGL.getGameScene().setBackgroundRepeat(background.getBackgroundImage());
            FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
        });
    }

}