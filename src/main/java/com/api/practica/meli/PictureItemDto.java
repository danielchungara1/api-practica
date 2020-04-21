package com.api.practica.meli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureItemDto {

    private String id; //": "793498-MLA32146296718_092019",
    private String url; //": "http://mla-s2-p.mlstatic.com/793498-MLA32146296718_092019-O.jpg",
    private String secure_url; //": "https://mla-s2-p.mlstatic.com/793498-MLA32146296718_092019-O.jpg",
    private String size; //": "258x500",
    private String max_size; //": "620x1200",
    private String quality; //": ""

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecure_url() {
        return secure_url;
    }

    public void setSecure_url(String secure_url) {
        this.secure_url = secure_url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMax_size() {
        return max_size;
    }

    public void setMax_size(String max_size) {
        this.max_size = max_size;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
