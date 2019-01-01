package com.user.firebasedatabase.Adapter.ArgoAdvisor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.user.firebasedatabase.Adapter.CropDetails.ViewCropAdapter;
import com.user.firebasedatabase.Pojo.AdvisorList;
import com.user.firebasedatabase.Pojo.CropList;
import com.user.firebasedatabase.Pojo.WeatherForecast.WeatherForecastResult;
import com.user.firebasedatabase.R;
import com.user.firebasedatabase.admin.AddAdvisor;
import com.user.firebasedatabase.admin.AddCrop;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ArgoAdapter extends RecyclerView.Adapter<ArgoViewHolder> {
    private Context context;
    private List<AdvisorList> cropLists;
    String type;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public ArgoAdapter(Context context, List<AdvisorList> cropLists, String type) {
        this.context = context;
        this.cropLists = cropLists;
        this.type = type;
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("ArgoAdvisor");
    }

    @NonNull
    @Override
    public ArgoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_argo_details, parent, false);

        return new ArgoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArgoViewHolder viewCropAdapter, int i) {


        int position = i;
Log.e(TAG,type);
        if (type.equalsIgnoreCase("user")){
            viewCropAdapter.call.setVisibility(View.GONE);}
        else {
            viewCropAdapter.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = cropLists.get(position).getCropPojos().get(position).getMobileno();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    context.startActivity(intent);
                }
            });

            viewCropAdapter.call.setVisibility(View.VISIBLE);
        }
        viewCropAdapter.name.setText("Name :" + cropLists.get(position).getCropPojos().get(position).getAdvisorname());
        viewCropAdapter.address.setText("Address :" + cropLists.get(position).getCropPojos().get(position).getAddress());
        viewCropAdapter.mobileno.setText("Mobile No :" + cropLists.get(position).getCropPojos().get(position).getMobileno());


    }

    @Override
    public int getItemCount() {
        return cropLists.size();
    }

    public void addItem(AdvisorList list, int position) {


        Intent intent = new Intent(context, AddAdvisor.class);
        intent.putExtra("name", cropLists.get(position).getCropPojos().get(position).getAdvisorname());
        intent.putExtra("address",  cropLists.get(position).getCropPojos().get(position).getAddress());
        intent.putExtra("mobileno",  cropLists.get(position).getCropPojos().get(position).getMobileno());
        intent.putExtra("key",  cropLists.get(position).getCropKey().get(position));

        context.startActivity(intent);
//        notifyItemInserted(cropLists.size());
    }

    public void removeItem(int position) {
        myRef.child(cropLists.get(position).getCropKey().get(position)).removeValue();
        cropLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cropLists.size());

    }
}
