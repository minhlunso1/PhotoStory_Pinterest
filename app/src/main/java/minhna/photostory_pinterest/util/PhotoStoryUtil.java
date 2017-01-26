package minhna.photostory_pinterest.util;

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

}
