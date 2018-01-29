package com.lhzcp;

import android.util.Log;

/**
 * @author master
 */
public class IXHlogUtils {

	private static final String TAG = "IXH";
	private static final boolean isDebug = true;

	public static void e(String msg) {
		if (isDebug) {
			Log.e(TAG, "--->\n" + msg);
		}
	}

}
