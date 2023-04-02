package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;

public class Background extends Item {
    Image backgroundImage;

    public Background(String name, int price, String backgroundName) {
        setName(name);
        setPrice(price);
        Image image = getAssetLoader().loadImage(backgroundName);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(17 * 16);
        imageView.setFitWidth(32 * 16);

    }
}
