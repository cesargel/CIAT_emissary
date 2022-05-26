package com.cibergenius.emissary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Tags")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(name = "epc")
    private String EPC;
    @Column(name = "location")
    private String location;
    @Column(name = "logical_devices")
    private String logicalDevices;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "create_date")
    private Date createDate;
    @Column(nullable = true, name = "publish_date")
    private Date publishDate;
    @Column(nullable = true, name = "log")
    private String log;

    @PrePersist
    private void onCreate() {
        createDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEPC() {
        return EPC;
    }

    public void setEPC(String ePC) {
        EPC = ePC;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @JsonIgnore
    public String getLogicalDevices() {
        return logicalDevices;
    }

    public void setLogical_devices(String logical_Devices) {
        this.logicalDevices = logical_Devices;
    }
    
    public String getLog() {
        return log;
    }
        
    public void setLog(String log) {
        this.log = log;
    }

}
