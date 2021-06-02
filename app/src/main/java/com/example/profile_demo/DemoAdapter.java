package com.example.profile_demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
     ArrayList<javabeen> ViewList;
     Context ctx;

    public DemoAdapter(ArrayList<javabeen> ViewList, Context ctx) {
        this.ViewList = ViewList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_content,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            javabeen items = ViewList.get(position);
            String imgurl = items.getAvatar();
            String fname = items.getFirst_name();
            String lname= items.getLast_name();
            String email = items.getEmail();

             holder.fname.setText(fname);
             holder.lname.setText(lname);
             holder.email.setText(email);
             Picasso.get().load(imgurl).fit().centerInside().into(holder.imageView);

             /////click event
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent= new Intent(ctx,profileView.class);
                 intent.putExtra("id",items.getId());
                 intent.putExtra("avatar",items.getAvatar());
                 intent.putExtra("fname",items.getFirst_name());
                 intent.putExtra("lname",items.getLast_name());
                 intent.putExtra("email",items.getEmail());

                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 ctx.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return ViewList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fname,lname,email;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.card);
            fname = itemView.findViewById(R.id.tvname);
            lname = itemView.findViewById(R.id.tvlastname);
            email = itemView.findViewById(R.id.tvemail);
            imageView = itemView.findViewById(R.id.imgprofile);
        }
    }

}
