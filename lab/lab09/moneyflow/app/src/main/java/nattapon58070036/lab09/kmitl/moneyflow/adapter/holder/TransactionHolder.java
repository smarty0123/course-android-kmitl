package nattapon58070036.lab09.kmitl.moneyflow.adapter.holder;

/**
 * Created by SMART on 8/11/2560.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import nattapon58070036.lab09.kmitl.moneyflow.R;


public class TransactionHolder extends RecyclerView.ViewHolder {

    public View rootLayout;
    public TextView sign;
    public TextView textDescribe;
    public TextView textAmount;

    public TransactionHolder(View itemView) {
        super(itemView);
        rootLayout = itemView.findViewById(R.id.rootLayout);
        sign = itemView.findViewById(R.id.sign);
        textDescribe = itemView.findViewById(R.id.textDescribe);
        textAmount = itemView.findViewById(R.id.textAmount);
    }
}
