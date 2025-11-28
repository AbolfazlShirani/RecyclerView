package com.example.recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rec = findViewById(R.id.rec);
        LinearLayoutManager linm = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(linm);


        ArrayList<datamodule> data = new ArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(
                    new datamodule(
                            R.mipmap.logo,
                            "post =>" + i
                    )
            );
        }
        myadapter adapter = new myadapter(data);
        rec.setAdapter(adapter);

        findViewById(R.id.fap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(
                        new datamodule(
                                R.mipmap.logo,
                                "post =>" + data.size()
                        )
                );
                adapter.notifyItemInserted(data.size()+1);
            }
        });
    }
    public class myadapter extends RecyclerView.Adapter<myadapter.MyViewholder>{

        ArrayList<datamodule> List = new ArrayList<>();
        public class MyViewholder extends RecyclerView.ViewHolder{
            ImageView image,img_del;
            TextView title;
            CardView item;
            public MyViewholder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.title);
                item = itemView.findViewById(R.id.item);
                img_del = itemView.findViewById(R.id.img_delete);


            }
        }
        public myadapter(ArrayList<datamodule> data_){

            List = data_;
        }
        @NonNull
        @Override
        public myadapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
            MyViewholder hd = new MyViewholder(itemView);
            return hd;
        }

        @Override
        public void onBindViewHolder(@NonNull myadapter.MyViewholder holder, int position) {

            holder.image.setImageResource(List.get(holder.getAbsoluteAdapterPosition()).getImage());
            holder.title.setText(List.get(holder.getAbsoluteAdapterPosition()).getTitle());

            holder.img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List.remove(holder.getAbsoluteAdapterPosition());
                    notifyItemRemoved(holder.getAbsoluteAdapterPosition());

                }
            });
        }

        @Override
        public int getItemCount() {
            return  List==null ? 0 : List.size();
        }
    }
}

