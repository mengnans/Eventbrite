package com.eventbrite.domain;
public class Certificate {
    private int id;
    private String eventId;
    private String xml;

    public Certificate(int id, String eventId, String xml) {
        this.id = id;
        this.eventId = eventId;
        this.xml = xml;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", eventId='" + eventId + '\'' +
                ", xml='" + xml + '\'' +
                '}';
    }
}
