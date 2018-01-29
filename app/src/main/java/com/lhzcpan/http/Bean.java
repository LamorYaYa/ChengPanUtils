package com.lhzcpan.http;

/**
 * Created by master on 2017/12/4.
 */

public class Bean {

    String url;
    String path;
    String name;
    String adid;


    public Bean(String url, String path, String name, String adid) {
        this.url = url;
        this.path = path;
        this.name = name;
        this.adid = adid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }
}
