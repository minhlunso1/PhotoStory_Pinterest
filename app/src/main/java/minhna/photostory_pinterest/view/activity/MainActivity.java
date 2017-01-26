package minhna.photostory_pinterest.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pinterest.android.pdk.PDKBoard;
import com.pinterest.android.pdk.PDKCallback;
import com.pinterest.android.pdk.PDKClient;
import com.pinterest.android.pdk.PDKException;
import com.pinterest.android.pdk.PDKPin;
import com.pinterest.android.pdk.PDKResponse;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import minhna.photostory_pinterest.R;
import minhna.photostory_pinterest.model.BasicProfile;
import minhna.photostory_pinterest.model.PSMode;
import minhna.photostory_pinterest.model.Pin;
import minhna.photostory_pinterest.module.AC;
import minhna.photostory_pinterest.module.AP;
import minhna.photostory_pinterest.view.adapter.ImageFragmentPagerAdapter;
import minhna.photostory_pinterest.view.custom.PageTransformer;
import minhna.photostory_pinterest.view.custom.PagerContainer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pager_container) PagerContainer container;
    @BindView(R.id.vp) ViewPager pager;
    @BindView(R.id.card) View card;
    @BindView(R.id.overlay) View overlay;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_content) TextView tvContent;
    @BindView(R.id.tv_footer) TextView tvAuthor;
    @BindView(R.id.heart_inside) View heartInside;
    @BindView(R.id.up_area) View upArea;
    int index;
    int[] bgColors = {R.color.black_overlay, R.color.grey_overlay, R.color.dark_overlay, R.color.white_overlay};
    public int colorIndex=3;
    private List<Pin> pins;
    private BasicProfile profile;
    private final String PIN_FIELDS = "image,counts,note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideBars();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        overlay.setBackgroundColor(ContextCompat.getColor(this, bgColors[colorIndex]));
        if (getIntent()!=null) {
            Intent intent = getIntent();
            profile = intent.getParcelableExtra(AC.KEK_BASIC_PROFILE);
        }
        if (AP.getStringData(this, AC.PATH_MODE).equals(PSMode.USER) && profile!=null)
            tvAuthor.setText(profile.getFullName());
        getPins();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void hideBars() {
        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();
    }

    public void getPins() {
        HashMap<String, String> params = new HashMap<>();
        params.put(PDKClient.PDK_QUERY_PARAM_FIELDS, PIN_FIELDS);
        PDKClient.getInstance().getPath(AP.getStringData(this, AC.PATH_MODE), params, new PDKCallback() {
            @Override
            public void onSuccess(PDKResponse response) {
                List<PDKPin> pdkPins = response.getPinList();
                pins = Pin.castToMyList(pdkPins);
                if (pins!=null) {
                    Log.d(AC.TAG_NAME, "pin count:" + pins.size());
                    displayRightFlow();
                }
            }
            @Override
            public void onFailure(PDKException exception) {
                exception.printStackTrace();
                Log.d(AC.TAG_NAME, "cannot get pins");
            }
        });
    }

    private void displayRightFlow() {
        setupPager();
        tvContent.setText(pins.get(0).note);
    }

    private void displayPendingFlow() {
    }

    public static void go(Context context, BasicProfile profile) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(AC.KEK_BASIC_PROFILE, profile);
        context.startActivity(intent);
    }

    private void setupPager() {
        pager = container.getViewPager();
        ImageFragmentPagerAdapter adapter = new ImageFragmentPagerAdapter(getSupportFragmentManager(), pins);
        pager.setPageTransformer(false, new PageTransformer());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);
        pager.setPageMargin(32);
        pager.setClipChildren(false);
        pager.setCurrentItem(index);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                colorIndex++;
                if (colorIndex>3)
                    colorIndex=0;
                overlay.setBackgroundColor(ContextCompat.getColor(getBaseContext(), bgColors[colorIndex]));
                if (position>index)
                    startTextAnimation(false);
                else if (position<index)
                    startTextAnimation(true);
                index = position;
                if (pins.get(index).isLove) {
                    if (upArea.getVisibility()==View.VISIBLE)
                        loveIt(null);
                } else {
                    if (upArea.getVisibility() == View.INVISIBLE)
                        revertLoveItAcion();
                }
                tvContent.setText(pins.get(position).note);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void startTextAnimation(final boolean toLeft) {
        final float toMoveX;
        int duration = 500;
        if (toLeft)
            toMoveX = -8f;
        else
            toMoveX = 8f;

        ObjectAnimator animX = ObjectAnimator.ofFloat(tvTitle, "x", toMoveX);
        animX.setDuration(duration);
        ObjectAnimator animX1 = ObjectAnimator.ofFloat(tvContent, "x", toMoveX);
        animX.setDuration(duration);

        ObjectAnimator animFade = ObjectAnimator.ofFloat(tvTitle, "alpha", 0.4f);
        animFade.setDuration(duration);
        ObjectAnimator animFade1 = ObjectAnimator.ofFloat(tvContent, "alpha", 0.4f);
        animFade.setDuration(duration);
        ObjectAnimator animFade2 = ObjectAnimator.ofFloat(tvAuthor, "alpha", 0.4f);
        animFade.setDuration(duration);

        AnimatorSet animSetCollection = new AnimatorSet();
        animSetCollection.playTogether(animX, animFade, animX1, animFade1, animFade2);
        animSetCollection.start();

        animSetCollection.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                revertTextAnimation(toLeft);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void revertTextAnimation(boolean toLeft) {
        final float toMoveX;
        if (toLeft)
            toMoveX = 3f;
        else
            toMoveX = -3f;

        ObjectAnimator animX = ObjectAnimator.ofFloat(tvTitle, "x", toMoveX);
        ObjectAnimator animX1 = ObjectAnimator.ofFloat(tvContent, "x", toMoveX);

        ObjectAnimator animFade = ObjectAnimator.ofFloat(tvTitle, "alpha", 1f);
        ObjectAnimator animFade1 = ObjectAnimator.ofFloat(tvContent, "alpha", 1f);
        ObjectAnimator animFade2 = ObjectAnimator.ofFloat(tvAuthor, "alpha", 1f);

        AnimatorSet animSetCollection = new AnimatorSet();
        animSetCollection.playTogether(animX, animFade, animX1, animFade1, animFade2);
        animSetCollection.start();
    }

    public void loveIt(View view) {
        pins.get(index).isLove = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            upArea.setVisibility(View.INVISIBLE);
            heartInside.setVisibility(View.VISIBLE);
            ValueAnimator anim = ValueAnimator.ofArgb(ContextCompat.getColor(this, R.color.white_overlay),
                    ContextCompat.getColor(this, R.color.black_overlay));
            anim.addUpdateListener(valueAnimator -> card.setBackgroundColor((Integer) valueAnimator.getAnimatedValue()));
            anim.setDuration(500);
            anim.start();
        }
    }

    public void revertLoveItAcion() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            upArea.setVisibility(View.VISIBLE);
            heartInside.setVisibility(View.INVISIBLE);
            ValueAnimator anim = ValueAnimator.ofArgb(ContextCompat.getColor(this, R.color.black_overlay),
                    ContextCompat.getColor(this, R.color.white_overlay));
            anim.addUpdateListener(valueAnimator ->
                    card.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white)));
            anim.setDuration(0);
            anim.start();
        }
    }
}
