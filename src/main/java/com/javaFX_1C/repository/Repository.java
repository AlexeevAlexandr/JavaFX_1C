package com.javaFX_1C.repository;

import com.javaFX_1C.model.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Repository {

    private Transaction transaction = null;

    public void save(Client client){
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    public List getClients(){
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            return session.createQuery("FROM Client").list();

        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public boolean updateClient(int id, String name, String address, String phone){
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Client SET name = :name, address = :address, phone = :phone WHERE id = :id");
            query.setParameter("id", id);
            query.setParameter("name", name);
            query.setParameter("address", address);
            query.setParameter("phone", phone);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return false;
    }

    public boolean deleteClient(int id){
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE Client WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return false;
    }
}