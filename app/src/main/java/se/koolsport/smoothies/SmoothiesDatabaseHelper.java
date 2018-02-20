package se.koolsport.smoothies;

/**
 * Created by Hinnenberg on 2018-02-10.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.koolsport.smoothie.R;


public class SmoothiesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "smoothies";
    private static final String TAG = "Smoothie table test";

    public static final String TABLE_NAME = "SMOOTHIES";
    public static final String COLUMN_SMOOTHIE_ID = "smoothieId";
    public static final String COLUMN_SMOOTHIE_NAME = "smoothieName";
    public static final String COLUMN_SMOOTHIE_DESCRIPTION = "smoothieDescription";
    public static final String COLUMN_SMOOTHIE_INGREDIENTS = "smoothieIngredients";
    public static final String COLUMN_SMOOTHIE_RESOURCE_ID= "smoothieResourceId";

    SQLiteDatabase database;
    SQLiteOpenHelper dbhandler;


    SmoothiesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        //Smoothies table:
        db.execSQL("CREATE TABLE SMOOTHIES (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "DESCRIPTION TEXT," +
                "INGREDIENTS TEXT," +
                "IMAGE_RESOURCE_ID INTEGER);");
        insertSmoothie(db, "Berry Blues", "This is an everyday savour", "Blueberry, Raspberry, Pear (2x), Honey, Citron, Mint, Water or Juice", R.drawable.blueberry);
        insertSmoothie(db, "Orange Objects", "Makes you happy and satisfied", "Orange, Carrot, Sea Buckthorn, Orange or Tangerine juice, Ice", R.drawable.tangerine);
        insertSmoothie(db, "Orange T-Shirt", "You survive in prison", "Blood Orange, Carrot, Citron, rose hip juice, Ice", R.drawable.tangerine);
        insertSmoothie(db, "Butt Kick", "Wakes up the sleepy one", "Ginger, Citron, Homemade syrup, Water (just a bit)", R.drawable.citron);
        insertSmoothie(db, "Pecan Milk", "Big Pecca energy milk", "Pecan, Honey, water", R.drawable.pecan);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //private static void?
    public void insertSmoothie(SQLiteDatabase db, String name, String description, String ingredients, int resourceID) {

        ContentValues smoothieValues = new ContentValues();
        smoothieValues.put("NAME", name);
        smoothieValues.put("DESCRIPTION", description);
        smoothieValues.put("INGREDIENTS", ingredients);
        smoothieValues.put("IMAGE_RESOURCE_ID", resourceID);
        db.insert("SMOOTHIES", null, smoothieValues);
    }


    //testar
    //Delete Smoothie from table, was void but changed to boolean
    public boolean deleteSmoothie(SQLiteDatabase db, String name, String description, String ingredients, int resourceID) {
        ContentValues smoothieValues = new ContentValues();

        String[] selection = new String[] {Integer.toString(drinkId)};

        int result = db.delete("SMOOTHIES", "_id = ?", selection);

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }


    //Not in use, try to comment out before deleting
    public void addSmoothie(SmoothiesDbModel smoothiesDbModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues smoothieValues = new ContentValues();

        smoothieValues.put("NAME", smoothiesDbModel.getSmoothieName());
        smoothieValues.put("DESCRIPTION", smoothiesDbModel.getSmoothieDescription());
        smoothieValues.put("INGREDIENTS", smoothiesDbModel.getSmoothiesIngredients());
        smoothieValues.put("IMAGE_RESOURCE_ID", smoothiesDbModel.getImageResourceId());

        //Log.d(TAG, "addData: Adding " + smoothiesDbModel + " to " +
    }




    /*
    //TODO tying to fetch all data from smooties table
    // Get all smoothies items
    //public List<SmoothiesDbModel> getAllSmoothieItems(){
    //    List<SmoothiesDbModel> smoothiesItems = new ArrayList<>();
    //    SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = database.query(SmoothiesDatabaseHelper.DB_NAME,null,null,null,null,null,null);
        Log.i(LOGTAG, "Count: Smoothie table items: " + c.getCount() + " rows");
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                SmoothiesDbModel smoothies = new SmoothiesDbModel();
                smoothies.setSmoothieId(cursor.getInt(cursor.getColumnIndex(SmoothiesDatabaseHelper.NAME));
                smoothies.setSmoothieName(cursor.getString(cursor.getColumnIndex(SmoothiesDatabaseHelper.DESCRIPTION)));
                smoothies.setSmoothieDescription(cursor.getString(cursor.getColumnIndex(SmoothiesDatabaseHelper.COLUMN_FOODS_DESCRIPTION)));
                smoothies.setSmootiesIngredients(cursor.getString(cursor.getColumnIndex(SmoothiesDatabaseHelper.COLUMN_FOODS_INGREDIENTS)));
                smoothies.add(smoothies);

                Log.i("Foods table items: ", smoothies.getSmoothieId() + ", "
                        + SmoothiesDbModel.getSmoothieName() + ", " + SmoothiesDbModel.getSmoothieDescription());
            }
        }
        return SmoothiesDbModel;
    }*/

}



//Update database version, instead of the original oncreate.
// Will add a new column to table, + Delete all, db.delete("SMOOTHIES", null, null);

    /*
     * Update database only 1/4 steps, change version:
     * private static final int DB_VERSION = 2;
     */


    /*
     * Update database only 2/4
     * @Override
     * public void onCreate(SQLiteDatabase db) {
     *      updateSmoothiesDatabase (db, 0, DB_VERSION);
     * }
     */


    /*
     * Update database only 3/4
     * @Override
     * public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     * updateSmoothiesDatabase(db, oldVersion, newVersion);
     * }
     */


    /*
     * Update database only 4/4
     * public void onCreate(SQLiteDatabase db) {

        if (oldVersion > 1) {
        db.execSQL("CREATE TABLE SMOOTHIES (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "DESCRIPTION TEXT," +
                "INGREDIENTS TEXT," +
                "IMAGE_RESOURCE_ID INTEGER);");
        insertSmoothie(db, "Berry Blues", "This is an everyday savour", "Blueberry, Raspberry, Pear (2x), Honey, Citron, Mint, Water or Juice", R.drawable.blueberry);
        insertSmoothie(db, "Orange Objects", "Makes you happy and satisfied", "Orange, Carrot, Sea Buckthorn, Orange or Tangerine juice, Ice", R.drawable.tangerine);
        insertSmoothie(db, "Butt Kick", "Wakes up the sleepy one", "Ginger, Citron, Homemade syrup, Water (just a bit)", R.drawable.citron);
        insertSmoothie(db, "Pecan Milk", "Big Pecca energy milk", "Pecan, Honey, water", R.drawable.pecan);
        db.delete("SMOOTHIES", null, null);
        }

        if(oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN TASTESTARS NUMERIC;");
     *  }
     *  }
     */

    /*
//UPDATE, DELETE, ALTER, examples
ContentValues smoothieValues = new ContentValues();
smoothieValues.put("Description", "Only on Thusdays");
db.update("SMOOTHIES",
        smoothieValues,
        "NAME = ?",
        new String[] {"Berry Blues"});

db.delete("SMOOTHIES","NAME = ?",
        new String[] {"Berry Blues"});

DROP TABLE SMOOTHIES
    */