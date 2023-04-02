package softwaredesign;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class RPSUI extends BaseUI {
    private final RPS rpsGame;
    private final Label outcomeLabel;
    private final Pet pet;
    private final User user;

    public RPSUI(HBox clockBar, Pet pet, User user) {
        super(clockBar);
        rpsGame = new RPS();

        this.pet = pet;
        this.user = user;

        outcomeLabel = new Label("");
        outcomeLabel.setFont(new Font("Arial", 20));
        outcomeLabel.setStyle("-fx-text-fill: white;");
        getTopBar().getChildren().add(outcomeLabel);

        Button rockButton = createIconButton("fist.png", getBottomBar());
        Button paperButton = createIconButton("raise-hand.png", getBottomBar());
        Button scissorsButton = createIconButton("peace-sign.png", getBottomBar());

        rockButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.ROCK);
            }
        });

        paperButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.PAPER);
            }
        });

        scissorsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play(Choice.SCISSORS);
            }
        });
    }

    private void play(Choice userChoice) {
        rpsGame.setUserChoice(userChoice);
        Outcome outcome = rpsGame.calculateOutcome();
        updateOutcomeLabel(outcome);

        pet.improveMood(); // Pet's mood is increased no matter the outcome

        switch (outcome) {
            case DRAW:
                user.setBalance(user.getBalance() + 5);
                break;
            case WIN:
                user.setBalance(user.getBalance() + 15);
                break;
            case LOSE:
                // User gets nothing if they lose, or maybe -5?
                break;
        }
    }

    private void updateOutcomeLabel(Outcome outcome) {
        switch (outcome) {
            case DRAW:
                outcomeLabel.setText("It's a draw!");
                break;
            case WIN:
                outcomeLabel.setText("You win!");
                break;
            case LOSE:
                outcomeLabel.setText("You lose!");
                break;
        }
    }
}