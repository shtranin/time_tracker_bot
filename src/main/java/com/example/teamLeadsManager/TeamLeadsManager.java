package com.example.teamLeadsManager;

import com.example.teamLeadsManager.model.TeamLead;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TeamLeadsManager {
    private final SessionFactory sessionFactory;

    public TeamLeadsManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isUserTeamLead(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(TeamLead.class, userId) != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean leadHasGroup(Long id) {
            try (Session session = sessionFactory.openSession()) {
                return session.load(TeamLead.class, id).hasGroup();
            }
        }


    public void save(TeamLead entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteById(Long id) {
        if (id == null)
            return;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int updated = session.createQuery("delete TeamLead g where g.telegramId = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            if (updated != 1) {
                throw new IllegalArgumentException();
            }
            transaction.commit();
        } catch (IllegalArgumentException e) {
            System.err.println("Entity is not present in the table");
            throw new RuntimeException();
        }
    }
    public List<TeamLead> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select g from TeamLead g", TeamLead.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public void setHasGroup(Long id,boolean result){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("update TeamLead set hasGroup = :result where telegramId = :id")
                    .setParameter("id",id)
                    .setParameter("result",result)
                    .executeUpdate();
            transaction.commit();
        }
    }

}
