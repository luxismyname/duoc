package com.bongmai.tiha.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;


public class NumberTextWatcher implements TextWatcher {

    public interface TextChangedListener {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }

    //
    private DecimalFormat dfFractional;
    private DecimalFormat decimalFormat;
    private boolean hasFractionalPart;

    private EditText et;

    TextChangedListener textChangedListener;

    public NumberTextWatcher(EditText et, TextChangedListener listener) {
        dfFractional = AppUtils.formatNumber("N2");
        dfFractional.setDecimalSeparatorAlwaysShown(true);
        decimalFormat = AppUtils.formatNumber("N0");
        this.et = et;
        hasFractionalPart = false;
        this.textChangedListener = listener;

    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);
        try {
            int inilen, endlen;
            inilen = et.getText().length();

            String v = s.toString().replace(String.valueOf(dfFractional.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = dfFractional.parse(v);
            int cp = et.getSelectionStart();
            if (hasFractionalPart) {
                et.setText(dfFractional.format(n));
            } else {
                et.setText(decimalFormat.format(n));
            }
            endlen = et.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= et.getText().length()) {
                et.setSelection(sel);
            } else {
                // place cursor at the end?
                et.setSelection(et.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }
        et.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(String.valueOf(dfFractional.getDecimalFormatSymbols().getDecimalSeparator()))) {
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
        textChangedListener.onTextChanged(s, start, before, count);
    }
}

