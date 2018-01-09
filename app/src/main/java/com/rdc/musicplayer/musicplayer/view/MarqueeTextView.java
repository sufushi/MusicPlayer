package com.rdc.musicplayer.musicplayer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView implements Runnable {

    private int mCurrentSrcollX;
    private int mTextWidth;
    private boolean mIsMeasure;
    private boolean mIsStop;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (! mIsMeasure) {
            measureTextWidth();
            mIsMeasure =true;
        }
    }

    private void measureTextWidth() {
        Paint paint = this.getPaint();
        String string = this.getText().toString();
        mTextWidth = (int) paint.measureText(string);
    }

    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        startScroll();
    }

    public void startScroll() {
        mIsStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    public void restartScroll() {
        mCurrentSrcollX = 0;
        startScroll();
    }

    public void stopScroll() {
        mIsStop = true;
    }

    @Override
    public void run() {
        mCurrentSrcollX -= 1;
        scrollTo(mCurrentSrcollX, 0);
        if (mIsStop) {
            return;
        }
        if (getScrollX() <= - (this.getWidth())) {
            scrollTo(mTextWidth, 0);
            mCurrentSrcollX = mTextWidth;
        }
        postDelayed(this, 10);
    }

}
