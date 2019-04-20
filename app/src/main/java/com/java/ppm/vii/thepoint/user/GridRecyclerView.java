package com.java.ppm.vii.thepoint.user;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;



//todo: Ignore this class as it is a Failed attempt to add an animation handler to teh Gridlayout which did not work very well and should not be used without completely breaking everything!
//
@Deprecated
public class GridRecyclerView extends RecyclerView
{
    public GridRecyclerView(Context context)
    {
        super(context);
    }

    public GridRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GridRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count)
    {
        final LayoutManager layoutManager = getLayoutManager();
        if (getAdapter() != null && layoutManager instanceof GridLayoutManager)
        {
            GridLayoutAnimationController.AnimationParameters animationParams = (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;

            if (animationParams == null)
            {
                animationParams = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationParams;
            }

            animationParams.count = count;
            animationParams.index = index;

            final  int columns = ((GridLayoutManager) layoutManager).getSpanCount();
            animationParams.columnsCount = columns;
            animationParams.rowsCount = count / columns;

            final int invertedIndex = count - 1 - index;
            animationParams.column = columns - 1 - (invertedIndex % columns);

            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns;



        }
        else
        {
            super.attachLayoutAnimationParameters(child, params, index, count);
        }
    }

}
