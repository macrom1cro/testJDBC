package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Util util = new Util();

        {
            try (Statement statement = util.getConnection().createStatement()){
                /*
                statement.executeUpdate("create table Users2 (id BIGINT not null auto_increment, " +

                        "name VARCHAR (30) not null, " +
                        "lastname VARCHAR (30) not null, " +
                        "age TINYINT not null, " +
                        "PRIMARY KEY (id))");
*/
                statement.executeUpdate("insert into Users2 (name, lastname, age)" +
                        "VALUES ('Amega', 'Amegovna', '21')");

                ResultSet resultSet = statement.executeQuery("select * from Users");

                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setAge(resultSet.getByte("age"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
