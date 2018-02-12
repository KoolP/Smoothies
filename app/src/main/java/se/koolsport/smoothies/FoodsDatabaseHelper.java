package se.koolsport.smoothies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.koolsport.smoothies.FoodsDbModel;

/**
 * Created by Hinnenberg on 2018-02-12.
 */

public class FoodsDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "foods.db";
    private static final String LOGTAG = "Food DB";

    FoodsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Foods table
    public static final String TABLE_FOODS = "foods";
    public static final String COLUMN_FOODS_ID = "foodsId";
    public static final String COLUMN_FOODS_NAME = "foodsName";
    public static final String COLUMN_FOODS_DESCRIPTION = "foodsDescription";
    public static final String COLUMN_FOODS_INGREDIENTS = "foodsIngredients";
    private static final String TABLE_FOODS_CREATE =
            "CREATE TABLE " + TABLE_FOODS + " (" +
                    COLUMN_FOODS_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_FOODS_NAME + " TEXT, " +
                    COLUMN_FOODS_DESCRIPTION + " TEXT, " +
                    COLUMN_FOODS_INGREDIENTS + " TEXT" + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_FOODS_CREATE);
        Log.i(LOGTAG, "Foods Table is created");

        db.execSQL("INSERT INTO foods (foodsName, foodsDescrition, foodsIngredients) " +
                "VALUES ('Porridge', 'A staple food', 'Oats'), " +
                "('Finnish Porridge', 'More staple food', 'Rye'), " +
                "('Oat Meal', 'Another staple food', 'Oats')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS foods");

        Log.i(LOGTAG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);
    }


    // Get all food items
    public List<FoodsDbModel> getAllFoodsItems(){
        List<FoodsDbModel> foodsItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(FoodsDatabaseHelper.TABLE_FOODS,null,null,null,null,null,null);
        Log.i(LOGTAG, "Count: Foods table items: " + c.getCount() + " rows");
        if(c.getCount() > 0){
            while(c.moveToNext()){
                FoodsDbModel foods = new FoodsDbModel();
                foods.setFoodsId(c.getInt(c.getColumnIndex(FoodsDatabaseHelper.COLUMN_FOODS_ID)));
                foods.setFoodsName(c.getString(c.getColumnIndex(FoodsDatabaseHelper.COLUMN_FOODS_NAME)));
                foods.setFoodsDescription(c.getString(c.getColumnIndex(FoodsDatabaseHelper.COLUMN_FOODS_DESCRIPTION)));
                foods.setFoodsIngredients(c.getString(c.getColumnIndex(FoodsDatabaseHelper.COLUMN_FOODS_INGREDIENTS)));
                foodsItems.add(foods);

                Log.i("Foods table items: ", foods.getFoodsId() + ", "
                        + foods.getFoodsName() + ", " + foods.getFoodsDescription());
            }
        }
        return foodsItems;
    }


}
