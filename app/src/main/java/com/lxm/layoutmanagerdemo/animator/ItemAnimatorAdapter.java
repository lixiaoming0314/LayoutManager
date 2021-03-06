package com.lxm.layoutmanagerdemo.animator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxm.layoutmanagerdemo.R;
import com.lxm.layoutmanagerdemo.touchhelp.ItemTouchHelpCallback;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by lixiaoming on 2018/6/22.
 */

public class ItemAnimatorAdapter extends RecyclerView.Adapter<ItemAnimatorAdapter.ViewHolder> implements ItemTouchHelpCallback.OnItemCallbackListener {

    private Context context;
    private ArrayList<String> picUrls = new ArrayList<>();

    public ItemAnimatorAdapter(Context context) {
        this.context = context;
    }

    public void setPicUrls(ArrayList<String> data) {
        this.picUrls = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        // 实例化展示的view
        // 实例化viewholder
        v = LayoutInflater.from(context).inflate(R.layout.item_second, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /**
     * 增加数据
     */
    public void addData(int position) {
        picUrls.add(position, "A -1 ");
        notifyItemInserted(position);//注意这里
    }

    /**
     * 移除数据
     */
    public void removeData(int position) {
        picUrls.remove(position);
        notifyItemRemoved(position);//注意这里
    }
    /**
     * 移除数据
     */
    public void swipe(int fromPosition,int toPosition) {
        notifyItemMoved(fromPosition,toPosition);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.text.setText(picUrls.get(position));

    }

    @Override
    public int getItemCount() {
        return picUrls == null ? 0 : picUrls.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        /**
         * 在这里进行给原数组数据的移动
         */
        Collections.swap(picUrls, fromPosition, toPosition);
        /**
         * 通知数据移动
         */
        notifyItemMoved(fromPosition, toPosition);

    }

    @Override
    public void onSwipe(int position) {
        picUrls.remove(position);
        notifyItemRemoved(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

}
