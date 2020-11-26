package com.nhuhoa.puppystore.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


@Entity
public class UserInformation implements Serializable {

    @SerializedName("idPurchase")
    @ColumnInfo(name="idPurchase")
    @PrimaryKey(autoGenerate = true)
    private int idPurchase;
    @SerializedName("idUser")
    @ColumnInfo(name="idUser")
    private int idUser;

    @SerializedName("username")
    @ColumnInfo(name="username")
    private String username;

    @SerializedName("email")
    @ColumnInfo(name="email")
    private String email;

    @SerializedName("phone")
    @ColumnInfo(name="phone")
    private String phone;

    @SerializedName("address")
    @ColumnInfo(name="address")
    private String address;

    @SerializedName("quantity")
    @ColumnInfo(name="quantity")
    private int quantity;

    @SerializedName("idProduct")
    @ColumnInfo(name="idProduct")
    private int idProduct;

    @SerializedName("totalPrice")
    @ColumnInfo(name="totalPrice")
    private double totalPrice;

    @SerializedName("paymentMethod")
    @ColumnInfo(name="paymentMethod")
    private String paymentMethod;

    @SerializedName("purchaseTime")
    @ColumnInfo(name="purchaseTime")
    private String purchaseTime;
    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPurchaseTime() {
        Date currentDateTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String purchaseTime = dateFormat.format(currentDateTime);
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "idPurchase=" + idPurchase +
                ", idUser=" + idUser +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", quantity=" + quantity +
                ", idProduct=" + idProduct +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", purchaseTime='" + purchaseTime + '\'' +
                '}';
    }

    public UserInformation(int idPurchase, int idUser, String username, String email, String phone, String address, int quantity, int idProduct, double totalPrice, String paymentMethod, String purchaseTime) {
        this.idPurchase = idPurchase;
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.purchaseTime = purchaseTime;
    }

    public UserInformation() {
    }
}
