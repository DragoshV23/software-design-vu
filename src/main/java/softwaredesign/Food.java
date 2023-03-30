package softwaredesign;

public final class Food extends Item {
    private int nutritionVal;

    public int getNutritionVal() {return nutritionVal; }

    public Food(String name, int price, int nutritionVal) {
        this.nutritionVal = nutritionVal;
        setName(name);
        setPrice(price);
    }
}

