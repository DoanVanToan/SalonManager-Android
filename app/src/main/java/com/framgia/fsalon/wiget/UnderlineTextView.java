package com.framgia.fsalon.wiget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by framgia on 9/1/17.
 */

public class UnderlineTextView extends android.support.v7.widget.AppCompatTextView {

    public UnderlineTextView(Context context) {
        super(context);
        setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public UnderlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public UnderlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
