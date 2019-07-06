package com.example.pizzalovers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pizzalovers.Models.Pizza;
import com.example.pizzalovers.Activities.PizzaDetails;
import com.example.pizzalovers.R;

import java.util.List;
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext ;
    private List<Pizza> mData ;
    RequestOptions option;


        public RecyclerViewAdapter(Context mContext, List<Pizza> mData) {
            this.mContext = mContext;
            this.mData = mData;

            // Request option for Glide
            option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view ;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.pizza_row_item,parent,false) ;
            final MyViewHolder viewHolder = new MyViewHolder(view) ;
            viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, PizzaDetails.class);
                    i.putExtra("pizza_Id",mData.get(viewHolder.getAdapterPosition()).getPizzaId());
                    i.putExtra("Pizza_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                    i.putExtra("Pizza_price",mData.get(viewHolder.getAdapterPosition()).getPrice());
                    i.putExtra("Pizza_name",mData.get(viewHolder.getAdapterPosition()).getName());
                    i.putExtra(" Pizza_image",mData.get(viewHolder.getAdapterPosition()).getImageUrl());

                    mContext.startActivity(i);

                }
            });




            return viewHolder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.pizz_name.setText(mData.get(position).getName());
            holder.pizz_price.setText(String.valueOf(mData.get(position).getPrice()));


            // Load Image from the internet and set it into Imageview using Glide

            Glide.with(mContext).load(mData.get(position).getImageUrl()).apply(option).into(holder.img_thumbnail);



        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView pizz_name ;
            TextView pizz_price;

            ImageView img_thumbnail;
            LinearLayout view_container;





            public MyViewHolder(View itemView) {
                super(itemView);

                view_container = itemView.findViewById(R.id.container);
                pizz_name= itemView.findViewById(R.id.pizza_name);
                pizz_price= itemView.findViewById(R.id.price);

                img_thumbnail = itemView.findViewById(R.id.thumbnail);

            }
        }
    }
