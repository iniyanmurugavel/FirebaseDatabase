package com.user.firebasedatabase.admin;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.user.firebasedatabase.Pojo.AddCropPojo;
import com.user.firebasedatabase.R;

import java.io.IOException;

public class AddCrop extends AppCompatActivity {
    String txt_key = "", image = "";

    Button submit;
    MaterialEditText cropname, scheduleprice, marketprice, quantity;

    private static final int PICK_IMAGE_REQUEST = 234;

    //track Choosing Image Intent
    private static final int CHOOSING_IMAGE_REQUEST = 1234;
    //view objects
    private Button buttonChoose;
    private Button buttonUpload;
    private EditText editTextName;
    private TextView textViewShow;
    private ImageView imageView;
    LinearLayout layout_button;
    //uri to store file
    private Uri filePath;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    //firebase objects

    public static final String STORAGE_PATH_UPLOADS = "uploads/";
    public static final String DATABASE_PATH_UPLOADS = "uploads";
    //progress dialog
    private ProgressDialog progressDialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("CropDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crop);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        cropname = findViewById(R.id.cropname);
        scheduleprice = findViewById(R.id.scheduleprice);
        marketprice = findViewById(R.id.marketprice);
        quantity = findViewById(R.id.quantity);
        submit = findViewById(R.id.submit);
        buttonChoose = (Button) findViewById(R.id.btnChoose);
        buttonUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        layout_button = findViewById(R.id.layout_button);

        Intent intent = getIntent();
        if (intent != null) {
            String txt_cropname = intent.getStringExtra("cropname");
            String txt_marketprice = intent.getStringExtra("marketprice");
            String txt_scheduleprice = intent.getStringExtra("scheduleprice");
            image = intent.getStringExtra("image");
            txt_key = intent.getStringExtra("key");
            cropname.setText(txt_cropname);
            scheduleprice.setText(txt_scheduleprice);
            marketprice.setText(txt_marketprice);
            submit.setText("Update");
            layout_button.setVisibility(View.GONE);
        }


        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(cropname.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "Enter cropname", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(scheduleprice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter scheduleprice", Toast.LENGTH_SHORT).show();


                    return;
                }
                if (TextUtils.isEmpty(marketprice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter marketprice", Toast.LENGTH_SHORT).show();


                    return;
                }

                if (submit.getText().toString().equalsIgnoreCase("Update")) {


                    AddCropPojo cropPojo = new AddCropPojo(cropname.getText().toString(), marketprice.getText().toString(), scheduleprice.getText().toString(), image);

                    myRef.child(txt_key).setValue(cropPojo);
                    startActivity(new Intent(AddCrop.this, ViewCropDetails.class));
                    finish();


                } else {
                    uploadFile();
                }


            }
        });

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading


            if (!validateInputFileName(filePath.toString())) {
                return;
            }
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageReference.child(STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                            // Upload upload = new Upload(editTextName.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());

                            //Log.e("ssss", "Uri: " +downloadUrl);


                            storageReference.child("uploads/" + taskSnapshot.getMetadata().getName()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    Log.e("ssss", "Uri: " + uri);

                                    AddCropPojo cropPojo = new AddCropPojo(cropname.getText().toString(), marketprice.getText().toString(), scheduleprice.getText().toString(), uri.toString());

                                    myRef.push().setValue(cropPojo);
                                    startActivity(new Intent(AddCrop.this, ViewCropDetails.class));
                                    finish();
                                    // Got the download URL for 'users/me/profile.png'
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }


    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private boolean validateInputFileName(String fileName) {

        if (TextUtils.isEmpty(fileName)) {
            Toast.makeText(AddCrop.this, "Enter file name!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
