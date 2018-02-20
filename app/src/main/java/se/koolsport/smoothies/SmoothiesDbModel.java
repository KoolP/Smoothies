package se.koolsport.smoothies;

/**
 * Created by Hinnenberg on 2018-02-18.
 * Class unused at the moment, test first to comment out before deleting
 */

public class SmoothiesDbModel {

    private int smoothieId;
    private String smoothieName;
    private String smoothieDescription;
    private String smoothiesIngredients;
    private int imageResourceId;

    public SmoothiesDbModel(int smoothieId, String smoothieName, String smoothieDescription, String smoothiesIngredients, int imageResourceId) {

    this.smoothieId = smoothieId;
    this.smoothieName = smoothieName;
    this.smoothieDescription = smoothieDescription;
    this.smoothiesIngredients = smoothiesIngredients;
    //this.imageResourceId = imageResourceId;

    }

    public SmoothiesDbModel() {
    }
    public int getSmoothieId() {return smoothieId;}
    public String getSmoothieName() { return smoothieName; }
    public String getSmoothieDescription() { return smoothieDescription; }
    public String getSmoothiesIngredients() { return smoothiesIngredients; }
    public int getImageResourceId() { return imageResourceId; }

    public void setSmoothieId(int smoothieId) { this.smoothieId = smoothieId; }
    public void setSmoothieName(String smoothieName) { this.smoothieName = smoothieName; }
    public void setSmoothieDescription(String smoothieDescription) { this.smoothieDescription = smoothieDescription; }
    public void setSmootiesIngredients(String smoothiesIngredients) { this.smoothiesIngredients = smoothiesIngredients; }
    public void setImageResourceId(int imageResourceId) {this.imageResourceId = imageResourceId; }


}
