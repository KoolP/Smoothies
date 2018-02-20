package se.koolsport.smoothies;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import se.koolsport.smoothie.R;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkId";

    private int drinkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        toolbarStylez();

        //Get the drink from the intent, smoothie id user chose
        drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);


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

                //get the smoothie details from cursor
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_delete_drink) {
            Log.d("TEST","Delete clicked!");
            SmoothiesDatabaseHelper smoothiesDatabaseHelper = new SmoothiesDatabaseHelper(this);
            SQLiteDatabase db = smoothiesDatabaseHelper.getWritableDatabase();
            smoothiesDatabaseHelper.deleteSmoothie(db,"","","",drinkId);
            Intent intent = new Intent(this, DrinkCategoryActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    //Inflates menu adds items to the appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drink, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void toolbarStylez() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TitleText color
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getColor(R.color.colorAccent));
        //Adding the back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
