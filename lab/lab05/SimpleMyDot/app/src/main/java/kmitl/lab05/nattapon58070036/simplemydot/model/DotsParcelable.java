package kmitl.lab05.nattapon58070036.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SMART on 20/9/2560.
 */

public class DotsParcelable implements Parcelable {
    private List<Dot> allDot = new ArrayList<>();

    public DotsParcelable(List<Dot> dot){
        this.allDot = dot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.allDot);
    }

    public DotsParcelable() {
    }

    protected DotsParcelable(Parcel in) {
        this.allDot = new ArrayList<Dot>();
        in.readList(this.allDot, Dot.class.getClassLoader());
    }

    public static final Parcelable.Creator<DotsParcelable> CREATOR = new Parcelable.Creator<DotsParcelable>() {
        @Override
        public DotsParcelable createFromParcel(Parcel source) {
            return new DotsParcelable(source);
        }

        @Override
        public DotsParcelable[] newArray(int size) {
            return new DotsParcelable[size];
        }
    };

    public List<Dot> getAllDot() {
        return allDot;
    }
}
