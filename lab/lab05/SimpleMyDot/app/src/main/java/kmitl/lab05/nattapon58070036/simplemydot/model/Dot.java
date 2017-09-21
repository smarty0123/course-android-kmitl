package kmitl.lab05.nattapon58070036.simplemydot.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;


public class Dot implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    public Dot(int centerX, int centerY, int radius) {
        this(centerX, centerY, radius, Color.RED);
    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.centerX);
        dest.writeInt(this.centerY);
        dest.writeInt(this.radius);
        dest.writeInt(this.color);
    }

    protected Dot(Parcel in) {
        this.centerX = in.readInt();
        this.centerY = in.readInt();
        this.radius = in.readInt();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<Dot> CREATOR = new Parcelable.Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel source) {
            return new Dot(source);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };
}