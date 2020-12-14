package com.example.visitrwanda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private ArrayList<Booking> dataset;
    Context context;

    //---Define MyViewHolder-----

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_idno;
        TextView tv_client_name;
        TextView tv_contactno;
        ImageView img_gender;
        ImageView img_call;
        Button btn_book_title;
        LinearLayout li_card_booking;



        public MyViewHolder(View itemView){
            super(itemView);
            this.tv_idno= (TextView)itemView.findViewById(R.id.tv_idno);
            this.tv_client_name= (TextView)itemView.findViewById(R.id.tv_client_name);
            this.tv_contactno= (TextView)itemView.findViewById(R.id.tv_contactno);
            this.img_gender = (ImageView)itemView.findViewById(R.id.img_gender);
            this.img_call = (ImageView)itemView.findViewById(R.id.img_call);
            this.btn_book_title = (Button)itemView.findViewById(R.id.btn_book_title);
            this.li_card_booking = (LinearLayout)itemView.findViewById(R.id.li_card_booking);

        }
    }

    //-----end_MyViewHolder-----
    public CustomAdapter(ArrayList<Booking> dataset,Context context)
    {
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_booking,parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

                return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView tv_idno = holder.tv_idno;
        TextView tv_client_name = holder.tv_client_name;
        TextView tv_contactno = holder.tv_contactno;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_book_title = holder.btn_book_title;
        LinearLayout li_card_booking = holder.li_card_booking;

        tv_idno.setText(dataset.get(position).idno+"");
        tv_client_name.setText(dataset.get(position).client_name+"");
        tv_contactno.setText(dataset.get(position).contactno+"");

        if(dataset.get(position).gender.equalsIgnoreCase("Male"))
        {
            img_gender.setImageResource(R.drawable.male_icon);
        }
        else if(dataset.get(position).gender.equalsIgnoreCase("Female"))
        {
            img_gender.setImageResource(R.drawable.female_icon);
        }

        //----logic to set title-----
        btn_book_title.setText(dataset.get(position).client_name.toUpperCase().charAt(0) +"");
        //--------random color logic----
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        btn_book_title.setBackgroundColor(Color.rgb(red, green, blue));

        if (dataset.get(position).contactno.length()>9){

            img_call.setVisibility(View.VISIBLE);
            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iCall = new Intent (Intent.ACTION_DIAL, Uri.parse("tel:" +dataset.get(position).contactno));
                    context.startActivity(iCall);
                }
            });
        }
        else
        {
            img_call.setVisibility(View.GONE);
        }

        li_card_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idno = dataset.get(position).idno;
                String client_name = dataset.get(position).client_name;
                String contactno = dataset.get(position).contactno;
                String gender = dataset.get(position).gender;

                //Toast.makeText(context, idno+"\n"+client_name+"\n"+gender, Toast.LENGTH_LONG).show();
                Intent iUpdate = new Intent(context, BookingDetailActivity.class);

                iUpdate.putExtra("idno", idno);
                iUpdate.putExtra("client_name", client_name);
                iUpdate.putExtra("contactno", contactno);
                iUpdate.putExtra("gender", gender);
                context.startActivity(iUpdate);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
