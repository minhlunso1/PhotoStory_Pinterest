package minhna.photostory_pinterest.util;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 * Created by Administrator on 25-Jan-17.
 */

public class PhotoStoryUtil {

    public int getWidthScreen(AppCompatActivity context){
        Display display = context.getWindowManager().getDefaultDisplay();
        return display.getWidth();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
