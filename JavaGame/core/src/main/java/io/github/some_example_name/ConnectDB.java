package io.github.some_example_name;

import java.sql.*;

public class ConnectDB {

    private static final String url = "jdbc:mysql://localhost:3306/zombieshooter";
    private static final String user = "root";
    private static final String passwd = "";

    public static String name = "";

    public static Connection connectToDB() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connection Reussie");
        return connection;
    }


    public static boolean checkUserInDatabase(String nom, String password) {

        try (Connection connection = ConnectDB.connectToDB()) {
            String sql = "SELECT id, username FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    public static void addUserToDatabase(String nom, String password ) {
        try (Connection conn = ConnectDB.connectToDB()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password , score)  VALUES (?,?,?)");

            pstmt.setString(1, nom);
            pstmt.setString(2, password);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserScoreInDatabase(int newScore) {
        String sql = "UPDATE users set score = ? where username = ?";
        try (Connection conn = ConnectDB.connectToDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newScore);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
