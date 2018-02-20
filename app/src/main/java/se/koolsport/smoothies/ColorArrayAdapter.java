package se.koolsport.smoothies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import se.koolsport.smoothie.R;

/**
 * Created by Hinnenberg on 2018-02-16.
 */

public class ColorArrayAdapter extends ArrayAdapter<ColorInfo> {

    public ColorArrayAdapter(Context context, ArrayList<ColorInfo> colorList) {
        super(context, 0, colorList);

        //Now passing to context and color list to superclass, de don't need to refer them in this class
        //in other words no member variables for context or array list
        //They can simply get accessed via getContext or getItem and passing a position

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    //Both get View and getDropDownView in thisoverrides in same
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.color_spinner_row, parent, false
            );
        }

        ImageView imageViewColor = convertView.findViewById(R.id.image_view_color);

        ColorInfo currentColor = getItem(position);

        //Warns as it can theoratically produce nullpoint exeption, it used
        if (currentColor != null) {
            imageViewColor.setImageResource(currentColor.getColorimage());
        }
        return convertView;
    }

}
