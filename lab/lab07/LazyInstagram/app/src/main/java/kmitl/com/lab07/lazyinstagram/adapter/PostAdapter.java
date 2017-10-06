package kmitl.com.lab07.lazyinstagram.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.com.lab07.lazyinstagram.MainActivity;
import kmitl.com.lab07.lazyinstagram.R;

/**
 * Created by student on 10/6/2017 AD.
 */
class Holder extends RecyclerView.ViewHolder{
    public ImageView image;
    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

    String[] data = {"https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n5.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n6.jpg"

    };
    private Context context;
    public PostAdapter(Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
