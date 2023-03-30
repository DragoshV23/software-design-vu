package softwaredesign;

public abstract class Item {

    private String name;

    private int price;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setPrice(int price) { this.price = price; }
    public int getPrice() { return price; }
}
