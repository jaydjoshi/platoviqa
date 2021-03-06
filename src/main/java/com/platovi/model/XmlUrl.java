package com.platovi.model;



import java.text.SimpleDateFormat;
import java.util.Date;

import  javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(value = XmlAccessType.NONE)
@XmlRootElement(name = "url")
public class XmlUrl {
    public enum Priority {
        HIGH("1.0"), MEDIUM("0.5");

        private String value;

        Priority(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @XmlElement
    private String loc;

    
    @XmlElement
    private String lastmod = formatter.format(new Date());

    @XmlElement
    private String changefreq;

    @XmlElement
    private String priority;

    public XmlUrl() {
    }

    public XmlUrl(String loc, Priority priority, String changefreq) {
        this.loc = loc;
        this.priority = priority.getValue();
        this.changefreq = changefreq;
    }

    public String getLoc() {
        return loc;
    }

    public String getPriority() {
        return priority;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public String getLastmod() {
        return lastmod;
    }
}