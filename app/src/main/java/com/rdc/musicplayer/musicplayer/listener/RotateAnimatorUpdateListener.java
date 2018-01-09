package com.rdc.musicplayer.musicplayer.listener;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;

public class RotateAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

    private boolean mIsPause = false;
    private boolean mIsPaused = false;
    private boolean mIsPlay = true;
    private float mFraction = 0.0f;
    private long mCurrentPlayTime = 1L;
    private Animator mAnimator;

    public RotateAnimatorUpdateListener(Animator animator) {
        mAnimator = animator;
    }

    public boolean isPause() {
        return mIsPause;
    }

    public boolean isPlay() {
        return mIsPlay;
    }

    public void pause() {
        mIsPause = true;
        mIsPlay = false;
    }

    public void play() {
        mIsPause = false;
        mIsPaused = false;
        mIsPlay = true;
    }

    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        if (mIsPause) {
            if (!mIsPaused) {
                mCurrentPlayTime = animation.getCurrentPlayTime();
                mFraction = animation.getAnimatedFraction();
                animation.setInterpolator(new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float input) {
                        return mFraction;
                    }
                });
                mIsPaused = true;
            }
            new CountDownTimer(ValueAnimator.getFrameDelay(), ValueAnimator.getFrameDelay()) {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                @Override
                public void onFinish() {
                    animation.setCurrentFraction(mCurrentPlayTime);
                }
            }.start();
        } else {
            animation.setInterpolator(null);
        }
    }
}
