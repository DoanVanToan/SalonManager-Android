package com.framgia.fsalon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by MyPC on 20/07/2017.
 */
public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<V> {

    private final Context mContext;

    protected BaseRecyclerViewAdapter(@NonNull Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    public abstract void onUpdatePage(List<T> data);

    /**
     * OnRecyclerViewItemClickListener
     *
     * @param <T> Data from item click
     */
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemRecyclerViewClick(T item);
    }
}
