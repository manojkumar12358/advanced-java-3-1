import java.sql.*;
public class Demo{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "ROOT";
        String password = "Manoj";
        try (Connection conn =
                DriverManager.getConnection(url, user, password)) {
            System.out.println("Database Connected Successfully.");
            CallableStatement insertStmt =
                    conn.prepareCall("{call insert_employee(?, ?, ?)}");
            insertStmt.setInt(1, 101);
            insertStmt.setString(2, "John Doe");
            insertStmt.setDouble(3, 55000);
            insertStmt.execute();
            System.out.println("Employee inserted successfully.");
            CallableStatement salaryStmt =
                    conn.prepareCall("{call get_salary_by_id(?, ?)}");
            salaryStmt.setInt(1, 101);
            salaryStmt.registerOutParameter(2, Types.DECIMAL);
            salaryStmt.execute();
            double salary = salaryStmt.getDouble(2);
            System.out.println("Salary of Employee 101 = " + salary);
            insertStmt.close();
            salaryStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
