package controller;

import entity.Food;
import helper.FoodDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateFood extends HttpServlet {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Food existingFood = null;
            existingFood = FoodDAO.getFood(id);
            if (existingFood == null) {
                return;
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("updateFood.jsp");
            req.setAttribute("food", existingFood);
            dispatcher.forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String fullName = req.getParameter("fullName");
            String categoryId = req.getParameter("categoryId");
            String description = req.getParameter("description");
            String image = req.getParameter("image");
            String price = req.getParameter("price");
            String stringCreatedAt = req.getParameter("createdAt");
            Date createdAt = sdf.parse(stringCreatedAt);
            java.sql.Date dateCreatedAt = new java.sql.Date(createdAt.getTime());
            String stringUpdatedAt = req.getParameter("updatedAt");
            Date updatedAt = sdf.parse(stringUpdatedAt);
            java.sql.Date dateUpdatedAt = new java.sql.Date(updatedAt.getTime());
            String status = req.getParameter("status");

            Food food = new Food(id, fullName, categoryId, description, image, price, dateCreatedAt, dateUpdatedAt, status);
            FoodDAO.updateFood(food);
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/list");
    }
}
