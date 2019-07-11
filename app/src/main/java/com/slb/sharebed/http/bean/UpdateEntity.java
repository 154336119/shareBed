package com.slb.sharebed.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdateEntity implements Parcelable {


    /**
     * platform : 1
     * version_num : 13
     * upgrade_desc : 1、修复若干bug
     * upgrade_url : http://sj.qq.com/myapp/detail.htm?apkName=com.meijiang.printsearch
     * create_time : 2019-05-30 20:09:59
     */

    private int platform;
    private int version_num;
    private String upgrade_desc;
    private String upgrade_url;
    private String create_time;

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getVersion_num() {
        return version_num;
    }

    public void setVersion_num(int version_num) {
        this.version_num = version_num;
    }

    public String getUpgrade_desc() {
        return upgrade_desc;
    }

    public void setUpgrade_desc(String upgrade_desc) {
        this.upgrade_desc = upgrade_desc;
    }

    public String getUpgrade_url() {
        return upgrade_url;
    }

    public void setUpgrade_url(String upgrade_url) {
        this.upgrade_url = upgrade_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.platform);
        dest.writeInt(this.version_num);
        dest.writeString(this.upgrade_desc);
        dest.writeString(this.upgrade_url);
        dest.writeString(this.create_time);
    }

    public UpdateEntity() {
    }

    protected UpdateEntity(Parcel in) {
        this.platform = in.readInt();
        this.version_num = in.readInt();
        this.upgrade_desc = in.readString();
        this.upgrade_url = in.readString();
        this.create_time = in.readString();
    }

    public static final Creator<UpdateEntity> CREATOR = new Creator<UpdateEntity>() {
        @Override
        public UpdateEntity createFromParcel(Parcel source) {
            return new UpdateEntity(source);
        }

        @Override
        public UpdateEntity[] newArray(int size) {
            return new UpdateEntity[size];
        }
    };
}