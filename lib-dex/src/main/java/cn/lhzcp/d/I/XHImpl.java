package cn.lhzcp.d.I;

import android.app.Activity;
import android.widget.Toast;

import cn.lhzcp.I.IXHtestListener;

/**
 * @author master
 */
public class XHImpl implements IXHtestListener {

	@Override
	public void a(Activity ac) {
		/** 初始化 */
		dexInit(ac);
	}

	@Override
	public void b(Activity ac) {
		showToast(ac, "测试B");
	}

	@Override
	public void c(Activity ac) {
		showToast(ac, "测试C");
	}

	private void showToast(final Activity ac, final String msg) {
		ac.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(ac, msg, Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void dexInit(Activity ac) {
		showToast(ac, "初始化完成");
	}

}
