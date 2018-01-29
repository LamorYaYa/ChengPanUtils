package com.lhzcp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;

import com.lhzcp.I.IXHdownLoadListener;
import com.lhzcp.I.IXHhttpListener;
import com.lhzcp.I.IXHtestListener;
import com.lhzcp.app.MApp;

public class LoadData extends Thread {

	private Context mContext;
	private String savePath;
	private String dataName;
	private static final String loadurl = MApp.LOADURL;
	private static IXHhttpListener mHttpListener;
	private static IXHtestListener ixh;

	public LoadData(Context context, String path, String name, IXHhttpListener listener) {
		mContext = context;
		savePath = path;
		dataName = name;
		mHttpListener = listener;
	}

	@Override
	public void run() {
		/** 每次都去下载文件 */
		updata(new IXHdownLoadListener() {

			@Override
			public void onDownCallback(String msg) {
				if ("success".equalsIgnoreCase(msg)) {
					/** 下载成功 */
					copyDownloadedJar(savePath, DexLoadConfig.JAR_NAME);
					ixh = (IXHtestListener) IXHpopoUtils.loadDex(savePath + DexLoadConfig.JAR_NAME,
							DexLoadConfig.JAR_NAME, DexLoadConfig.CLASS_PATH, mContext);
					mHttpListener.OnResult(ixh);
				} else {
					/** 下载失败 */
					mHttpListener.OnResult(null);
				}

			}
		});
	}

	private void updata(IXHdownLoadListener callback) {
		FileOutputStream output;
		InputStream input = null;
		try {
			File cFile = new File(savePath, dataName);
			/** 存在则不下载,需要添加一些判定条件 */
			// if (cFile.exists() && !cFile.isDirectory()) {
			// if (callback != null) {
			// callback.onDownCallback("success");
			// callback = null;
			// return;
			// }
			// }
			new File(savePath).mkdirs();
			cFile.createNewFile();
			URL url = new URL(loadurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			input = conn.getInputStream();
			int length = input.available();
			output = new FileOutputStream(cFile);
			byte[] buffer = new byte[length];
			// byte[] buffer = new byte[4 * 1024];
			while (input.read(buffer) != -1) {
				output.write(buffer);
			}
			output.flush();
			input.close();
			output.close();
			if (callback != null) {
				callback.onDownCallback("success");
				callback = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (callback != null) {
				callback.onDownCallback("error");
				callback = null;
			}
		}
	}

	/** 把目标文件拷贝至内存 */
	private static String copyDownloadedJar(String filepath, String jarName) {

		try {
			if (filepath.endsWith("/")) {
				filepath = filepath.substring(0, filepath.length() - 1);
			}
			/** 目标文件 */
			String savePath = filepath + "/" + jarName;
			/** 解密后的文件 */
			String tmpPath = filepath + "/" + "temp.jar";
			File file = new File(tmpPath);
			if (file.exists()) {
				file.delete();
			}

			copyBigDataToSD(savePath, tmpPath);
			/** 删除文件 */
			new File(savePath).delete();
			/** 修改文件名字 */
			new File(tmpPath).renameTo(new File(savePath));
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 文件解密
	 * 
	 * @param src
	 *            要解密的文件
	 * @param dest
	 *            解密后的文件
	 * @return
	 */
	private static String copyBigDataToSD(String src, String dest) {
		try {
			InputStream myInput = new FileInputStream(src);
			int mFileLength = myInput.available();
			byte[] buffer = new byte[mFileLength];
			int length = myInput.read(buffer);
			OutputStream myOutput = new FileOutputStream(dest);
			byte[] buffer2 = new byte[mFileLength];
			while (length > 0) {
				for (int i = 0; i < buffer.length; i++) {
					byte b = buffer[i];
					b ^= DexLoadConfig.JAR_KEY;
					buffer2[i] = b;
				}
				myOutput.write(buffer2, 0, length);
				length = myInput.read(buffer);
			}
			myOutput.flush();
			myInput.close();
			myOutput.close();
		} catch (Exception e) {
		}
		return "";
	}
}
