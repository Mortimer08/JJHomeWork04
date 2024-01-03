package org.example.models.Task01;

import org.example.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "secret";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            createDatabase(connection);
            useDataBase(connection);
            clearData(connection);
            createTable(connection);
            int count = rnd.nextInt(4, 7);
            for (int i = 0; i < count; i++) {
                insertData(connection, Course.create());
            }
            Collection<Course> courses = readData(connection);
            for (Course course : courses
            ) {
                System.out.println(course);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection connection) throws SQLException {
        String createDataBaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDataBaseSQL)) {
            statement.execute();
        }
    }

    private static void useDataBase(Connection connection) throws SQLException {
        String useDataBaseSQL = "USE schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDataBaseSQL)) {
            statement.execute();
        }
    }
private static void clearData(Connection connection) throws SQLException {
        String removeTableSQL = "DROP TABLE IF EXISTS courses;";
        try (PreparedStatement statement = connection.prepareStatement(removeTableSQL)){
            statement.execute();
        }
}
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses" +
                                "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, title VARCHAR(200), duration INT NULL);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    private static void insertData(Connection connection, Course course) throws SQLException {
        String insertDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, String.valueOf(course.getDuration()));
            statement.execute();
        }
    }

    private static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        String readDBData = "SELECT * FROM courses;";
        try (PreparedStatement statement = connection.prepareStatement(readDBData)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                courses.add(new Course(id, title, duration));
            }
            return courses;
        }
    }
}