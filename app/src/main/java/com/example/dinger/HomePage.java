package com.example.dinger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    private String mYear, mMonth, mDay, mHour, mMinute, mSec;
    ImageView imgView;
    String imgUrl=" ";
    static int pos=0;
    ImageButton likebutton, dislikebtn;
   private RecyclerView mrecyclerView;
   private ImgAdapter imgAdapter;
   //private ArrayList<PetImage> petimg;
  // private Context ct;
   //private ArrayAdapter<String> arrayAdapter;


    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        imgView = (ImageView) findViewById(R.id.imageView);
        int x = 0;
        FetchDataFromApi(x);

        likebutton = (ImageButton) findViewById(R.id.likebutton);
        dislikebtn = (ImageButton) findViewById(R.id.dislikebtn);


        likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 1;
                FetchDataFromApi(x);
            }
        });

        dislikebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 0;
                FetchDataFromApi(x);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        mrecyclerView = findViewById(R.id.recyclerview);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Images");


        FirebaseRecyclerOptions<PetImage> firebaseRecyclerOptions =
                new FirebaseRecyclerOptions.Builder<PetImage>()
                        .setQuery(databaseReference, PetImage.class)
                        .build();


        imgAdapter = new ImgAdapter(firebaseRecyclerOptions);
        //Log.d("success","migu ");
        imgAdapter.startListening();
        mrecyclerView.setAdapter(imgAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        imgAdapter.stopListening();

    }

    private void FetchDataFromApi(final int x)
    {
        if(x==1)
        {
            Dateset();
            Timeset();
            pos++;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("Images").child(String.valueOf(pos));
            PetImage petImage = new PetImage(imgUrl,mYear,mMonth+1,mDay,mHour,mMinute,mSec);
            myRef.setValue(petImage);
            myRef.onDisconnect().removeValue();

        }

        RequestQueue queue = Volley.newRequestQueue(HomePage.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://dog.ceo/api/breeds/image/random", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Log.d("Dog","Respons" + response.getString("message"));
                            imgUrl = response.getString("message");
                            //Log.d("Dog","Respons " +s );
                            Picasso.get().load(imgUrl).into(imgView);
                            //updateImgList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        //Log.d("Error","wrong");

                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void Dateset()
    {
        final Calendar c = Calendar.getInstance();
        mYear = String.valueOf(c.get(Calendar.YEAR));
        mMonth = String.valueOf(c.get(Calendar.MONTH));
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

    }

    private void Timeset(){
        final Calendar c = Calendar.getInstance();
        mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        mMinute = String.valueOf(c.get(Calendar.MINUTE));
        mSec = String.valueOf(c.get(Calendar.SECOND));
    }

}