package com.example.todolist1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mData;
    //初始有3个任务
    int num =3;
    // 事件回调监听
    private MyAdapter.OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.id_num);
        }
    }
        //构造函数
        public MyAdapter(ArrayList<String> data) {
            this.mData = data;
        }


        public void updateData(ArrayList<String> data) {
            this.mData = data;
            notifyDataSetChanged();
        }
        // 添加新的Item
        public void addNewItem() {
            if(mData == null) {
                mData = new ArrayList<>();
            }
            mData.add(0, "Task"+(++num));
            notifyItemInserted(0);
            updateData(mData);
        }
        // 删除Item
        public void deleteItem() {
            if(mData == null || mData.isEmpty()) {
                return;
            }
            mData.remove(0);
            notifyItemRemoved(0);
            updateData(mData);
            }

        // 定义点击回调接口
        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        // 定义一个设置点击监听器的方法
        public void setOnItemClickListener(MyAdapter.OnItemClickListener listener) {
            this.onItemClickListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mTv.setText(mData.get(position));
            // 对RecyclerView的每一个itemView设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if(onItemClickListener != null) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
}
