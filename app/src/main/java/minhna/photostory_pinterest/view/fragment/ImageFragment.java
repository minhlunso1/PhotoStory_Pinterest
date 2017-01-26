package minhna.photostory_pinterest.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhna.photostory_pinterest.R;
import minhna.photostory_pinterest.module.AC;

/**
 * Created by Administrator on 26-Jan-17.
 */

public class ImageFragment extends Fragment {

    @BindView(R.id.img) ImageView imgView;
    @BindView(R.id.pb_img) ProgressBar pbImg;
    Context context;

    public static ImageFragment newInstance(String imageUrl) {
        Bundle args = new Bundle();
        ImageFragment fragment = new ImageFragment();
        args.putString(AC.IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View v = inflater.inflate(R.layout.fragment_img_item, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String imageUrl = bundle.getString(AC.IMAGE_URL);

        Glide.with(getActivity())
                .load(imageUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;//remember just return false
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbImg.setVisibility(View.GONE);
                        return false;
                    }
                })
                .placeholder(R.drawable.bg_2)
                .error(R.drawable.bg_2)
                .centerCrop()
                .into(imgView);
    }

}
