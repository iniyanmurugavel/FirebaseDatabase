package com.user.firebasedatabase.Adapter.ArgoAdvisor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.firebasedatabase.R;

public class ArgoViewHolder extends RecyclerView.ViewHolder {

    public TextView name, address,mobileno;

Button call;
    ArgoViewHolder(View itemView) {
        super(itemView);

        mobileno = (TextView) itemView.findViewById(R.id.mobileno);
        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        call=(Button)itemView.findViewById(R.id.call);

    }

}

