package com.framgia.fsalon.utils.binding;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by beepi on 31/07/2017.
 */
public class DeviderItemDecoration extends RecyclerView.ItemDecoration {
    private final int mVerticalSpaceHeight;

    public DeviderItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
