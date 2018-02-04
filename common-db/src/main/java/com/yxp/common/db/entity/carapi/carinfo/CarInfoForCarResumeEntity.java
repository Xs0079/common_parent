package com.yxp.common.db.entity.carapi.carinfo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 *给汽车简历系统提供定时拉去成交订单信息数据库实体
 * @author yanzongrui
 */
public class CarInfoForCarResumeEntity {
    /**
     * 车架号 vin
     */
    private String vin;
    /**
     * 品牌名称
     */
    private String branName;
    /**
     * 品牌Id
     */
    private Long branId;
    /**
     * 车系名称
     */
    private String serieName;
    /**
     * 车系Id
     */
    private Long serieId;
    /**
     * 车型名称
     */
    private String producerName;
    /**
     * 车型Id
     */
    private Long producerId;
    /**
     * 上牌时间
     */
    private Date liceseYear;
    /**
     * 里程数
     */
    private Long mileage;
    /**
     * 车源城市名称
     */
    private String cityName;
    /**
     * 车源城市Id
     */
    private Integer cityId;
    /**
     * 买家成交价
     */
    private BigDecimal price;

    /**
     *拍品Id
     */
    private Long publishId;

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBranName() {
        return branName;
    }

    public void setBranName(String branName) {
        this.branName = branName;
    }

    public Long getBranId() {
        return branId;
    }

    public void setBranId(Long branId) {
        this.branId = branId;
    }

    public String getSerieName() {
        return serieName;
    }

    public void setSerieName(String serieName) {
        this.serieName = serieName;
    }

    public Long getSerieId() {
        return serieId;
    }

    public void setSerieId(Long serieId) {
        this.serieId = serieId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getLiceseYear() {
        return liceseYear;
    }

    public void setLiceseYear(Date liceseYear) {
        this.liceseYear = liceseYear;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
