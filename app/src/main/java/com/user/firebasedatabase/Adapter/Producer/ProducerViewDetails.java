package com.user.firebasedatabase.Adapter.Producer;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.firebasedatabase.R;

public class ProducerViewDetails extends RecyclerView.ViewHolder {

    public TextView marketprice,deliveryoption ,cropname, scheduleprice, quantity, total;
    ImageView imageView;
    Button cardview;

    ProducerViewDetails(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        marketprice = (TextView) itemView.findViewById(R.id.marketprice);
        scheduleprice = (TextView) itemView.findViewById(R.id.scheduleprice);
        cropname = (TextView) itemView.findViewById(R.id.cropname);
        cardview = (Button) itemView.findViewById(R.id.cardview);

        deliveryoption = (TextView) itemView.findViewById(R.id.deliveryoption);

        quantity = (TextView) itemView.findViewById(R.id.quantity);
        total = (TextView) itemView.findViewById(R.id.price);


    }

}

