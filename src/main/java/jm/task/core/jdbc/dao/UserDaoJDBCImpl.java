package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("no add User");
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> userList= new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

    }
}
