package com.example.dinger;

public class PetImage {
    private String Images;
    private String Year, Month, Dy, Hour, Minute, Sec;
    public PetImage(){

    }

    public PetImage(String Images, String Yr, String Mon, String D, String Hr, String Min, String sec){
        this.Images=Images;
        Dy=D;
        Hour=Hr;
        Year=Yr;
        Month=Mon;
        Minute=Min;
        Sec=sec;

    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getDy() {
        return Dy;
    }

    public void setDy(String dy) {
        Dy = dy;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String minute) {
        Minute = minute;
    }

    public String getSec() {
        return Sec;
    }

    public void setSec(String sec) {
        Sec = sec;
    }
}
