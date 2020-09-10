package com.model;

public class AuditModel {
    Integer id;
    String uri;
    String timeStamp;

    public AuditModel() {
    }

    public AuditModel(int id, String uri, String timeStamp) {
        this.id = id;
        this.uri = uri;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
