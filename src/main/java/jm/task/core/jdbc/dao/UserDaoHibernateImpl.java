package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
    private Session session;
    private Transaction transaction;
    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT not null auto_increment, " +
                "name VARCHAR (30) not null, " +
                "lastName VARCHAR (30) not null, " +
                "age TINYINT not null, " +
                "PRIMARY KEY (id))";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
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

        String sql = "SELECT id, name, lastName, age FROM users";
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        List<User> userList = query.list();
        transaction.commit();
        session.close();
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
