package minhna.photostory_pinterest.view.custom;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Minh on 1/13/2017.
 */

public class PagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    boolean requiredRedraw = false;
    private Point pointCenter = new Point();
    private Point pointTouch = new Point();

    //require these contructor to generate view from xml
    public PagerContainer(Context context) {
        super(context);
        init();
    }
    public PagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public PagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ViewPager getViewPager() {
        return pager;
    }

    private void init() {
        setClipChildren(false);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onFinishInflate() {
        try {
            pager = (ViewPager) getChildAt(0);
            pager.addOnPageChangeListener(this);
        } catch (Exception e) {
            //the child must be viewpager
            throw new IllegalStateException("IllegalStateException");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointTouch.x = (int) ev.getX();
                pointTouch.y = (int) ev.getY();
            default:
                ev.offsetLocation(pointCenter.x - pointTouch.x, pointCenter.y - pointTouch.y);
                break;
        }
        return pager.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //redraw
        if (requiredRedraw) invalidate();
    }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) {
        requiredRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        pointCenter.x = w / 2;
        pointCenter.y = h / 2;
    }
    
}
