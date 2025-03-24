package com.routine;

public class RoutineModel {
    private String info;
    private String subject;
    private String time;

    public RoutineModel() {}

    public RoutineModel(String info, String subject, String time) {
        this.info = info;
        this.subject = subject;
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
