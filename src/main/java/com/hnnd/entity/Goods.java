package com.hnnd.entity;

/**
 * 商品表
 * `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `goods_name` varchar(100) NOT NULL,
 `goods_title` varchar(100) NOT NULL,
 `goods_img` varchar(200) NOT NULL,
 `goods_detail` varchar(200) NOT NULL,
 `goods_price` decimal(10,2) NOT NULL,
 `goods_stock` int(10) DEFAULT NULL,
 * Created by 85073 on 2018/4/1.
 */
public class Goods {

    private long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private double goodsPrice;

    private Integer goodsStock;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }
}
