package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FoodUI extends BaseUI {
    private Pet pet;
    private User user;
    private Shop foodShop;

    public FoodUI(HBox clockBar) {
        super(clockBar);

        this.pet = Pet.getInstance();
        this.user = User.getInstance();

        Food burger = new Food("Burger", 10, 25);
        Food kip = new Food("Roasted Kip", 20, 35);
        Food banana = new Food("Bananas", 5, 10);
        ArrayList<Item> stock = new ArrayList<>();
        stock.add(burger);
        stock.add(kip);
        stock.add(banana);
        this.foodShop = new Shop("Food Shop", stock);

        addAdditionalComponents();
        VBox ui = createUI(getTopUi(), getBottomBar());
        this.getChildren().add(ui);
    }
    private void addAdditionalComponents() {
        // Add user balance
        String balanceString = "Balance: $" + user.getBalance();
        Label balanceLabel = new Label(balanceString);

        balanceLabel.setTextFill(Color.WHITE);
        balanceLabel.setFont(Font.loadFont(Objects.requireNonNull(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf")).toExternalForm(), 20));
        getTopBar().getChildren().add(balanceLabel);

        //go back button
        Button goBackButton = createIconButton("back.png", getBottomBar());
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
            }
        });

        addFoodButton("burger.png", (Food) foodShop.getStock().get(0));
        addFoodButton("roast-chicken.png", (Food) foodShop.getStock().get(1));
        addFoodButton("banana.png", (Food) foodShop.getStock().get(2));
    }
    void foodButtonHoverEffect(Button button, Food food) {
        Node balanceDisplay = getTopBar().getChildren().get(0);
        button.setOnMouseEntered(event -> {
                getTopBar().getChildren().clear();

                //Set food label + style
                Label foodProperty = new Label(food.getLabel());
                foodProperty.setTextFill(Color.WHITE);
                foodProperty.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 20));

                getTopBar().getChildren().add(foodProperty);
        });


        button.setOnMouseExited(event -> {
                getTopBar().getChildren().clear();
                getTopBar().getChildren().add(balanceDisplay);
        });
    }
    private void addFoodButton(String imageName, Food food) {
        Button button = createIconButton(imageName, getBottomBar());

        foodButtonHoverEffect(button, food);
        button.setOnAction(event -> {
            if (user.pay(food)) {
                pet.feed(food);
                FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
            } else {
                getTopBar().getChildren().clear();
                String errorMessage = "You don't have enough money!";

                //Set error message + style
                Label errorMessageLabel = new Label(errorMessage);
                errorMessageLabel.setTextFill(Color.WHITE);
                errorMessageLabel.setFont(Font.loadFont(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 15));

                getTopBar().getChildren().add(errorMessageLabel);

                // Re-enable hover effect after a short delay
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    // Remove error message
                    getTopBar().getChildren().remove(errorMessageLabel);
                });
                pause.play();
            }
        });
    }
}
