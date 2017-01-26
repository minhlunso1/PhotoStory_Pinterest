package minhna.photostory_pinterest.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Administrator on 26-Jan-17.
 */

@StringDef({PSMode.AUTHOR, PSMode.USER})
@Retention(RetentionPolicy.SOURCE)
public @interface PSMode {
    String AUTHOR = "boards/minhandroidit/my-board/pins/";
    String USER = "me/pins/";
}
