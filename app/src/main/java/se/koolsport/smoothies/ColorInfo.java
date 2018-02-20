package se.koolsport.smoothies;

/**
 * Created by Hinnenberg on 2018-02-16.
 */

public class ColorInfo {
    private int mColorImage;

    //Contsructor that passes values
    public ColorInfo(int colorImage) {
        mColorImage = colorImage;
    }

    //To get values from objects from do gettermethods
    public int getColorimage(){
        return mColorImage;
    }

}
