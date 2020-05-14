package com.example.moviestars.Model;


public class MyPojo {
    private String per_page;

    private String total;

    private Ad ad;

    private Data[] data;

    private String page;

    private String total_pages;

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public String toString() {
        return "ClassPojo [per_page = " + per_page + ", total = " + total + ", ad = " + ad + ", data = " + data + ", page = " + page + ", total_pages = " + total_pages + "]";
    }
}

