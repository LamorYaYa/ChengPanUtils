package com.lhzcpan.f.util;

import com.lhzcpan.f.bean.HomeBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 * @date 2018/3/7
 */

public class NewsUtil {

    public static List<HomeBean> getZhiHuList(String data) {
        List<HomeBean> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.optJSONArray("stories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                int id = jsonObject1.optInt("id");
                String title = jsonObject1.optString("title");
                String imgUrl = jsonObject1.optJSONArray("images").optString(0);
                HomeBean homeBean = new HomeBean(id, title, imgUrl);
                list.add(homeBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
