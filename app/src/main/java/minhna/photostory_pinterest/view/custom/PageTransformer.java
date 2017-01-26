package minhna.photostory_pinterest.view.custom;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class PageTransformer implements ViewPager.PageTransformer {

    private float offset = -1;
    private float minAlpha=0.7f;

    @Override
    public void transformPage(View page, float position) {
        if (offset == -1) {
            offset = 75 / page.getMeasuredWidth();
        }
        if (position < -1) {
            page.setAlpha(0.7f);
        } else if (position <= 1) {
            float alphaFactor = Math.max(minAlpha, 1 - Math.abs(position - offset));
            page.setAlpha(alphaFactor);
        } else {
            page.setAlpha(0.7f);
        }
    }

}
