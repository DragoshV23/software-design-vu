package softwaredesign;

public final class Food extends Item {
    private int nutritionVal;
    private String label;

    public int getNutritionVal() {return nutritionVal; }

    public Food(String name, int price, int nutritionVal) {
        this.nutritionVal = nutritionVal;
        setName(name);
        setPrice(price);
        this.label = this.getName() + "\nPrice: $" + this.getPrice() +  "\nNutritional value: " + this.getNutritionVal();
    }
    public String getLabel() {return this.label;}
}

