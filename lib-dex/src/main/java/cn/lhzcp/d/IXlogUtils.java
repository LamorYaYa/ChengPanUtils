package cn.lhzcp.d;

import android.util.Log;

public class IXlogUtils {

	private static final String TAG = "IXH";
	private static final boolean isDebug = true;

	public static void e(String msg) {
		if (isDebug) {
			Log.e(TAG, "--->\n" + msg);
		}
	}

}
