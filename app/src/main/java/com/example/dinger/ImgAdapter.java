package com.example.dinger;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


public class ImgAdapter extends FirebaseRecyclerAdapter<PetImage,ImgAdapter.ImgViewholder> {


    public ImgAdapter(@NonNull FirebaseRecyclerOptions<PetImage> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull ImgViewholder holder, int position, @NonNull PetImage model) {
        holder.datetime.setText(model.getHour()+":"+model.getMinute()+":"+model.getSec()+"    "+model.getDy()+"/"+model.getMonth()+"/"+model.getYear());
        Picasso.get().load(model.getImages()).into(holder.img);
    }

    @NonNull
    @Override
    public ImgViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imgview, parent, false);
        return new ImgViewholder(view);
    }

    public static class ImgViewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView datetime;
        public ImgViewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.petimg);
            datetime = (TextView) itemView.findViewById(R.id.datetime);

        }
    }

}

