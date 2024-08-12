import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;
    // Constructor
    public User(Connection connection , Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    // Register function
    public void register(){
        scanner.nextLine();
        System.out.println("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        if(user_exist(email)){
            System.out.println("User Already Exist for this Email Address!!");
            System.out.println();
            return;
        }
        String query = "insert into user(full_name , email , password) values" +
                "(? , ? , ?) ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Registration is successfull !!");
                System.out.println();
            }else {
                System.out.println("Registration Failed");
                System.out.println();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Login function
    public String login(){
        scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        String login_query = "select * from user where email= ? and password = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(login_query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    return null;
    }

    // User Exist function
    public boolean user_exist(String email){
        try {
            String query = "SELECT * FROM user where email = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
