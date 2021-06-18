package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    private Session session;
    private Transaction transaction;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT not null auto_increment, " +
                    "name VARCHAR (30) not null, " +
                    "lastName VARCHAR (30) not null, " +
                    "age TINYINT not null, " +
                    "PRIMARY KEY (id))";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no createUsersTable");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no dropUsersTable");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no saveUser");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String sql = "DELETE FROM USERS WHERE ID = :id";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no removeUserById");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            userList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no getAllUsers");
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE users";

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("no getAllUsers");
            throw new RuntimeException(e);
        }
    }
}
