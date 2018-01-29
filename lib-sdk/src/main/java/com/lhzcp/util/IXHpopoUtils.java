package com.lhzcp.util;

import java.io.File;

import android.content.Context;
import dalvik.system.DexClassLoader;

public class IXHpopoUtils {
	/**
	 * 加载类
	 * 
	 * @param path
	 *            jar路径
	 * @param jarName
	 *            jar名字
	 * @param classpath
	 *            类路径
	 * @return
	 */
	public static Object loadDex(String path, String jarName, String classpath, Context context) {

		try {
			unloadDex(context, jarName);
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			/** dex临时存储路径 */
			File file = context.getDir("dex", 0);
			DexClassLoader classLoader = new DexClassLoader(path, file.getAbsolutePath(), null,
					context.getClassLoader());
			/** 实现类 */
			Class<?> libProviderClazz = classLoader.loadClass(classpath);
			Object obj = libProviderClazz.newInstance();
			unloadDex(context, jarName);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void unloadDex(Context context, String jarName) {
		try {
			/**  */
		} catch (Exception e) {
		}
	}
}
