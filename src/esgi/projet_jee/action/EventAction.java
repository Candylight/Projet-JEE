package esgi.projet_jee.action;

import java.util.List;


import com.opensymphony.xwork2.ActionSupport;
import esgi.projet_jee.model.User;

public class EventAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private List<User> users;
    private Boolean employee;
    private String username;
    private String userid;
    private String planner;
    private String events;

    public List<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }
    public String getUserid() {
        return userid;
    }
    public String getPlanner() {
        return planner;
    }
    public String getEvents() {
        return events;
    }
    public String javaplanner() throws Exception {
        return SUCCESS;
    }
    public String events() throws Exception {
        return SUCCESS;
    }
    public String login() throws Exception {
        return SUCCESS;
    }
    public String logout() throws Exception {
        return SUCCESS;
    }
}