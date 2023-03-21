package softwaredesign;

public class User {
    int balance;

    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getBalance(){return balance; }

    public Boolean buy(Item item){return false; }
    public void playMiniGame(MiniGame minigame){}
}
