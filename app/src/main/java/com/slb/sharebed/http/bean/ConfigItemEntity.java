package com.slb.sharebed.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigItemEntity implements Parcelable {

    /**
     * id : 1
     * config_remark : 关于我们
     * config_value : <p>这是关于我们</p>
     */

    private int id;
    private String config_remark;
    private String config_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfig_remark() {
        return config_remark;
    }

    public void setConfig_remark(String config_remark) {
        this.config_remark = config_remark;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.config_remark);
        dest.writeString(this.config_value);
    }

    public ConfigItemEntity() {
    }

    protected ConfigItemEntity(Parcel in) {
        this.id = in.readInt();
        this.config_remark = in.readString();
        this.config_value = in.readString();
    }

    public static final Parcelable.Creator<ConfigItemEntity> CREATOR = new Parcelable.Creator<ConfigItemEntity>() {
        @Override
        public ConfigItemEntity createFromParcel(Parcel source) {
            return new ConfigItemEntity(source);
        }

        @Override
        public ConfigItemEntity[] newArray(int size) {
            return new ConfigItemEntity[size];
        }
    };
}
