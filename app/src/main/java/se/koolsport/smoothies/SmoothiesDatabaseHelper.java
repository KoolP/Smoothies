package se.koolsport.smoothies;

/**
 * Created by Hinnenberg on 2018-02-10.
 */

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

import se.koolsport.smoothie.R;


public class SmoothiesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "smoothies";


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
        insertSmoothie(db, "Orange T-Shirt", "You survive in prison", "Blood Orange, Carrot, Citron, rose hi juice, Ice", R.drawable.tangerine);
        insertSmoothie(db, "Butt Kick", "Wakes up the sleepy one", "Ginger, Citron, Homemade syrup, Water (just a bit)", R.drawable.citron);
        insertSmoothie(db, "Pecan Milk", "Big Pecca energy milk", "Pecan, Honey, water", R.drawable.pecan);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    private static void insertSmoothie(SQLiteDatabase db, String name, String description, String ingredients, int resourceID) {
        ContentValues smoothieValues = new ContentValues();
        smoothieValues.put("NAME", name);
        smoothieValues.put("DESCRIPTION", description);
        smoothieValues.put("INGREDIENTS", ingredients);
        smoothieValues.put("IMAGE_RESOURCE_ID", resourceID);
        db.insert("SMOOTHIES", null, smoothieValues);
    }

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