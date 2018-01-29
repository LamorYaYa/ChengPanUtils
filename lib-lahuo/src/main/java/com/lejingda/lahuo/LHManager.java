package com.lejingda.lahuo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lejingda.lahuo.interfaces.LHInterface;

import org.json.JSONObject;

/**
 * @author master
 * @date 2018/1/22
 */

public class LHManager {

    private static final LHInterface lhImple = new LHImple();

    public static void init(Context context) {
        lhImple.init(context);
    }

    public static void showTextNotification(Context context, String data) {
        lhImple.showTextNotification(context, data);
    }

    public static void showBigBitmapNotification(final Context context, final String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Glide.with(context).load(jsonObject.optString("icon")).asBitmap().into(new SimpleTarget<Bitmap>() {

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    lhImple.showBigBitmapNotification(context, data, null);
                }

                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    lhImple.showBigBitmapNotification(context, data, resource);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTextBitmapNotification(final Context context, final String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Glide.with(context).load(jsonObject.optString("icon")).asBitmap().into(new SimpleTarget<Bitmap>() {

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    lhImple.showTextBitmapNotification(context, data, null);
                }

                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    lhImple.showTextBitmapNotification(context, data, resource);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
