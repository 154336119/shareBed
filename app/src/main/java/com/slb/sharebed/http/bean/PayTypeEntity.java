package com.slb.sharebed.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PayTypeEntity implements Parcelable {

    /**
     * wechatQrcode : http://img.xikeqiche.com/upload/2019-06-05/5eda76f3-3288-4a42-b6b6-8a9d0f276f22.png
     * bankAccount : 6228145698296378
     * showPay : 1
     * alipayQrcode : http://img.xikeqiche.com/upload/2019-06-05/2df5dab0-c945-4ee8-8abe-2bfdd911384f.png
     * bankCompany : 常州西柯汽车科技有限公司
     * bankTax : 123456789
     * bankName : 常州支行
     */

    private String wechatQrcode;
    private String bankAccount;
    private String showPay;
    private String alipayQrcode;
    private String bankCompany;
    private String bankTax;
    private String bankName;

    public String getWechatQrcode() {
        return wechatQrcode;
    }

    public void setWechatQrcode(String wechatQrcode) {
        this.wechatQrcode = wechatQrcode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getShowPay() {
        return showPay;
    }

    public void setShowPay(String showPay) {
        this.showPay = showPay;
    }

    public String getAlipayQrcode() {
        return alipayQrcode;
    }

    public void setAlipayQrcode(String alipayQrcode) {
        this.alipayQrcode = alipayQrcode;
    }

    public String getBankCompany() {
        return bankCompany;
    }

    public void setBankCompany(String bankCompany) {
        this.bankCompany = bankCompany;
    }

    public String getBankTax() {
        return bankTax;
    }

    public void setBankTax(String bankTax) {
        this.bankTax = bankTax;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wechatQrcode);
        dest.writeString(this.bankAccount);
        dest.writeString(this.showPay);
        dest.writeString(this.alipayQrcode);
        dest.writeString(this.bankCompany);
        dest.writeString(this.bankTax);
        dest.writeString(this.bankName);
    }

    public PayTypeEntity() {
    }

    protected PayTypeEntity(Parcel in) {
        this.wechatQrcode = in.readString();
        this.bankAccount = in.readString();
        this.showPay = in.readString();
        this.alipayQrcode = in.readString();
        this.bankCompany = in.readString();
        this.bankTax = in.readString();
        this.bankName = in.readString();
    }

    public static final Creator<PayTypeEntity> CREATOR = new Creator<PayTypeEntity>() {
        @Override
        public PayTypeEntity createFromParcel(Parcel source) {
            return new PayTypeEntity(source);
        }

        @Override
        public PayTypeEntity[] newArray(int size) {
            return new PayTypeEntity[size];
        }
    };
}
