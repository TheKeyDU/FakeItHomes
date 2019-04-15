package com.dqj.fakeithomes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dqj.fakeithomes.siv.SmartImageView;

import java.util.ArrayList;
import java.util.List;

 class homeadpter extends RecyclerView.Adapter<homeadpter.MyViewHolder> {
     private setclick msetclick;

     public void setclick(setclick setclicks) {
         this.msetclick = setclicks;
     }

     public interface setclick {
         void onClick(int pos);

         void onLongClick(int pos);
     }
     Context context;
     List<bean.NewslistBean> list = new ArrayList<>();

     public homeadpter(Context context, List list) {
         this.context = context;
         this.list = list;
     }

     class MyViewHolder extends RecyclerView.ViewHolder  {
         SmartImageView imageView;
         TextView title;
         TextView time;
         TextView from;
         CardView mcardView;
         int id;
         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             imageView = (SmartImageView) itemView.findViewById(R.id.news_img);
             title = (TextView) itemView.findViewById(R.id.news_title);
             time = (TextView) itemView.findViewById(R.id.news_time);
             from = (TextView) itemView.findViewById(R.id.news_from);
mcardView=(CardView) itemView.findViewById(R.id.cd);

         }


     }

     @NonNull
     @Override
     public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
         MyViewHolder myViewHolder = new MyViewHolder(view);

         return myViewHolder;

     }

     @NonNull


     public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
         String[] strings = list.get(i).getPostdate().split("T");
         String time = strings[1].substring(0, 5) + " " + strings[0];
         myViewHolder.from.setText(list.get(i).getSid() == 0 ? "it之家" : "位置");
         myViewHolder.title.setText(list.get(i).getTitle());
         myViewHolder.time.setText(time);
         //myViewHolder.imageView.setImageUrl(list.get(i).getImage());
         Glide
                 .with(context)
                 .load(list.get(i).getImage())
                 .into(myViewHolder.imageView);
if(msetclick!=null)
{
    myViewHolder.mcardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            msetclick.onClick(i);
        }
    });
}
     }


     @Override
     public int getItemCount() {
         return list.size();
     }
 }