package jm.task.core.jdbc.service;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {

    private Connection connection = getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT not null auto_increment, " +
                "name VARCHAR (30) not null, " +
                "lastname VARCHAR (30) not null, " +
                "age TINYINT not null, " +
                "PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no createUsersTable");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no dropUsersTable");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no saveUser");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS (ID) VALUE (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no removeUserById");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no getAllUsers");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USERS";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no cleanUsersTable");
        }
    }
}
