package softwaredesign;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.profile.DataFile;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.*;
import java.time.LocalTime;
import java.util.Optional;
import javafx.scene.text.Font;
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

    Pet _pet = loadOrCreatePet(); // ensures singleton object instance is only 1 and loaded
    User _user = loadOrCreateUser(); // ensures singleton object instance is only 1 and loaded
    public HBox clock;
    public static UiFactory uiFactory = new UiFactory();
    public static void animatePet() {
        Pet pet = Pet.getInstance();
        petEntity.getComponent(AnimationComponent.class).setAnim(pet);
    }
    static Entity petEntity;

    public static void savePet() {
        Pet pet = Pet.getInstance();
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

    private static Pet loadPet() {
        try {
            Pet petLoad = Pet.getInstance();
            FileInputStream f = new FileInputStream(new File("saveFile.txt"));
            ObjectInputStream o = new ObjectInputStream(f);

            Pet.instance = (Pet) o.readObject();
            f.close();
            o.close();

            return petLoad;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    private static User loadUser() {
        try {
            User userLoad = User.getInstance();
            FileInputStream f = new FileInputStream(new File("userFile.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            User.instance = (User) o.readObject();
            f.close();
            o.close();

            return userLoad;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static void saveUser() {
        User user = User.getInstance();
        try {
            FileOutputStream f = new FileOutputStream(new File("userFile.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(user);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream User");
        }
    }

    @Override
    protected void initGame() {
        clock = createClock();
        loadOrCreatePetEntity(clock);
        reducePetStats(Duration.seconds(6), clock);
    }

    private static Pet loadOrCreatePet() {
        File f = new File("saveFile.txt");
        if(f.exists() && !f.isDirectory()) {
            return loadPet();
        } else {
            return Pet.getInstance();
        }
    }
    static User loadOrCreateUser() {
        File f = new File("userFile.txt");
        if(f.exists() && !f.isDirectory()) {
            return loadUser();
        } else {
            return User.getInstance();
        }
    }

    private static void loadOrCreatePetEntity(HBox clock) {
        File f = new File("saveFile.txt");
        if(f.exists() && !f.isDirectory()) {
            Pet pet = loadPet();
            // Spawn pet
            petEntity = FXGL.entityBuilder()
                    .at((32 * 8) - 16 * 8, (37 * 8) - 16 * 4)
                    .with(new AnimationComponent(pet))
                    .buildAndAttach();
        } else {
            Pet pet = Pet.getInstance();
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
                FXGL.getGameScene().addUINode(uiFactory.getUi("MAIN", clock));
            }, Duration.seconds(10));
        }
    }

    static void reducePetStats(Duration interval, HBox clockBar) {
        Pet pet = Pet.getInstance();
        getGameTimer().runAtInterval(() -> {
            if (pet.getStage() != LifeStage.EGG) {
                pet.hungry();
                pet.tired();
                pet.bored();
                pet.dirty();
                checkIfDead(clockBar);
            }
        }, interval);
    }

    @Override
    protected void initUI() {
        User user = User.getInstance();
        HBox clockBar = createClock();
        BackgroundFactory backgroundFactory = new BackgroundFactory();
        System.out.println(user.getActiveBackground());
        FXGL.getGameScene().setBackgroundRepeat(backgroundFactory.getBackground(user.getActiveBackground()).getBackgroundImage());

        File f = new File("saveFile.txt");
        if(f.exists() && !f.isDirectory()) {
            FXGL.getGameScene().addUINode(uiFactory.getUi("MAIN", clockBar));
        } else {
            FXGL.getGameScene().addUINode(uiFactory.getUi("EGG", clockBar));
        }
        // Add the UI to the game scene

    }
    public static HBox createClock() {
        // Creating clock
        HBox clockBar = new HBox();
        clockBar.setPrefSize(FXGL.getAppWidth(), 8 * 16);
        clockBar.setStyle("-fx-background-color: #000000; -fx-font-size: 60");
        clockBar.setAlignment(Pos.CENTER);


        Label timerLabel = new Label("00:00:00");

        timerLabel.setText("00:00:00");
        timerLabel.setFont(Font.loadFont(getGameScene().getClass().getResource("/assets/fonts/PressStart2P-Regular.ttf").toExternalForm(), 35));
        final LocalTime[] time = {LocalTime.MIDNIGHT};

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time[0] = time[0].plusSeconds(1);
            timerLabel.setText(String.format("%02d:%02d:%02d",
                    time[0].getHour(),
                    time[0].getMinute(),
                    time[0].getSecond()
            ));
            timerLabel.setStyle("-fx-text-fill: #8B4000");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        clockBar.getChildren().add(timerLabel);
        return clockBar;
    }

    static void checkIfDead(HBox clockBar){
        Pet pet = Pet.getInstance();
        if(pet.getHealth() <= 0 || pet.getEnergy() <= 0 || pet.getMood() <= 0 || pet.getHunger() <= 0){
            pet.die();
            animatePet();
            FXGL.getGameScene().addUINode(uiFactory.getUi("DEAD", clockBar));
            deleteSaveFile();
            saveUser();
        }
    }

    public static void deleteSaveFile() {File f = new File("saveFile.txt"); f.delete();}

    public static void main(String[] args) {
        launch(args);
    }
}