package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;

public class Background extends Item {
    private Image backgroundImage;

    public Background(String name, int price, String backgroundName) {
        setName(name);
        setPrice(price);
        Image image = getAssetLoader().loadImage(backgroundName);
        this.backgroundImage = image;
    }

    public Image getBackgroundImage() {return backgroundImage; }
}
