package softwaredesign;

public final class Food extends Item{
    int nutritionVal;
    int testVal;

    public int getNutritionVal() {return nutritionVal; }

    public Food(String name, int price, int nutritionVal){
        this.nutritionVal = nutritionVal;
        this.name = name;
        this.price = price;
    }
}
