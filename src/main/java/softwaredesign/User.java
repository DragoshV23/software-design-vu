package softwaredesign;

import javafx.scene.image.Image;

public class User {
    private static volatile User instance;
    private int balance;
    private Background activeBackground;
    public User() {
        this.balance = 0;
        // asset loader from fxgl needed, thats why constructor does not initialize activeBackground
    }
    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new User();
                }
            }
        }
        return instance;
    }

    public boolean pay(Food food) {
        if (this.balance >= food.getPrice()) {this.balance -= food.getPrice(); return true;}
        else {return false; }
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getBalance(){return balance; }

    public Boolean buy(Item item){return false; }
    public Background getActiveBackground() {return this.activeBackground; }
    public void setActiveBackground(Background background) {this.activeBackground = background; }
    public void playMiniGame(MiniGame minigame){}
}
