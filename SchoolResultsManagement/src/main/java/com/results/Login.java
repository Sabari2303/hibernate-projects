package com.results;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("type");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       

        if (userType.equals("admin") && username.equals("admin") && password.equals("admin123")) {
            response.sendRedirect("admin.html");
        } else if (userType.equals("student")) {
            String jdbcURL = "jdbc:mysql://localhost:3306/school";
            String dbUser = "root";
            String dbPassword = "03Sabari";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "SELECT * FROM marks WHERE student_name = ? AND student_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    request.setAttribute("studentName", resultSet.getString("student_name"));
                    request.setAttribute("studentId", resultSet.getString("student_id"));
                    request.setAttribute("Tamil", resultSet.getInt("subject1"));
                    request.setAttribute("English", resultSet.getInt("subject2"));
                    request.setAttribute("Mathematics", resultSet.getInt("subject3"));
                    request.setAttribute("Science", resultSet.getInt("subject4"));
                    request.setAttribute("Social", resultSet.getInt("subject5"));
                    RequestDispatcher rd=request.getRequestDispatcher("result.jsp");
            	    rd.forward(request, response);
                 }else {
                    response.sendRedirect("login.html?type=student&error=true");
                }

                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("login.html?type=student&error=true");
            }
        } else {
            response.sendRedirect("login.html?type=" + userType + "&error=true");
        }
    }
}