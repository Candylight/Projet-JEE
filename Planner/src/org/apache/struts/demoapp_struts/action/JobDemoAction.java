package org.apache.struts.demoapp_struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.demoapp_struts.model.CustomEventsManager;
import org.apache.struts.demoapp_struts.model.RegisterDao;
import org.apache.struts.demoapp_struts.model.User;
import org.apache.struts.demoapp_struts.util.HibernateUtil;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXLightboxSelect;
import com.dhtmlx.planner.controls.DHXMiniCalendar;
import com.dhtmlx.planner.controls.DHXUnitsView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;
import com.opensymphony.xwork2.ActionSupport;

public class JobDemoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private List<User> users;
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
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("email") == null) {
            return ERROR;
        }

        DHXPlanner planner = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
        planner.setInitialDate(2013, 7, 15);
        planner.setInitialView("units");
        planner.config.setScrollHour(8);
        planner.setWidth(844);
        planner.setHeight(518);
        planner.load("events", DHXDataFormat.JSON);
        planner.data.dataprocessor.setURL("events");
        planner.config.setDetailsOnCreate(true);
        planner.config.setFirstHour(10);
        planner.config.setLastHour(19);
        planner.extensions.add(DHXExtension.TOOLTIP);

        // create units view
        DHXUnitsView units = new DHXUnitsView("units", "user", "Units");
        units.setServerListLink("users");
        units.setSkipIncorrect(true);
        planner.views.add(units);


        // adds sections in lightbox
        DHXLightboxSelect sel = new DHXLightboxSelect("user", "Owner");
        sel.setServerList("users");
        planner.lightbox.add(sel);


        // creates mini-calendar
        DHXMiniCalendar cal = new DHXMiniCalendar("minical");
        cal.setNavigation(true);
        planner.calendars.add(cal);

        users = (new CustomEventsManager(request)).getUsers();
        username = session.getAttribute("name").toString();
        userid = session.getAttribute("id").toString();
        this.planner = planner.render();
        return SUCCESS;
    }

    public String events() throws Exception {
        CustomEventsManager evs = new CustomEventsManager(ServletActionContext.getRequest());
        events = evs.run();
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