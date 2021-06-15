package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
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
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "DELETE FROM USERS WHERE ID = :id AND  LASTNAME = :lastname AND  AGE = :age";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter("name",name);
        query.setParameter("lastname",lastName);
        .setParameter("age",age);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "DELETE FROM USERS WHERE ID = :id";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        List<User> userList = query.list();
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
