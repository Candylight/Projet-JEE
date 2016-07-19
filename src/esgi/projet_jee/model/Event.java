package esgi.projet_jee.model;

import com.dhtmlx.planner.DHXEvent;

public class Event extends DHXEvent {

    public String user;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

}