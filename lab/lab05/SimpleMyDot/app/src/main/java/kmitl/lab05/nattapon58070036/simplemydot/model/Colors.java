package kmitl.lab05.nattapon58070036.simplemydot.model;

import android.graphics.Color;

import java.util.Random;

public class Colors {
    public Colors() {
    }

    public int getColor() {
        int red = new Random().nextInt(255);
        int green = new Random().nextInt(255);
        int blue = new Random().nextInt(255);
        return Color.rgb(red, green, blue);
    }
}