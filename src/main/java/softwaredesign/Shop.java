package softwaredesign;

import java.util.ArrayList;

public class Shop {
    String name;
    ArrayList<Item> stock = new ArrayList<>();
    public Shop(String name, ArrayList<Item> stock) {
        this.name = name;
        this.stock = stock;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {return name; }

    public ArrayList<Item> getStock(){return stock; }

    public void pay(Item item){}

    public Boolean checkBalance(int price, int balance){return false; }


}
