package kmitl.lab05.nattapon58070036.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class Dots implements Parcelable {

    public void setAllDot(ArrayList<Dot> allDot) {
        this.allDot = allDot;
    }

    public void set(int i, Dot dot) {
        this.allDot.set(i, dot);
        this.listener.onDotsChanged(this);
    }

    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();
    private List<Dot> backupDot = new ArrayList<>();
    public List<Dot> getAllDot() {
        return allDot;
    }


    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        backupDot.add(allDot.get(position));
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        backupDot = new ArrayList<>(allDot);
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < allDot.size(); i++) {
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.pow(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2), 0.5);
            if (distance <= allDot.get(i).getRadius()) {
                return i;
            }
        }
        return -1;
    }

    public void undoDot(){
        if(allDot.isEmpty()){
            allDot = new ArrayList<>(backupDot);
            backupDot.clear();
        }else {
            if(backupDot.isEmpty()){
                allDot.remove(allDot.size()-1);
            }else{
                allDot.add(backupDot.get(0));
                backupDot.remove(0);
            }

        }
        this.listener.onDotsChanged(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeParcelable((Parcelable) this.listener, flags);
        dest.writeList(this.allDot);
        dest.writeList(this.backupDot);

    }

    public Dots() {
    }

    protected Dots(Parcel in) {
        this.listener = in.readParcelable(OnDotsChangeListener.class.getClassLoader());
        this.allDot = new ArrayList<Dot>();
        in.readList(this.allDot, Dot.class.getClassLoader());
        this.backupDot = new ArrayList<Dot>();
        in.readList(this.backupDot, Dot.class.getClassLoader());
    }

    public static final Parcelable.Creator<Dots> CREATOR = new Parcelable.Creator<Dots>() {
        @Override
        public Dots createFromParcel(Parcel source) {
            return new Dots(source);
        }

        @Override
        public Dots[] newArray(int size) {
            return new Dots[size];
        }
    };
}