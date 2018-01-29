package cn.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author master
 */
public class JavaExclusive {

    public static void main(String[] args) {
        copyBigDataToSD("D://javaTest//ii.jar", "D://javaTest//custom.jar");
    }

    private static boolean copyBigDataToSD(String src, String dest) {
        try {
            InputStream myInput = new FileInputStream(src);
            byte[] buffer = new byte[1024];
            int length = myInput.read(buffer);
            OutputStream myOutput = new FileOutputStream(dest);
            byte[] buffer2 = new byte[1024];
            while (length > 0) {
                for (int i = 0; i < buffer.length; i++) {
                    byte b = buffer[i];
                    b ^= 'p';
                    buffer2[i] = b;
                }
                myOutput.write(buffer2, 0, length);
                length = myInput.read(buffer);
            }
            myOutput.flush();
            myInput.close();
            myOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
