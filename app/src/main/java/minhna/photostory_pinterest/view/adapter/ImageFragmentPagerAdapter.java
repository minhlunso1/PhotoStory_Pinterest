package minhna.photostory_pinterest.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import minhna.photostory_pinterest.model.Pin;
import minhna.photostory_pinterest.view.fragment.ImageFragment;

/**
 * Created by Administrator on 26-Jan-17.
 */

public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {

    private int count;
    private List<Pin> pins;

    public ImageFragmentPagerAdapter(FragmentManager fm, List<Pin> pins) {
        super(fm);
        this.pins = pins;
        this.count = pins.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(pins.get(position).imageUrl);
    }

    @Override
    public int getCount() {
        return count;
    }
}
