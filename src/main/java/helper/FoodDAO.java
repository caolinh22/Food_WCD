package helper;

import entity.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDAO {
    private static final String DATABASE_URL_FORMAT = "jdbc:mysql://%s:%d/%s?useUnicode=true&contentEncoding=UTF-8";
    private static final String DATABASE_SERVER = "localhost";
    private static final int DATABASE_PORT = 8000;
    private static final String DATABASE_NAME = "food-asm-wcd";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PWD = "";
    private static Connection jdbcConnection;

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            if (jdbcConnection == null || jdbcConnection.isClosed()) {
                jdbcConnection = DriverManager.getConnection(
                        String.format(DATABASE_URL_FORMAT, DATABASE_SERVER, DATABASE_PORT, DATABASE_NAME),
                        DATABASE_USER,
                        DATABASE_PWD);
                System.out.println("Connect success!");
            }
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }
        return jdbcConnection;
    }

    public static void insertFood(Food food) {
        try {
            String sql = "INSERT INTO foods VALUES (?,?,?,?,?,?,?,?,?)";
            Connection connection = getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, food.getId());
            statement.setString(2, food.getFullName());
            statement.setString(3, food.getCategoryId());
            statement.setString(4, food.getDescription());
            statement.setString(5, food.getImage());
            statement.setString(6, food.getPrice());
            statement.setString(7, String.valueOf(food.getCreatedAt()));
            statement.setString(8, String.valueOf(food.getUpdatedAt()));
            statement.setString(9, food.getStatus());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Food> listAllFoods() throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            return null;
        }
        List<Food> listFood = new ArrayList<>();

        String sql = "SELECT * FROM foods";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String fullName = resultSet.getString("fullName");
            String categoryId = resultSet.getString("categoryId");
            String description = resultSet.getString("description");
            String image = resultSet.getString("image");
            String price = resultSet.getString("price");
            Date createdAt = resultSet.getDate("createdAt");
            Date updatedAt = resultSet.getDate("updatedAt");
            String status = resultSet.getString("status");

            Food food = new Food(id, fullName, categoryId, description, image, price, createdAt, updatedAt, status);
            listFood.add(food);
        }
        return listFood;
    }

    public static boolean deleteFood(Food food) throws SQLException {
        Connection connection = getConnection();
        String sql = "DELETE FROM foods where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, food.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    public static void updateFood(Food food) throws SQLException {
        Connection connection = getConnection();
        String sql = "UPDATE foods SET fullName = ?, categoryId = ?, description = ?, image = ?, price = ?, createdAt = ?, updatedAt = ?, status = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, food.getFullName());
        statement.setString(2, food.getCategoryId());
        statement.setString(3, food.getDescription());
        statement.setString(4, food.getImage());
        statement.setString(5, food.getPrice());
        statement.setDate(6, (java.sql.Date) food.getCreatedAt());
        statement.setDate(7, (java.sql.Date) food.getUpdatedAt());
        statement.setString(8, food.getStatus());
        statement.setString(9, food.getId());

        statement.executeUpdate();
    }

    public static Food getFood(String id) throws SQLException {
        Connection connection = getConnection();
        Food food = null;
        String sql = "SELECT * FROM foods WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String fullName = resultSet.getString("fullName");
            String categoryId = resultSet.getString("categoryId");
            String description = resultSet.getString("description");
            String image = resultSet.getString("image");
            String price = resultSet.getString("price");
            Date createdAt = resultSet.getDate("createdAt");
            Date updatedAt = resultSet.getDate("updatedAt");
            String status = resultSet.getString("status");

            food = new Food(id, fullName, categoryId, description, image, price, createdAt, updatedAt, status);
        }
        return food;
    }

}
