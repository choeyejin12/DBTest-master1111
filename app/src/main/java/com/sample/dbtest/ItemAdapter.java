package com.sample.dbtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items; //배열로 한 장소의 이름/주소/아이디/이미지를 묶어서 관리

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);

        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //뷰를 홀드(고정하는 역할) 위치를 잡아주고 해당되는 xml에 대입함
        VH vh= (VH) holder;

        Item item= items.get(position); //위치는 item배열로 잡음
        vh.tvName.setText(item.getP_name()); //장소 이름 대입
        vh.tvMsg.setText(item.getP_address()); //장소 주소 대입

        Glide.with(context).load(item.getP_image()).into(vh.iv); //gilde는 확장 라이브러리 따로 깐 것! 알아서 URL을 이미지로 바꿔줌
    }
//그냥 뷰 정의
    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMsg;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            iv=itemView.findViewById(R.id.iv);

        }
    }
}