package com.sd.lib.swipemenu.pull_condition;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import com.sd.lib.swipemenu.SwipeMenu;

import java.lang.ref.WeakReference;

public abstract class ViewPullCondition implements SwipeMenu.PullCondition
{
    private final WeakReference<View> mView;

    public ViewPullCondition(View view)
    {
        if (view == null)
            throw new IllegalArgumentException("view is null for pull condition");

        mView = new WeakReference<>(view);
    }

    public final View getView()
    {
        return mView == null ? null : mView.get();
    }

    @Override
    public final boolean canPull(SwipeMenu.Direction pullDirection, MotionEvent event, SwipeMenu swipeMenu)
    {
        final View view = getView();
        if (view == null)
        {
            swipeMenu.removePullCondition(this);
            return true;
        }

        if (view.getVisibility() != View.VISIBLE)
            return true;

        if (!isAttached(view))
            return true;

        return canPullImpl(pullDirection, event, swipeMenu);
    }

    protected abstract boolean canPullImpl(SwipeMenu.Direction pullDirection, MotionEvent event, SwipeMenu swipeMenu);

    private static boolean isAttached(View view)
    {
        if (view == null)
            return false;

        if (Build.VERSION.SDK_INT >= 19)
            return view.isAttachedToWindow();
        else
            return view.getWindowToken() != null;
    }
}
