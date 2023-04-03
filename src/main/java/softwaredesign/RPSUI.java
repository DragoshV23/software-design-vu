package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class RPSUI extends BaseUI {
    private final RPS rpsGame;
    private final Label outcomeLabel;
    private final Pet pet;
    private final User user;
    private int reward;

    public RPSUI(HBox clockBar) {
        super(clockBar);
        rpsGame = new RPS();

        this.pet = Pet.getInstance();
        this.user = User.getInstance();

        outcomeLabel = new Label("");
        outcomeLabel.setFont(Font.loadFont(Objects.requireNonNull(getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf")).toExternalForm(), 15));
        outcomeLabel.setStyle("-fx-text-fill: white;");
        getTopBar().getChildren().add(outcomeLabel);

        Button rockButton = createIconButton("fist.png", getBottomBar());
        Button paperButton = createIconButton("raise-hand.png", getBottomBar());
        Button scissorsButton = createIconButton("peace-sign.png", getBottomBar());

        rockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.ROCK);
                displayReturnButton();
            }
        });

        paperButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.PAPER);
                displayReturnButton();
            }
        });

        scissorsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.SCISSORS);
                displayReturnButton();
            }
        });
    }

    private void displayReturnButton() {
        getBottomBar().getChildren().clear();
        Button goBackButton = createIconButton("back.png", getBottomBar());
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXGL.getGameScene().addUINode(new MainUI(getClockBar()));
            }
        });
    }

    private void play(Choice userChoice) {
        rpsGame.setUserChoice(userChoice);
        Outcome outcome = rpsGame.calculateOutcome();

        pet.improveMood(); // Pet's mood is increased no matter the outcome
        pet.tired();       // Pet's energy goes down by 10 for each round

        switch (outcome) {
            case DRAW:
                reward = 5;
                user.setBalance(user.getBalance() + reward);
                break;
            case WIN:
                reward = 15;
                user.setBalance(user.getBalance() + reward);
                break;
            case LOSE:
                // User gets nothing if they lose, or maybe -5?
                break;
        }
        updateMessageLabel(rpsGame.getPetChoice(), outcome, reward);
    }

    private void updateMessageLabel(Choice petChoice, Outcome outcome, int reward) {
        String outcomeMessage = "";
        switch (outcome) {
            case DRAW:
                outcomeMessage = "It's a draw!";
                break;
            case WIN:
                outcomeMessage = "You win!";
                break;
            case LOSE:
                outcomeMessage = "You lose!";
                break;
        }
        String rewardMessage = (outcome == Outcome.LOSE) ? "You get no money." : "You get $" + reward + ".";
        outcomeLabel.setText(pet.getName() + " chose " + petChoice + ". " + "\n" + rewardMessage + " " + outcomeMessage +
                            "\n" + pet.getName() + " enjoyed the game!");
    }
}