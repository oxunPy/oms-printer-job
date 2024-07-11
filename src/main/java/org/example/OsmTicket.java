package org.example;

import java.io.InputStream;

public class OsmTicket {
        private InputStream kmCode;
        private InputStream abIcon;
        private String goodName;
        private String country;
        private String km;

    public OsmTicket() {
    }

    public InputStream getKmCode() {
        return kmCode;
    }

    public void setKmCode(InputStream kmCode) {
        this.kmCode = kmCode;
    }

    public InputStream getAbIcon() {
        return abIcon;
    }

    public void setAbIcon(InputStream abIcon) {
        this.abIcon = abIcon;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
}
