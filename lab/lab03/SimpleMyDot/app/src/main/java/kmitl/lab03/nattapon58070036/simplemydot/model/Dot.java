package kmitl.lab03.nattapon58070036.simplemydot.model;


/**
 * Created by student on 8/25/2017 AD.
 */

public class Dot {

    public interface OnDotChangeListener {
        void onDotChanged(Dot dot);
    }

    private OnDotChangeListener listener;

    public void setListener(OnDotChangeListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int color;
    private int red;
    private int green;
    private int blue;

    public Dot(OnDotChangeListener listener,
               int centerX, int centerY, int radius, int r, int g, int b) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.red = r;
        this.green = g;
        this.blue = b;
        this.listener.onDotChanged(this);
    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        this.listener.onDotChanged(this);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
