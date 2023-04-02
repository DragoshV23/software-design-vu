package softwaredesign;

public class User {
    private static volatile User instance;
    private int balance;
    public User() {
        this.balance = 0;
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
    public void playMiniGame(MiniGame minigame){}
}
