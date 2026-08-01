import java.sql.*;

class UResultSet {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "ROOT";
        String password = "Manoj";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create updatable and scrollable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Execute query
            ResultSet rs = st.executeQuery("SELECT * FROM Student");

            // Delete the last row
            if (rs.last()) {
                rs.deleteRow();
                System.out.println("Last student record deleted successfully.");
            }

            // Insert a new row
            rs.moveToInsertRow();
            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");
            rs.insertRow();

            System.out.println("New student record inserted successfully.");

            // Close resources
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
