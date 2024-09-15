package org.example;

import java.awt.*;

public class LightsModel {

    private Color colour;
    private int intensity;
//    private Timer timer;
    private boolean shine;

    public LightsModel(){
        colour = Color.orange;
        intensity = 100;
        shine = true;
    }

    public LightsModel(Color inputColour, int inputIntensity, boolean inputShine){
        colour = inputColour;
        intensity = inputIntensity;
        shine = inputShine;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public boolean isShine() {
        return shine;
    }

    public void setShine(boolean shine) {
        this.shine = shine;
    }
}
