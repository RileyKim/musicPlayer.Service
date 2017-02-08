package com.taeksukim.android.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaeksuKim on 2017. 2. 1..
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {

    List<Music> datas;
    Context context;
    Intent intent = null;

    public MusicAdapter( Context context){
        this.datas = DataLoader.get(context);
        this.context = context;
        intent = new Intent(context, PlayerActivity.class);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        Holder holder = new Holder(view);


        return holder;
    }


//
//    //플레이어 페이지로 이동
//    View.OnClickListener clickListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//
//            context.startActivity(intent);
//        }
//    };
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Music music = datas.get(position);
        holder.txtTitle.setText(music.title);
        holder.txtArtist.setText(music.artist);
        holder.position = position;

        //holder.position = position;

        //holder.imageView.setImageURI(music.album_image);
                            //로드할 대상Uri             입력될 이미지뷰
        Glide.with(context).load(music.album_image).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtTitle, txtArtist;
        ImageView imageView;
        int position;

        public Holder(View itemView) {

            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtArtist = (TextView) itemView.findViewById(R.id.txtArtist);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                 }
            });

        }


    }
}

