package softwaredesign;

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements Serializable {
    static volatile User instance;
    private int balance;
    private String activeBackground;
    public User() {
        this.balance = 0;
        this.activeBackground = "Default";
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

    public boolean pay(Item item) {
        if (this.balance >= item.getPrice()) {this.balance -= item.getPrice(); return true;}
        else {return false; }
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getBalance(){return balance; }

    public String getActiveBackground() {return this.activeBackground; }
    public void setActiveBackground(String background) {this.activeBackground = background; }
    public void playMiniGame(MiniGame minigame){}
}
