package minhna.photostory_pinterest.module;

import minhna.photostory_pinterest.util.PhotoStoryUtil;

/**
 * Created by Administrator on 25-Jan-17.
 */

//prefer to using Dagger but I want to make it quick
public class AC {

    public final static String TAG_NAME = "PS_PDK";
    final static String APP_ID = "4880824334284768215";
    public final static String IMAGE_URL = "imageUrl";
    public final static String PATH_MODE = "pathMode";
//    public final static String KEK_PDK_RESULT = "PDKCLIENT_EXTRA_RESULT";
    public final static String KEK_BASIC_PROFILE = "basicProfile";
    public final static String PINTEREST_PACKAGE = "com.pinterest";

    public static int PREFERRED_IMAGE_WIDTH_FULL = PhotoStoryUtil.dpToPx(320);
    public static int PREFERRED_IMAGE_HEIGHT_FULL = PhotoStoryUtil.dpToPx(320);

}
