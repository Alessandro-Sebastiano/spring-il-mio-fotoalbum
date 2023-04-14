package org.project.java.springilmiofotoalbum.model;

public class AlertMessages {

    typeAlert typeAlertMessage;
    private String textMessage;
    public AlertMessages(typeAlert typeAlertMessage, String textMessage) {
        this.typeAlertMessage = typeAlertMessage;
        this.textMessage = textMessage;
    }

    public AlertMessages() {

    }

    public typeAlert getTypeAlertMessage() {
        return typeAlertMessage;
    }

    public void setTypeAlertMessage(typeAlert typeAlertMessage) {
        this.typeAlertMessage = typeAlertMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public enum typeAlert {
        SUCCESS,
        ERROR;
    }

}
