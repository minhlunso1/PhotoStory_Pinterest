package minhna.photostory_pinterest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class ShowImageActivity extends AppCompatActivity {

    @BindView(R.id.image_full_view)
    ImageView imageView;
    @BindView(R.id.progress_bar_show_image)
    ProgressBar progressBar;

    public static void go(Context context, String url) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra(AC.IMAGE_URL, url);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);
        initUI();
        loadImage();
    }

    private void initUI() {
        if (getSupportActionBar()!=null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void loadImage() {
        String url = getIntent().getStringExtra(AC.IMAGE_URL);
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .error(R.drawable.bg_2)
                .dontTransform()
                .override(AC.PREFERRED_IMAGE_WIDTH_FULL, AC.PREFERRED_IMAGE_HEIGHT_FULL)
                .into(imageView);
    }
}
