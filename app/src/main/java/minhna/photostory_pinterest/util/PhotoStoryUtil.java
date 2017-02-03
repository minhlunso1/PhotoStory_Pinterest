package minhna.photostory_pinterest.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
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

    public static boolean isPackageExisted(Context context, String targetPackage){
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(targetPackage,PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void installApp(Context context, String targetPackage) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + targetPackage));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        } catch (android.content.ActivityNotFoundException anfe) {
            anfe.printStackTrace();
        }
    }

}
