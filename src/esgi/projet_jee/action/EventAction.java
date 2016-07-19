package esgi.projet_jee.action;

import java.util.List;


import com.opensymphony.xwork2.ActionSupport;
import esgi.projet_jee.model.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import esgi.projet_jee.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("email") != null)
            return LOGIN;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || password == null)
            return SUCCESS;

        Session s = HibernateUtil.getSessionFactory().openSession();
        List<User> users = s.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .list();

        if (users.size() == 0)
            return ERROR;

        session.setAttribute("id", users.get(0).getId());
        session.setAttribute("email", users.get(0).getEmail());
        session.setAttribute("name", users.get(0).getName());
        return LOGIN;
    }
    public String logout() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("name");
        return SUCCESS;
    }
}