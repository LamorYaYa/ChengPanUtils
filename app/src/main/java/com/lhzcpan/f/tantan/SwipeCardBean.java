package com.lhzcpan.f.tantan;

import java.util.ArrayList;
import java.util.List;

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/18.
 */

public class SwipeCardBean {
    private int postition;
    private String url;
    private String name;

    public SwipeCardBean(int postition, String url, String name) {
        this.postition = postition;
        this.url = url;
        this.name = name;
    }

    public int getPostition() {
        return postition;
    }

    public SwipeCardBean setPostition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SwipeCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public SwipeCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SwipeCardBean> initDatas() {
        List<SwipeCardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new SwipeCardBean(i++, "http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg", "XXX-001"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179596182&di=9447ad25d8cfac7134a1382a5f032f93&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201502%2F24%2F20150224100704_muy2E.thumb.700_0.jpeg", "XXX-002"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179596180&di=97fd2dbc630948f45e456aaf2c2d2d4a&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201506%2F16%2F20150616102457_svf5n.jpeg", "XXX-003"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179596169&di=f1c943563577605a9d641bf431bf88ae&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2Fa%2F55792d8f5aa0e.jpg", "XXX-004"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179596168&di=5a4c5c764365c5d6bf042deb188a35e2&imgtype=0&src=http%3A%2F%2Fimg.lenovomm.com%2Fs3%2Fimg%2Fapp%2Fapp-img-lestore%2F5208-2015-07-09102451-1436451891778.jpg%3FisCompress%3Dtrue%26width%3D320%26height%3D480%26quantity%3D1%26rotate%3Dtrue", "XXX-005"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179596166&di=eeeb81d17898ceae3cb8c62f1fd49e55&imgtype=0&src=http%3A%2F%2Fimg4.hao76.com%2Fupload%2Fimages%2F2015%2F10%2F09%2F1444353120819675.jpg", "XXX-006"));
        datas.add(new SwipeCardBean(i++, "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=911824898,2906936862&fm=27&gp=0.jpg", "XXX-007"));
        datas.add(new SwipeCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523179742550&di=3ed86e8182b57780191c53592662888c&imgtype=0&src=http%3A%2F%2Fb.zol-img.com.cn%2Fsjbizhi%2Fimages%2F7%2F320x510%2F141577895116.jpg", "XXX-008"));
        return datas;
    }
}
