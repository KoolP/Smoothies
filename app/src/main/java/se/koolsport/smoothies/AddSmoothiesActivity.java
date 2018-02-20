package se.koolsport.smoothies;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.Menu;
import java.util.ArrayList;
import se.koolsport.smoothie.R;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class AddSmoothiesActivity extends AppCompatActivity {

    private ArrayList<ColorInfo> mColorList;
    private ColorArrayAdapter mAdapter;
    private EditText smoothieName;
    private EditText smoothieAbout;
    private EditText smoothieIngredients;
    private Spinner colorSpinner;

    private String name;
    private String about;
    private String ingredients;
    private int colorId;
    //private int colorId;
    SmoothiesDatabaseHelper smoothiesDbHelper = new SmoothiesDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_smoothies);
        toolbarStylez();
        findViews();

        //Create spinner array list and its items
        initList();
        Spinner spinnerColors = findViewById(R.id.spinner);
        //instance of custom adapter, passs context and array of item
        mAdapter = new ColorArrayAdapter(this,mColorList);
        //this sets coloradapter on color spinner and displays items
        spinnerColors.setAdapter(mAdapter);
        //clickevents
        spinnerColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ColorInfo clickedItem = (ColorInfo) parent.getItemAtPosition(position);
                int clickedCountry = clickedItem.getColorimage();
                Toast.makeText(AddSmoothiesActivity.this, clickedCountry + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void toolbarStylez() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TitleText color
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getColor(R.color.colorAccent));
        //Adding the back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        smoothieName = findViewById(R.id.editText_smoothieName);
        smoothieAbout = findViewById(R.id.editText_smoothieQuote);
        smoothieIngredients = findViewById(R.id.editText_smoothieIngredients);
        colorSpinner = findViewById(R.id.spinner);
    }

    private void initList() {
        mColorList = new ArrayList<>();
        mColorList.add(new ColorInfo(R.drawable.blueberry));
        mColorList.add(new ColorInfo(R.drawable.citron));
        mColorList.add(new ColorInfo(R.drawable.pecan));
        mColorList.add(new ColorInfo(R.drawable.tangerine));
    }

    //Inflates menu adds items to the appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_smoothie, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void onClickSaveSmoothie(View view) {
        //Get reference to the database and insert (add new records)
        SmoothiesDatabaseHelper smoothiesDbHelper = new SmoothiesDatabaseHelper(this);

        ContentValues values = new ContentValues();
        SQLiteDatabase db = smoothiesDbHelper.getWritableDatabase();
        name = smoothieName.getText().toString();
        about = smoothieAbout.getText().toString();
        ingredients = smoothieIngredients.getText().toString();
        //colorId = (int) colorSpinner.getSelectedItem();

        //addSmoothie();
        smoothiesDbHelper.insertSmoothie(db, name, about, ingredients, colorId);

    }

    /*
    public void addSmoothie() {
        SmoothiesDbModel smoothiesDbModel = new SmoothiesDbModel(name, about, ingredients, colorId);
        smoothiesDbHelper.addSmoothie(smoothiesDbModel);
    }*/

    //Save name of Smoohtie
    private String saveSmoothieName() {
        return smoothieName.getText().toString();
    }
    //Save quote/comment/about of smoothie
    private String saveSmoothieAbout() {
        return smoothieAbout.getText().toString();
    }
    //Save ingredients of smoothie
    private String saveSmoothieIngredients() {
        return smoothieIngredients.getText().toString();
    }
    //Save value of spinner
    private int saveSpinnerValue() {
        return (int) colorSpinner.getSelectedItem();
    }


    //When action is clicked
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_smoothie:
                Intent intent = new Intent(this, DrinkCategoryActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
