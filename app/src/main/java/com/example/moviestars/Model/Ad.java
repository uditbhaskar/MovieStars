package com.example.moviestars.Model;

public class Ad {

    private String company;

    private String text;

    private String url;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [company = " + company + ", text = " + text + ", url = " + url + "]";
    }
}
