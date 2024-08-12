import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    // Credentials
    private static final String url = "jdbc:mysql://127.0.0.1:3306/banking_system";// Enter your database link
    private static final String username = "root";// Enter your mysql username
    private static final String password = "";// Enter your mysql password

    static Connection conn = null ;
    // Function which will return connection with the databse

    public static Connection connect() {
        try {
            // Load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            // Obtain a connection
            conn = DriverManager.getConnection(url , username , password);

        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return conn;

    }
}
