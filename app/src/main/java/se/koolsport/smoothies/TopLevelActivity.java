package se.koolsport.smoothies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import se.koolsport.smoothie.R;
import se.koolsport.smoothies.FoodsDatabaseHelper;
import se.koolsport.smoothies.FoodsDbModel;

public class TopLevelActivity extends AppCompatActivity {

    FoodsDatabaseHelper fdbHelper = new FoodsDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Title color
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getColor(R.color.colorAccent));

        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        if (position == 0) {
                            Intent intent = new Intent(TopLevelActivity.this,
                                    DrinkCategoryActivity.class);
                            startActivity(intent);
                        }if (position == 1) {
                            fdbHelper.getAllFoodsItems();
                        }
                    }
                };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
