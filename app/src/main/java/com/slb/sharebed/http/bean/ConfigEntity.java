package com.slb.sharebed.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigEntity implements Parcelable {
    /**
     *   "config_remark": "关于我们",
     *       "config_value": "<p>这是关于我们</p>"
     */
    ConfigItemEntity ABOUT_US;
    /**
     * "config_remark": "用户指南",
     *       "config_value": "<p>这里是用户协议</p>"
     */
    ConfigItemEntity USER_GUIDE;
    /**
     *   "config_remark": "法律声明与平台规则",
     *       "config_value": "<p style=\"text-align: justify;\"><img src=\"http://img.baidu.com/hi/jx2/j_0015.gif\"/>法律声明与平台规则<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\"/></p>"
     */
    ConfigItemEntity LAW_DECLARE;
    /**
     *  "config_remark": "客服电话",
     *       "config_value": "0512-66668788"
     */
    ConfigItemEntity KEFU_TEL;
    /**
     *   "config_remark": "APP首页背景图",
     *       "config_value": "http://sharebed.lanlin.site/upload/2019-07-18/fd20b3fe-fc98-4b63-b117-c0c5b1fdd424.png"
     */
    ConfigItemEntity INDEX_IMG;
    /**
     *   "config_remark": "4个充值金额",
     *       "config_value": "[{\"money\":150,\"is_hot\":true},{\"money\":120,\"is_hot\":false},{\"money\":100,\"is_hot\":false},{\"money\":50,\"is_hot\":false},{\"money\":20,\"is_hot\":false},{\"money\":10,\"is_hot\":false}]"
     */
    ConfigItemEntity CHARGE_MONEY;
    /**
     * "config_remark": "床位租赁计费单价/小时",
     *       "config_value": "50"
     */
    ConfigItemEntity BED_SINGLE_PRICE;
    /**
     *  "config_remark": "押金金额",
     *       "config_value": "199"
     */
    ConfigItemEntity DEPOSIT_MONEY;


    public ConfigItemEntity getABOUT_US() {
        return ABOUT_US;
    }

    public void setABOUT_US(ConfigItemEntity ABOUT_US) {
        this.ABOUT_US = ABOUT_US;
    }

    public ConfigItemEntity getUSER_GUIDE() {
        return USER_GUIDE;
    }

    public void setUSER_GUIDE(ConfigItemEntity USER_GUIDE) {
        this.USER_GUIDE = USER_GUIDE;
    }

    public ConfigItemEntity getLAW_DECLARE() {
        return LAW_DECLARE;
    }

    public void setLAW_DECLARE(ConfigItemEntity LAW_DECLARE) {
        this.LAW_DECLARE = LAW_DECLARE;
    }

    public ConfigItemEntity getKEFU_TEL() {
        return KEFU_TEL;
    }

    public void setKEFU_TEL(ConfigItemEntity KEFU_TEL) {
        this.KEFU_TEL = KEFU_TEL;
    }

    public ConfigItemEntity getINDEX_IMG() {
        return INDEX_IMG;
    }

    public void setINDEX_IMG(ConfigItemEntity INDEX_IMG) {
        this.INDEX_IMG = INDEX_IMG;
    }

    public ConfigItemEntity getCHARGE_MONEY() {
        return CHARGE_MONEY;
    }

    public void setCHARGE_MONEY(ConfigItemEntity CHARGE_MONEY) {
        this.CHARGE_MONEY = CHARGE_MONEY;
    }

    public ConfigItemEntity getBED_SINGLE_PRICE() {
        return BED_SINGLE_PRICE;
    }

    public void setBED_SINGLE_PRICE(ConfigItemEntity BED_SINGLE_PRICE) {
        this.BED_SINGLE_PRICE = BED_SINGLE_PRICE;
    }

    public ConfigItemEntity getDEPOSIT_MONEY() {
        return DEPOSIT_MONEY;
    }

    public void setDEPOSIT_MONEY(ConfigItemEntity DEPOSIT_MONEY) {
        this.DEPOSIT_MONEY = DEPOSIT_MONEY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.ABOUT_US, flags);
        dest.writeParcelable(this.USER_GUIDE, flags);
        dest.writeParcelable(this.LAW_DECLARE, flags);
        dest.writeParcelable(this.KEFU_TEL, flags);
        dest.writeParcelable(this.INDEX_IMG, flags);
        dest.writeParcelable(this.CHARGE_MONEY, flags);
        dest.writeParcelable(this.BED_SINGLE_PRICE, flags);
        dest.writeParcelable(this.DEPOSIT_MONEY, flags);
    }

    public ConfigEntity() {
    }

    protected ConfigEntity(Parcel in) {
        this.ABOUT_US = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.USER_GUIDE = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.LAW_DECLARE = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.KEFU_TEL = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.INDEX_IMG = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.CHARGE_MONEY = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.BED_SINGLE_PRICE = in.readParcelable(ConfigItemEntity.class.getClassLoader());
        this.DEPOSIT_MONEY = in.readParcelable(ConfigItemEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ConfigEntity> CREATOR = new Parcelable.Creator<ConfigEntity>() {
        @Override
        public ConfigEntity createFromParcel(Parcel source) {
            return new ConfigEntity(source);
        }

        @Override
        public ConfigEntity[] newArray(int size) {
            return new ConfigEntity[size];
        }
    };
}
