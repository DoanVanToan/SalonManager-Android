
package com.framgia.fsalon.utils.formatter;

import android.content.Context;
import android.widget.TextView;

import com.framgia.fsalon.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class CustomMarkerView extends MarkerView {
    private TextView mTextContent;

    public CustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mTextContent = (TextView) findViewById(R.id.text_content);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text = "";
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            text += Utils.formatNumber(ce.getHigh(), 0, true);
            mTextContent.setText(text);
        } else {
            text += Utils.formatNumber(e.getY(), 0, true);
            mTextContent.setText(text);
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
