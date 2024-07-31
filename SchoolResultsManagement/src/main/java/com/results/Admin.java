package com.results;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        int Tamil = Integer.parseInt(request.getParameter("Tamil"));
        int English = Integer.parseInt(request.getParameter("English"));
        int Mathematics = Integer.parseInt(request.getParameter("Mathematics"));
        int Science = Integer.parseInt(request.getParameter("Science"));
        int Social  = Integer.parseInt(request.getParameter("Social"));

        String jdbcURL = "jdbc:mysql://localhost:3306/school";
        String dbUser = "root";
        String dbPassword = "03Sabari";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "INSERT INTO marks (student_id, student_name, subject1, subject2, subject3, subject4, subject5) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setString(2, studentName);
            statement.setInt(3, Tamil);
            statement.setInt(4, English);
            statement.setInt(5, Mathematics);
            statement.setInt(6, Science);
            statement.setInt(7, Social);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("admin.html?success=true");
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("admin.html?error=true");
        }
    }
}
