package com.user.firebasedatabase.Adapter.CropDetails;

import android.content.Context;
import android.content.Intent;
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
import com.user.firebasedatabase.Pojo.CropList;
import com.user.firebasedatabase.Pojo.WeatherForecast.WeatherForecastResult;
import com.user.firebasedatabase.R;
import com.user.firebasedatabase.UserModule.ProducerAdd;
import com.user.firebasedatabase.admin.AddCrop;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ViewCropMainAdapter extends RecyclerView.Adapter<ViewCropAdapter> {
    private Context context;
    private List<CropList> cropLists;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String type = "";

    public ViewCropMainAdapter(Context context, List<CropList> cropLists, String type) {
        this.context = context;
        this.cropLists = cropLists;
        database = FirebaseDatabase.getInstance();
        this.type = type;
        myRef = database.getReference("CropDetails");
    }

    @NonNull
    @Override
    public ViewCropAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_crop_details, parent, false);

        return new ViewCropAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCropAdapter viewCropAdapter, int i) {


        int position = i;


        viewCropAdapter.cropname.setText("Crop Name :" + cropLists.get(position).getCropPojos().get(position).getCropname());
        viewCropAdapter.marketprice.setText("Market Price :" + cropLists.get(position).getCropPojos().get(position).getMarketprice());
        viewCropAdapter.scheduleprice.setText("Schedule Price :" + cropLists.get(position).getCropPojos().get(position).getScheduleprice());
       Log.e(TAG,type);

        if (type.equalsIgnoreCase("NoAction")) {
            viewCropAdapter.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(context, ProducerAdd.class);
                    i.putExtra("key", cropLists.get(position).getCropKey().get(0));
                    i.putExtra("cropname", cropLists.get(position).getCropPojos().get(position).getCropname());
                    i.putExtra("marketprice", cropLists.get(position).getCropPojos().get(position).getMarketprice());
                    i.putExtra("scheduleprice", cropLists.get(position).getCropPojos().get(position).getScheduleprice());
                   i.putExtra("image", cropLists.get(position).getCropPojos().get(position).getImage());
                    i.putExtra("type","User");
                    context.startActivity(i);
                }
            });

        }


        Log.e(TAG, "" + cropLists.get(position).getCropPojos().get(position).getImage());
        Glide.with(context).load(cropLists.get(position).getCropPojos().get(position).getImage()).into(viewCropAdapter.imageView);
    }

    @Override
    public int getItemCount() {
        return cropLists.size();
    }

    public void addItem(CropList list, int position) {


        Intent intent = new Intent(context, AddCrop.class);
        intent.putExtra("cropname", cropLists.get(position).getCropPojos().get(position).getCropname());
        intent.putExtra("marketprice",  cropLists.get(position).getCropPojos().get(position).getMarketprice());
        intent.putExtra("scheduleprice",  cropLists.get(position).getCropPojos().get(position).getScheduleprice());
        intent.putExtra("key", cropLists.get(position).getCropKey().get(position));
        intent.putExtra("image",  cropLists.get(position).getCropPojos().get(position).getImage());
        context.startActivity(intent);
//        notifyItemInserted(cropLists.size());
    }

    public void removeItem(int position) {
        Log.e(TAG, "----------" + cropLists.get(position).getCropKey().get(position));
        myRef.child(cropLists.get(position).getCropKey().get(position)).removeValue();
        cropLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cropLists.size());

    }
}
