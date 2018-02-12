package se.koolsport.smoothies;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;


import se.koolsport.smoothie.R;

public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent, smoothie id user chose
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
        //Drink drink = Drink.DRINKS[drinkId];


        //Create cursor
        SQLiteOpenHelper smoothiesDatabaseHelper = new SmoothiesDatabaseHelper(this);
        try {
            SQLiteDatabase db = smoothiesDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("SMOOTHIES",
                    //new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    new String[] {"NAME", "DESCRIPTION", "INGREDIENTS", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)},
                    null, null, null);

            //Move to first record in the Cursor
            if (cursor.moveToFirst()) {

                //get the smoothie details from curos
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                String ingredientsText = cursor.getString(2);
                int photoId = cursor.getInt(3);

                //Populate the smoothie name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //Populate the smoothie description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate the smoothie ingredients
                TextView ingredients = (TextView)findViewById(R.id.ingredients);
                ingredients.setText(ingredientsText);


                //Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

            }

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }



        /*
        //Populate the drink name
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(drink.getName());

        //Populate the drink description
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(drink.getDescription());

        //Populate the drink image
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());
        */

    }
}
