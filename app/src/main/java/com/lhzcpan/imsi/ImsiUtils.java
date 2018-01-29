package com.lhzcpan.imsi;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author master
 * @date 2017/12/25
 */

public class ImsiUtils {
    private Integer simId_1 = 0;
    private Integer simId_2 = 1;
    private String imsi_1 = "";
    private String imsi_2 = "";
    private String imei_1 = "";
    private String imei_2 = "";
    private Context mContext;

    public class ImsiInfo {
        public String chipName;
        public String imsi_1;
        public String imei_1;
        public String imsi_2;
        public String imei_2;

        public String getChipName() {
            return chipName;
        }

        public void setChipName(String chipName) {
            this.chipName = chipName;
        }

        public String getImsi_1() {
            return imsi_1;
        }

        public void setImsi_1(String imsi_1) {
            this.imsi_1 = imsi_1;
        }

        public String getImei_1() {
            return imei_1;
        }

        public void setImei_1(String imei_1) {
            this.imei_1 = imei_1;
        }

        public String getImsi_2() {
            return imsi_2;
        }

        public void setImsi_2(String imsi_2) {
            this.imsi_2 = imsi_2;
        }

        public String getImei_2() {
            return imei_2;
        }

        public void setImei_2(String imei_2) {
            this.imei_2 = imei_2;
        }

        @Override
        public String toString() {
            return "ImsiInfo{" +
                    "chipName='" + chipName + '\'' +
                    ", imsi_1='" + imsi_1 + '\'' +
                    ", imei_1='" + imei_1 + '\'' +
                    ", imsi_2='" + imsi_2 + '\'' +
                    ", imei_2='" + imei_2 + '\'' +
                    '}';
        }
    }


    public ImsiUtils(Context mContext) {
        this.mContext = mContext;
    }

    public boolean contentIMSI() {
        boolean flag = false;
        ImsiInfo imsInfo = getImsiInfo();
        if (imsInfo != null) {
            if (!TextUtils.isEmpty(imsInfo.getImsi_1())) {
                flag = true;
            }
            if (!TextUtils.isEmpty(imsInfo.getImei_1())) {
                flag = true;
            }
            if (!TextUtils.isEmpty(imsInfo.getImsi_2())) {
                flag = true;
            }
            if (!TextUtils.isEmpty(imsInfo.getImei_2())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取ImsiInfo
     *
     * @return
     */
    public ImsiInfo getImsiInfo() {
        ImsiInfo imsInfo = initQualcommDoubleSim();
        if (imsInfo != null) {
            return imsInfo;
        } else {
            imsInfo = initMtkDoubleSim();
            if (imsInfo != null) {
                return imsInfo;
            } else {
                imsInfo = initMtkSecondDoubleSim();
                if (imsInfo != null) {
                    return imsInfo;
                } else {
                    imsInfo = initSpreadDoubleSim();
                    if (imsInfo != null) {
                        return imsInfo;
                    } else {
                        imsInfo = getIMSI();
                        if (imsInfo != null) {
                            return imsInfo;
                        } else {
                            imsInfo = null;
                            return imsInfo;
                        }
                    }
                }
            }
        }
    }

    /**
     * MTK的芯片的判断
     *
     * @return
     */
    public ImsiInfo initMtkDoubleSim() {
        ImsiInfo imsInfo = null;
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> c = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = c.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            simId_1 = (Integer) fields1.get(null);
            Field fields2 = c.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            simId_2 = (Integer) fields2.get(null);

            Method m = TelephonyManager.class.getDeclaredMethod(
                    "getSubscriberIdGemini", int.class);
            imsi_1 = (String) m.invoke(tm, simId_1);
            imsi_2 = (String) m.invoke(tm, simId_2);

            Method m1 = TelephonyManager.class.getDeclaredMethod(
                    "getDeviceIdGemini", int.class);
            imei_1 = (String) m1.invoke(tm, simId_1);
            imei_2 = (String) m1.invoke(tm, simId_2);

            imsInfo = new ImsiInfo();
            imsInfo.chipName = "MTK芯片";
            imsInfo.imei_1 = imei_1;
            imsInfo.imei_2 = imei_2;
            imsInfo.imsi_1 = imsi_1;
            imsInfo.imsi_2 = imsi_2;

        } catch (Exception e) {
            imsInfo = null;
            return imsInfo;
        }
        return imsInfo;
    }

    /**
     * MTK的芯片的判断2
     *
     * @return
     */
    public ImsiInfo initMtkSecondDoubleSim() {
        ImsiInfo imsInfo = null;
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> c = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = c.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            simId_1 = (Integer) fields1.get(null);
            Field fields2 = c.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            simId_2 = (Integer) fields2.get(null);

            Method mx = TelephonyManager.class.getMethod("getDefault",
                    int.class);
            TelephonyManager tm1 = (TelephonyManager) mx.invoke(tm, simId_1);
            TelephonyManager tm2 = (TelephonyManager) mx.invoke(tm, simId_2);

            imsi_1 = tm1.getSubscriberId();
            imsi_2 = tm2.getSubscriberId();

            imei_1 = tm1.getDeviceId();
            imei_2 = tm2.getDeviceId();

            imsInfo = new ImsiInfo();
            imsInfo.chipName = "MTK芯片";
            imsInfo.imei_1 = imei_1;
            imsInfo.imei_2 = imei_2;
            imsInfo.imsi_1 = imsi_1;
            imsInfo.imsi_2 = imsi_2;

        } catch (Exception e) {
            imsInfo = null;
            return imsInfo;
        }
        return imsInfo;
    }

    /**
     * 展讯芯片的判断
     *
     * @return
     */
    public ImsiInfo initSpreadDoubleSim() {
        ImsiInfo imsInfo = null;
        try {
            Class<?> c = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method m = c.getMethod("getServiceName", String.class, int.class);
            String spreadTmService = (String) m.invoke(c,
                    Context.TELEPHONY_SERVICE, 1);
            TelephonyManager tm = (TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imsi_1 = tm.getSubscriberId();
            imei_1 = tm.getDeviceId();
            TelephonyManager tm1 = (TelephonyManager) mContext
                    .getSystemService(spreadTmService);
            imsi_2 = tm1.getSubscriberId();
            imei_2 = tm1.getDeviceId();
            imsInfo = new ImsiInfo();
            imsInfo.chipName = "展讯芯片";
            imsInfo.imei_1 = imei_1;
            imsInfo.imei_2 = imei_2;
            imsInfo.imsi_1 = imsi_1;
            imsInfo.imsi_2 = imsi_2;
        } catch (Exception e) {
            imsInfo = null;
            return imsInfo;
        }
        return imsInfo;
    }

    /**
     * 高通芯片判断
     *
     * @return
     */
    public ImsiInfo initQualcommDoubleSim() {
        ImsiInfo imsInfo = null;
        try {
            Class<?> cx = Class.forName("android.telephony.MSimTelephonyManager");
            Object obj = mContext.getSystemService("phone_msim");
            Method md = cx.getMethod("getDeviceId", int.class);
            Method ms = cx.getMethod("getSubscriberId", int.class);
            imei_1 = (String) md.invoke(obj, simId_1);
            imei_2 = (String) md.invoke(obj, simId_2);
            imsi_1 = (String) ms.invoke(obj, simId_1);
            imsi_2 = (String) ms.invoke(obj, simId_2);
            int statephoneType_2 = 0;
            boolean flag = false;
            try {
                Method mx = cx.getMethod("getPreferredDataSubscription", int.class);
                Method is = cx.getMethod("isMultiSimEnabled", int.class);
                statephoneType_2 = (Integer) mx.invoke(obj);
                flag = (Boolean) is.invoke(obj);
            } catch (Exception e) {
                // TODO: handle exception
            }
            imsInfo = new ImsiInfo();
            imsInfo.chipName = "高通芯片-getPreferredDataSubscription:" + statephoneType_2 + ",\nflag:" + flag;
            imsInfo.imei_1 = imei_1;
            imsInfo.imei_2 = imei_2;
            imsInfo.imsi_1 = imsi_1;
            imsInfo.imsi_2 = imsi_2;

        } catch (Exception e) {
            imsInfo = null;
            return imsInfo;
        }
        return imsInfo;
    }

    /**
     * 系统的api
     *
     * @return
     */
    public ImsiInfo getIMSI() {
        ImsiInfo imsInfo = null;
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            imsi_1 = tm.getSubscriberId();
            imei_1 = tm.getDeviceId();
        } catch (Exception e) {
            imsInfo = null;
            return imsInfo;
        }
        if (TextUtils.isEmpty(imsi_1) || imsi_1.length() < 10) {
            imsInfo = null;
            return imsInfo;
        } else {
            imsInfo = new ImsiInfo();
            imsInfo.chipName = "单卡芯片";
            imsInfo.imei_1 = imei_1;
            imsInfo.imei_2 = "没有";
            imsInfo.imsi_1 = imsi_1;
            imsInfo.imsi_2 = "没有";
            return imsInfo;
        }

    }
}