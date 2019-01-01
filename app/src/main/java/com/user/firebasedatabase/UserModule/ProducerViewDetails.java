package com.user.firebasedatabase.UserModule;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.firebasedatabase.Adapter.CropDetails.ViewCropMainAdapter;
import com.user.firebasedatabase.Adapter.Producer.ProductViewAdapter;
import com.user.firebasedatabase.Pojo.AddCropPojo;
import com.user.firebasedatabase.Pojo.CropList;
import com.user.firebasedatabase.Pojo.ProductAddPojo;
import com.user.firebasedatabase.Pojo.ProductList;
import com.user.firebasedatabase.R;
import com.user.firebasedatabase.admin.ViewCropDetails;

import java.util.ArrayList;
import java.util.List;

public class ProducerViewDetails extends AppCompatActivity {

    String TAG = ViewCropDetails.class.getSimpleName();

    String type = "User";
    private List<ProductAddPojo> addCropPojoList = new ArrayList<>();
    private List<String> addKeyList = new ArrayList<>();
    ProductViewAdapter mAdapter;

    private List<ProductList> cropLists = new ArrayList<>();

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private View view;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_view_details);


        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DatabaseReference myRef = database.getReference("ProductDetails");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(getIntent()!=null) {
            String user=getIntent().getStringExtra("type");
            Log.e(TAG,"NoAction"+user);
            if (user.equalsIgnoreCase("User") && !TextUtils.isEmpty(user)) {
                initSwipe();
            }

        }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                if (addCropPojoList.size() > 0) {
//                    addCropPojoList.clear();
//                    addKeyList.clear();
                for (DataSnapshot post : dataSnapshot.getChildren()) {


                    ProductAddPojo cAd = post.getValue(ProductAddPojo.class);
                    Log.e(TAG, "Error trying to get classified ad for update " +
                            "" + cAd.getCropname());
                    addCropPojoList.add(cAd);

                    addKeyList.add(post.getKey());

                    Log.e(TAG, "Iniyan" + addCropPojoList.size() + "----" + post.getKey());
                    ProductList cropList = new ProductList(addCropPojoList, addKeyList);
                    cropLists.add(cropList);
                    Log.e(TAG, "" + cropLists.size());
                }


                runLayoutAnimation(recyclerView);

            }


            //}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error trying to get classified ad for update " +
                        "" + databaseError);
            }
        });

    }


    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_left);

        mAdapter = new ProductViewAdapter(ProducerViewDetails.this, cropLists, getIntent().getStringExtra("type"));
        recyclerView.setAdapter(mAdapter);

        // mAdapter.notifyDataSetChanged();


        //   adapter.notifyDataSetChanged();
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    mAdapter.removeItem(position);
                } else if (direction == ItemTouchHelper.RIGHT) {

//                    edit_position = position;
//                    alertDialog.setTitle("Edit Country");
//                    et_country.setText(countries.get(position));
//                    alertDialog.show();
                    mAdapter.addItem(cropLists.get(position), position);
                    Log.e(TAG, "---" + cropLists.get(position).getCropPojos().get(position).getTotalamount());
                }

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);


                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);

        }
    }
}
