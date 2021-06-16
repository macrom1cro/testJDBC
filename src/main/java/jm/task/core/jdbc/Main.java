package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
/*        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov", (byte) 20);
        userService.saveUser("Sam","Qwerty", (byte) 25);
        userService.saveUser("Alex","Bot", (byte) 30);
        userService.saveUser("Tom","Cat", (byte) 35);
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }*/
        userService.cleanUsersTable();
        userService.dropUsersTable();
        System.out.println("GJ");
    }
}
