package com.lhzcpan.imsi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by master on 2017/12/26.
 */

public class GetProp {

    public void getProp() {
        Map<String, String> mHashMap = new HashMap<>();
        try {
            Process process = Runtime.getRuntime().exec("getprop");
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            BufferedReader input = new BufferedReader(ir);
            while (input.readLine() != null) {
                String a = input.readLine();
                String[] temp = null;
                temp = a.split("]:");
                if (temp.length > 1) {
                    mHashMap.put(temp[0], temp[1]);
                } else {
                    mHashMap.put(temp[0], "[]");
                }
            }


        } catch (Exception e) {

        }
    }


}
