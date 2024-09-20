package org.example;

import java.awt.*;
import java.util.Random;

public class LightsModel {

    private Color colour;
    private int intensity;
//    private Timer timer;
    private int timer;
    private boolean shine;

    public LightsModel(){
        colour = Color.orange;
        intensity = 100;
        shine = true;
        timer = randTimer();
    }

    public LightsModel(Color inputColour, int inputIntensity, boolean inputShine, int inputTimer){
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

    public void setTimer(int newTimer){
        timer = newTimer;
    }

    public int getTimer(){
        return timer;
    }

    public int randTimer(){
        Random rand = new Random();
        int randomTimer = rand.nextInt(1000-100+1)+100;
        return randomTimer;
    }
}
