package com.sslcs.timeline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;

/**
 * Created by CS on 2014/11/24 16:02.
 * TODO :
 */
public class TimeLineView extends RelativeLayout
{
    private int mPosition = 0;
    private int mPipeColor = 0xffe4e4e4;
    private int mWaterColor = 0xffff0000;
    private Paint mPaint = new Paint();
    private int mLineWidth, mMargin, mRadiusDot, mRadiusImage;
    private Adapter mAdapter = null;

    private OnItemClickListener mListener;

    public TimeLineView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        mLineWidth = getResources().getDimensionPixelSize(R.dimen.line_width);
        mMargin = getResources().getDimensionPixelSize(R.dimen.margin);
        mRadiusDot = getResources().getDimensionPixelSize(R.dimen.radius_dot);
        mRadiusImage = getResources().getDimensionPixelSize(R.dimen.radius_image);
    }

    public void setAdapter(Adapter adapter)
    {
        mAdapter = adapter;
        initViewsFromAdapter();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (null == mAdapter)
        {
            return;
        }

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = mAdapter.getCount() * mMargin * 2;
        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            int top = (1 + i * 2) * mMargin - mRadiusImage;
            int left = i % 2 == 0 ? getWidth() / 4 : getWidth() * 3 / 4;
            left = left - mRadiusImage;
            child.layout(left, top, left + child.getWidth(), top + child.getHeight());
        }
    }

    private void initViewsFromAdapter()
    {
        removeAllViews();
        if (null == mAdapter)
        {
            return;
        }

        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++)
        {
            addView(i);
        }
    }

    private void addView(final int pos)
    {
        View view = mAdapter.getView(pos, null, this);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int marginTop = (1 + pos * 2) * mMargin - mRadiusImage;
        int marginLeft = pos % 2 == 0 ? getWidth() / 4 : getWidth() * 3 / 4;
        params.setMargins(marginLeft - mRadiusImage, marginTop, 0, 0);
        addView(view, params);

        if (null == mListener)
        {
            return;
        }
        view.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mListener.onItemClick(pos);
            }
        });
    }

    public void setPipeColor(int color)
    {
        mPipeColor = color;
        invalidate();
    }

    public void setWaterColor(int color)
    {
        mWaterColor = color;
        invalidate();
    }

    public void setCurrentPosition(int position)
    {
        mPosition = position;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (null == mAdapter)
        {
            return;
        }

        mPaint.setColor(mPipeColor);
        mPaint.setStrokeWidth(mLineWidth);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);

        int margin, count = mAdapter.getCount();
        for (int i = 0; i < count; i++)
        {
            margin = (1 + i * 2) * mMargin;
            canvas.drawCircle(getWidth() / 2, margin, mRadiusDot, mPaint);

            if (i % 2 == 0)
            {
                canvas.drawLine(getWidth() / 4, margin, getWidth() / 2, margin, mPaint);
            }
            else
            {
                canvas.drawLine(getWidth() / 2, margin, getWidth() * 3 / 4, margin, mPaint);
            }
        }

        mPaint.setColor(mWaterColor);
        int height = (1 + mPosition * 2) * mMargin;
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, height, mPaint);
        for (int i = 0; i <= mPosition; i++)
        {
            margin = (1 + i * 2) * mMargin;
            canvas.drawCircle(getWidth() / 2, margin, mRadiusDot, mPaint);

            if (i % 2 == 0)
            {
                canvas.drawLine(getWidth() / 4, margin, getWidth() / 2, margin, mPaint);
            }
            else
            {
                canvas.drawLine(getWidth() / 2, margin, getWidth() * 3 / 4, margin, mPaint);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener l)
    {
        mListener = l;
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int pos);
    }
}
