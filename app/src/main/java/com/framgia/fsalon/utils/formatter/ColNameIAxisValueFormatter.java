package com.framgia.fsalon.utils.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by THM on 8/24/2017.
 */
public class ColNameIAxisValueFormatter implements IAxisValueFormatter {
    private final String[] mLabels;

    public ColNameIAxisValueFormatter(String[] labels) {
        mLabels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mLabels[(int) value];
    }
}
