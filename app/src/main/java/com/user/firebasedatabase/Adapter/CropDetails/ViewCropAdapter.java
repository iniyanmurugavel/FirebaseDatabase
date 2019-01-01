package com.user.firebasedatabase.Adapter.CropDetails;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.firebasedatabase.R;

public class ViewCropAdapter extends RecyclerView.ViewHolder {

    public TextView cropname, marketprice,scheduleprice;
    public ImageView imageView;
CardView cardview;

    public ViewCropAdapter(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        marketprice = (TextView) itemView.findViewById(R.id.marketprice);
        scheduleprice = (TextView) itemView.findViewById(R.id.scheduleprice);
        cropname = (TextView) itemView.findViewById(R.id.cropname);
        cardview = (CardView) itemView.findViewById(R.id.cardview);


    }

}
