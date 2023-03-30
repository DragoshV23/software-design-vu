package softwaredesign;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.view.MouseButtonView;
import com.almasb.fxgl.profile.DataFile;
import com.almasb.fxgl.profile.SaveLoadHandler;
import com.almasb.fxgl.ui.DialogBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.util.Duration;
import java.io.*;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    private static final int BUTTON_COUNT = 4;
    DataFile dataFile = new DataFile();


    @Override
    protected void initSettings(GameSettings settings){

        // Config window size
        settings.setTitle("Office Pets");
        settings.setHeight(37 * 16);
        settings.setWidth(32 * 16);
    }

    Pet pet = new Pet();
    Entity petEntity;
    private void save() {
        try {
            FileOutputStream f = new FileOutputStream(new File("saveFile.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(pet);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
    private void load() {
        try {

            FileInputStream f = new FileInputStream(new File("saveFile.txt"));
            ObjectInputStream o = new ObjectInputStream(f);

            Pet petLoad = (Pet) o.readObject();
            pet = petLoad;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    protected void onPreInit() {

    }

    @Override
    protected void initGame() {
        File f = new File("saveFile.txt");
        if(f.exists() && !f.isDirectory()) {
            load();
            // Spawn pet
            petEntity = FXGL.entityBuilder()
                    .at((32 * 8) - 16 * 8, (37 * 8) - 16 * 4)
                    .with(new AnimationComponent(pet))
                    .buildAndAttach();
        } else {
            // Spawn pet
            petEntity = FXGL.entityBuilder()
                    .at((32 * 8) - 16 * 8, (37 * 8) - 16 * 4)
                    .with(new AnimationComponent(pet))
                    .buildAndAttach();

            // Hatch after 10 sec
            getGameTimer().runOnceAfter(() -> {
                Platform.runLater(() -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Your pet hatched!");
                    dialog.setHeaderText("Race: " + pet.getRace());
                    Image src = getAssetLoader().loadImage("female.png");
                    if (pet.getGender() == Gender.MALE) {
                        src = getAssetLoader().loadImage("male.png");
                    }
                    dialog.setGraphic(new ImageView(src));
                    dialog.setContentText("Name your pet:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && !result.get().isEmpty()) {
                        String petName = result.get();
                        pet.setName(petName);
                    }
                });
                // Check if user entered a name and set the pet's name
                pet.birthday();
                petEntity.getComponent(AnimationComponent.class).setAnim(pet);
            }, Duration.seconds(10));
        }
    }
    @Override
    protected  void initInput() {}

    @Override
    protected void initUI() {
//        Font.loadFont(digital.ttf);

        // Set up the top ui
        VBox topUi = new VBox();
        topUi.setPrefSize(FXGL.getAppWidth(),14 * 16);
        topUi.setStyle("-fx-background-color: #1a1a1a;");
        topUi.setAlignment(Pos.CENTER);

        // Creating clock
        HBox clockBar = new HBox();
        clockBar.setPrefSize(FXGL.getAppWidth(), 8 * 16);
        clockBar.setStyle("-fx-background-color: #000000; -fx-font-size: 60");
        clockBar.setAlignment(Pos.CENTER);
        topUi.getChildren().add(clockBar);

        Label timerLabel = new Label("00:00:00");

        timerLabel.setText("00:00:00");
        final LocalTime[] time = {LocalTime.MIDNIGHT};

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time[0] = time[0].plusSeconds(1);
            timerLabel.setText(String.format("%02d:%02d:%02d",
                    time[0].getHour(),
                    time[0].getMinute(),
                    time[0].getSecond()
            ));
//            Font font = Font.loadFont("resources/assets/fonts/clockfont.ttf", 60); //throwing a weird error
//            timerLabel.setFont(font);
            timerLabel.setStyle("-fx-text-fill: #8B4000");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        clockBar.getChildren().add(timerLabel);

        // Set up the top bar
        HBox topBar = new HBox();
        topBar.setPrefSize(FXGL.getAppWidth(), 6 * 16);
        topBar.setStyle("-fx-background-color: #1a1a1a;");
        topBar.setAlignment(Pos.CENTER);
        topUi.getChildren().add(topBar);

        // Set up the bottom bar
        HBox bottomBar = new HBox();
        bottomBar.setPrefSize(FXGL.getAppWidth(), 6 * 16);
        bottomBar.setStyle("-fx-background-color: #1a1a1a;");
        bottomBar.setAlignment(Pos.CENTER);

        //TOP BUTTONS
        Button button1 = createIconButton("shopping-cart.png", topBar);
        Button button2 = createIconButton("", topBar);
        Button button3 = createIconButton("", topBar);
        Button button4 = createIconButton("", topBar);

        //BOTTOM BUTTONS
        Button button5 = createIconButton("knife-and-fork.png", bottomBar);
        Button button6 = createIconButton("games.png", bottomBar);
        Button button7 = createIconButton("sleep.png", bottomBar);
        Button button8 = createIconButton("graph.png", bottomBar);

        //popup
        Popup popup = new Popup();
        Label popupLabel = new Label();

        popupLabel.setMinWidth(300);
        popupLabel.setMinHeight(380);
        popupLabel.setStyle("-fx-background-color:#FAF9F6; -fx-font-size:25");
        popupLabel.setPadding(new Insets(20));
        popup.getContent().add(popupLabel);

        button8.setOnAction(new EventHandler<ActionEvent>() {
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

        //adds 1 age to pet
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pet.birthday();
                petEntity.getComponent(AnimationComponent.class).setAnim(pet);
                System.out.println(pet.getAge());
            }
        });
        // save/creates saveFile.txt
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (pet.getStage() != LifeStage.EGG)
                    save();
            }
        });

        // Add the bars to the UI
        VBox ui = new VBox();
        ui.getChildren().addAll(topUi, bottomBar);
        ui.setAlignment(Pos.CENTER);
        ui.setSpacing(FXGL.getAppHeight() - topUi.getPrefHeight() - bottomBar.getPrefHeight());

        // Add the UI to the game scene
        FXGL.getGameScene().addUINode(ui);
    }

    private Button createIconButton(String imageName, HBox bar) {
        Button button = new Button();
        Image icon = getAssetLoader().loadImage(imageName);
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(80);
        iconView.setFitWidth(80);
        button.setGraphic(iconView);
        button.setPrefSize(80, 80);
        bar.getChildren().add(button);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}