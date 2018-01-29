package com.lhzcpan.view;

/**
 * Created by master on 2017/12/13.
 */

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lhzcpan.R;


public class BottomDialog {
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    public static final int LINEAR = 0;
    public static final int GRID = 1;

    private CustomDialog customDialog;

    public BottomDialog(Context context) {
        customDialog = new CustomDialog(context);
    }

    public BottomDialog setOnItemClick(View.OnClickListener listener) {
        customDialog.setOnItemClick(listener);
        return this;
    }

    public void show() {
        customDialog.show();
    }

    public void dismiss() {
        customDialog.dismiss();
    }

    private final class CustomDialog extends Dialog {

        TextView delete;

        CustomDialog(Context context) {
            super(context, R.style.BottomDialog);
            init();
        }

        private void init() {
            setContentView(R.layout.bottom_dialog);
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            delete = findViewById(R.id.delete);

            findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        void setOnItemClick(View.OnClickListener onClickListener) {
            delete.setOnClickListener(onClickListener);
        }

    }
}