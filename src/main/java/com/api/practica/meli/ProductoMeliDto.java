package com.api.practica.meli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoMeliDto {

    private String id;
    private String title;
    private String site_id;
    private BigDecimal price;
    private String currency_id;
    private Long available_quatity;
    private String stop_time;
    private String condition;
    private String thumbnail;

    private List<PictureItemDto> pictures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Long getAvailable_quatity() {
        return available_quatity;
    }

    public void setAvailable_quatity(Long available_quatity) {
        this.available_quatity = available_quatity;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<PictureItemDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureItemDto> pictures) {
        this.pictures = pictures;
    }
}
