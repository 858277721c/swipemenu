package com.sd.lib.swipemenu;

import android.view.MotionEvent;
import android.view.View;

public interface SwipeMenu
{
    /**
     * 设置调试模式，会有日志输出，日志tag：SwipeMenu
     *
     * @param debug
     */
    void setDebug(boolean debug);

    /**
     * 设置是否可以拖动
     *
     * @param enable
     */
    void setPullEnable(boolean enable);

    /**
     * 添加回调对象
     *
     * @param callback
     */
    void addOnStateChangeCallback(OnStateChangeCallback callback);

    /**
     * 移除回调对象
     *
     * @param callback
     */
    void removeOnStateChangeCallback(OnStateChangeCallback callback);

    /**
     * 添加回调对象
     *
     * @param callback
     */
    void addOnViewPositionChangeCallback(OnViewPositionChangeCallback callback);

    /**
     * 移除回调对象
     *
     * @param callback
     */
    void removeOnViewPositionChangeCallback(OnViewPositionChangeCallback callback);

    /**
     * 添加回调对象
     *
     * @param callback
     */
    void addOnScrollStateChangeCallback(OnScrollStateChangeCallback callback);

    /**
     * 移除回调对象
     *
     * @param callback
     */
    void removeOnScrollStateChangeCallback(OnScrollStateChangeCallback callback);

    /**
     * 添加拖动条件限制
     *
     * @param condition
     */
    void addPullCondition(PullCondition condition);

    /**
     * 移除拖动条件限制
     *
     * @param condition
     */
    void removePullCondition(PullCondition condition);

    /**
     * 清空所有拖动条件限制
     */
    void clearPullCondition();

    /**
     * 设置内容view
     *
     * @param view
     */
    void setContentView(View view);

    /**
     * 设置某个方向的菜单view
     *
     * @param direction
     * @param view
     */
    void setMenuView(Direction direction, View view);

    /**
     * 设置菜单模式，默认覆盖模式{@link Mode#Overlay}
     *
     * @param mode {@link Mode}
     */
    void setMode(Mode mode);

    /**
     * 返回内容view
     *
     * @return
     */
    View getContentView();

    /**
     * 返回指定方向的菜单view
     *
     * @param direction {@link Direction}
     * @return
     */
    View getMenuView(Direction direction);

    /**
     * 返回菜显示方向
     *
     * @return
     */
    Direction getMenuDirection();

    /**
     * 是否可以拖动
     *
     * @return
     */
    boolean isPullEnable();

    /**
     * 返回当前菜单的状态
     *
     * @return
     */
    State getState();

    /**
     * 返回滚动状态
     *
     * @return
     */
    ScrollState getScrollState();

    /**
     * 返回当前内容view滚动的百分比[0-1]
     *
     * @return
     */
    float getScrollPercent();

    /**
     * 设置菜单状态
     *
     * @param state {@link State}
     * @param anim  true-动画效果，false-无动画
     * @return
     */
    boolean setState(State state, boolean anim);

    enum State
    {
        Close,
        OpenLeft,
        OpenTop,
        OpenRight,
        OpenBottom
    }

    enum Direction
    {
        Left,
        Top,
        Right,
        Bottom;

        public final boolean isHorizontal()
        {
            return this == Left || this == Right;
        }

        public final boolean isVertical()
        {
            return this == Top || this == Bottom;
        }
    }

    enum Mode
    {
        /**
         * 覆盖模式
         */
        Overlay,
        /**
         * 抽屉拉开模式
         */
        Drawer
    }

    enum ScrollState
    {
        /**
         * 空闲状态
         */
        Idle,
        /**
         * 拖动状态
         */
        Drag,
        /**
         * 惯性滑动状态
         */
        Fling
    }

    interface OnStateChangeCallback
    {
        /**
         * 状态变更回调
         *
         * @param oldState
         * @param newState
         * @param swipeMenu
         */
        void onStateChanged(State oldState, State newState, SwipeMenu swipeMenu);
    }

    interface OnViewPositionChangeCallback
    {
        /**
         * view位置变化回调，侧滑菜单滑动或者拖动
         *
         * @param left      内容view父容器的left
         * @param top       内容view父容器的top
         * @param isDrag    true-拖动，false-惯性滚动
         * @param swipeMenu
         */
        void onViewPositionChanged(int left, int top, boolean isDrag, SwipeMenu swipeMenu);
    }

    interface OnScrollStateChangeCallback
    {
        /**
         * 滚动状态变化回调
         *
         * @param oldState
         * @param newState
         * @param swipeMenu
         */
        void onScrollStateChanged(ScrollState oldState, ScrollState newState, SwipeMenu swipeMenu);
    }

    interface PullCondition
    {
        /**
         * 是否可以拖动
         *
         * @param pullDirection
         * @param event
         * @param swipeMenu
         * @return
         */
        boolean canPull(Direction pullDirection, MotionEvent event, SwipeMenu swipeMenu);
    }
}
