package com.choi.minjoon.backinschool.common.domain.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.choi.minjoon.backinschool.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2017-06-01.
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {

    List<FirstItem> items;


    //아이템 클릭시 실행 함수
    private ItemClick itemClick;

    public interface ItemClick {
        public void onClick(View view, int position);
    }

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        View view;

        @BindView(R.id.list_item_label1)
        TextView firstLabel;
        @BindView(R.id.list_item_label2)
        TextView secondLabel;
        @BindView(R.id.list_item_label3)
        TextView thirdLabel;

        @BindView(R.id.list_item_text1)
        TextView firstText;
        @BindView(R.id.list_item_text2)
        TextView secondText;
        @BindView(R.id.list_item_text3)
        TextView thirdText;
        @BindView(R.id.list_item_image1)
        ImageView thridImage;

        public ViewHolder(Context context, View ItemView) {
            super(ItemView);
            this.context = context;
            this.view = itemView;
            ButterKnife.bind(this, itemView);

            firstLabel.setText("id");
            secondLabel.setText("title");
            thirdLabel.setText("body");
            thirdText.setVisibility(View.GONE);
        }
    }

    public FirstAdapter(List<FirstItem> items) {
        this.items = items;
    }

    public void add(FirstItem item) {
        items.add(item);
    }

    public void replace(List<FirstItem> list) {
        this.items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int Position = position;

        holder.firstText.setText(items.get(position).getId().toString());
        holder.secondText.setText(items.get(position).getTitle().toString());
        Picasso.with(holder.context)
                .load("http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg")
                .into(holder.thridImage);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.onClick(v, Position + 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
