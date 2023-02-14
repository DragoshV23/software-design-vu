package softwaredesign;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SimpleFactory implements EntityFactory {

    @Spawns("Tamagotchi")
    public Entity newTamagotchi(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view("testotchi.png")
                .with(new ProjectileComponent(new Point2D(1,0), 69))
                .build();
    }
}
