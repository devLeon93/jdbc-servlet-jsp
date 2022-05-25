import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne) {
            cne.printStackTrace();
        }

        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee",
                    "testuser",
                    "emp1234");

            Statement stmt = conn.createStatement();
            String sqlQuery = "SELECT id,name,surname,email FROM employee ";
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while(rs.next()) {

                out.println(rs.getInt("id"));
                out.println(rs.getString("name"));
                out.println(rs.getString("surname"));
                out.println(rs.getString("email"));

            }

        }catch (SQLException se){
            se.printStackTrace();
        }


    }

}
