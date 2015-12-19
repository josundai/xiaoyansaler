package com.shangjia.component;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shangjia.R;

/**
 * Created by dai on 2015/12/19.
 */
public class ExitDialog {

        Context context;
        android.app.AlertDialog ad;
        TextView messageView;
        LinearLayout buttonLayout;
        public ExitDialog(Context context) {
            this.context=context;
            ad=new android.app.AlertDialog.Builder(context).create();
            ad.show();
            //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
            Window window = ad.getWindow();
            window.setContentView(R.layout.exit_dialog);
            messageView=(TextView)window.findViewById(R.id.message);
            buttonLayout=(LinearLayout)window.findViewById(R.id.buttonLayout);
        }

        public ExitDialog setMessage(int resId) {
            messageView.setText(resId);
            return this;
        }
        public ExitDialog setMessage(String message)    {
            messageView.setText(message);
            return this;
        }

    /*
    <Button
    android:id="@+id/id_button_confirm"
    android:layout_width="120dip"
    android:layout_height="wrap_content"
    android:background="@drawable/icon_button_exit_background"
    android:gravity="center"
    android:hint="@string/exit_dialog_yes"
    android:textColor="@color/white"
    android:textSize="18sp"
            />
    <!--android:layout_gravity="center"-->
    <Button
    android:id="@+id/id_button_cancel"
    android:layout_width="120dip"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:background="@drawable/icon_button_exit_background"
    android:hint="@string/exit_dialog__no"
    android:textColor="@color/white"
    android:textSize="18sp"
            />
    */
        /**
         * 设置确定按钮
         * @param listener
         */
        public ExitDialog setPositiveButton( View.OnClickListener listener)    {
            Button button=new Button(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setGravity(Gravity.CENTER);
            button.setBackgroundResource(R.drawable.icon_button_exit_background);
            button.setText(R.string.exit_dialog_yes);
            button.setTextColor(Color.WHITE);
            button.setTextSize(18);
            button.setOnClickListener(listener);
            buttonLayout.addView(button);
            return this;
        }
        /**
         * 设置取消按钮
         * @param listener
         */
        public ExitDialog setNegativeButton(  View.OnClickListener listener) {
            Button button = new Button(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.icon_button_exit_background);
            button.setText(R.string.exit_dialog__no);
            button.setTextColor(Color.WHITE);
            button.setTextSize(16);
            button.setOnClickListener(listener);
            button.setLayoutParams(params);
            buttonLayout.addView(button);
            return this;
        }





        /**
         * 关闭对话框
         */
        public void dismiss() {
            ad.dismiss();
        }
}
