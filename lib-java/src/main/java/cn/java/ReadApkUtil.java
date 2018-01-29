package cn.java;

import java.io.File;

/**
 * @author master
 * @date 2018/1/26
 */

public class ReadApkUtil {

    private static final String urlPath = "D:\\app";

    public static void main(String[] args) {
        findApk(urlPath);
    }

    private static void findApk(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith("apk")) {
                copyApk(f);
            } else if (f.isDirectory()) {
                findApk(f.getPath());
            }
        }
    }

    private static void copyApk(File f) {
        File file = new File("D:\\app\\1111111\\" + f.getName());
        f.renameTo(file);
    }

}
