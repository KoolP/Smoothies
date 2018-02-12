package se.koolsport.smoothies;

import java.util.UUID;
/**
 * Created by Hinnenberg on 2018-02-12.
 */

public class FoodsDbModel {

    private int foodsId;
    private String foodsName;
    private String foodsDescription;
    private String foodsIngredients;

    public FoodsDbModel() {
    }

    public FoodsDbModel(int foodsId, String foodsName, String foodsDescription, String foodsIngredients) {

        this.foodsId = foodsId;
        this.foodsName = foodsName;
        this.foodsDescription = foodsDescription;
        this.foodsIngredients = foodsIngredients;
    }

    public int getFoodsId() {
        return foodsId;
    }
    public void setFoodsId(int foodslistId) {
        this.foodsId = foodslistId;
    }
    public String getFoodsName() {
        return foodsName;
    }
    public void setFoodsName(String foodsName) {
        this.foodsName = foodsName;
    }
    public String getFoodsDescription() {
        return foodsDescription;
    }
    public void setFoodsDescription(String foodsDescription) { this.foodsDescription = foodsDescription; }
    public String getFoodsIngredients() { return foodsIngredients; }
    public void setFoodsIngredients(String foodsIngredients) { this.foodsIngredients = foodsIngredients; }


}
