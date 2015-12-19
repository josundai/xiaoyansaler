package com.shangjia.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shangjia.R;
import com.shangjia.component.ExitDialog;


public class HeaderView extends LinearLayout {
    private TextView mLeftBtn;
    private TextView mTitle;
    private TextView mRightTxt;
    private ImageView mRightBtn;
    private TextView mRightExistBtn;
    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_header, this, true);
        mLeftBtn = (TextView)findViewById(R.id.left_btn);
        mLeftBtn.setText(" ");
        mLeftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        mTitle = (TextView)findViewById(R.id.title);
        mRightTxt = (TextView)findViewById(R.id.id_text_help);
        mRightBtn = (ImageView)findViewById(R.id.right_btn);
        mRightExistBtn = (TextView)findViewById(R.id.right_exit_btn);
        mRightExistBtn.setVisibility(INVISIBLE);

        mRightExistBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final ExitDialog exitDialog = new ExitDialog( getContext()).setMessage(R.string.exit_dialog_confirm);
                exitDialog.setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Activity) getContext()).finish();
                    }
                });
                exitDialog.setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                });
            }
        });
    }

    public void setTitle(int resId){
        mTitle.setText(resId);
    }

    public void setTitle(String title){
        mTitle.setText(title);
    }

    public void setTitleCenter(){
        mTitle.setGravity(Gravity.CENTER);
    }

    public void setTitleLeft(){
        mTitle.setGravity(Gravity.LEFT);
    }

    public void setRightImage(int resId){
        mRightBtn.setImageResource(resId);
        mRightBtn.setVisibility(VISIBLE);
        mRightTxt.setVisibility(GONE);
    }

    public void setRightText(int resId){
        mRightTxt.setText(resId);
        mRightBtn.setVisibility(GONE);
        mRightTxt.setVisibility(VISIBLE);
    }

    public void hideRightImage(){
        mRightBtn.setVisibility(INVISIBLE);
    }

    public void removeLeftArrow(){
        Drawable[] drawables = mLeftBtn.getCompoundDrawables();
        if(drawables!=null){
            Drawable drawTop = drawables[1];
            Drawable drawRight = drawables[2];
            Drawable drawBottom = drawables[3];
            mLeftBtn.setCompoundDrawables(null, drawTop, drawRight, drawBottom );
        }
    }

    public void setRightBtnListener(View.OnClickListener listener){
        mRightBtn.setOnClickListener(listener);
    }

    public void setRightTextListener(View.OnClickListener listener){
        mRightTxt.setOnClickListener(listener);
    }

    public void addExistButton(){
        mRightExistBtn.setVisibility(VISIBLE);
    }
}
