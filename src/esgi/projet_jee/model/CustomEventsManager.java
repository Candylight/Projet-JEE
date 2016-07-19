package esgi.projet_jee.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import esgi.projet_jee.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXCollection;

public class CustomEventsManager extends DHXEventsManager {

    public CustomEventsManager(HttpServletRequest request) {
        super(request);
    }

    public Iterable<DHXEv> getEvents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DHXEv> evs = new ArrayList<DHXEv>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            evs = session.createCriteria(Event.class).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }

        return evs;
    }

    @Override
    public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
        HttpSession s = getRequest().getSession(true);

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if(status == DHXStatus.UPDATE){
                session.update(event);
            }else if (status == DHXStatus.DELETE){
                session.delete(event);
            }else if (status == DHXStatus.INSERT){
                session.save(event);
            }

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }
        return status;
    }

    @Override
    public DHXEv createEvent(String id, DHXStatus status) {
        return new Event();
    }

    @Override
    public HashMap<String, Iterable<DHXCollection>> getCollections() {
        List<User> users = getUsers();
        ArrayList<DHXCollection> users_list = new ArrayList<DHXCollection>();
        for (int i = 0; i < users.size(); i++) {
            users_list.add(new DHXCollection(users.get(i).getId().toString(), users.get(i).getName()));
        }

        HashMap<String,Iterable<DHXCollection>> c = new HashMap<String,Iterable<DHXCollection>>();
        c.put("users", users_list);

        return c;
    }

    public List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).add(Restrictions.isNotNull("id")).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }
        return users;
    }

    public User getUser(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).add(Restrictions.eq("id", Integer.parseInt(id))).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally{
            session.flush();
            session.close();
        }
        if (users.size() > 0)
            return users.get(0);
        else
            return null;
    }
}
