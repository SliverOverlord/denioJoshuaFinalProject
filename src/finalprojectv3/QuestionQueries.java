/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectv3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 *
 * @author Turtle
 */
public class QuestionQueries {
    @FXML
    
    private static final String URL = "jdbc:mysql://puff:3306/deniojo_Java";
    private static final String USERNAME = "deniojo";
    private static final String PASSWORD = "sLiver$betta1987";
    
    private Connection connection;
    private PreparedStatement select10Questions;
    private PreparedStatement selectAllQuestions;
    
    public ObservableList<Question> QResults = FXCollections.observableArrayList();
    
    public QuestionQueries()
    {
        try{
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        selectAllQuestions = connection.prepareStatement(
                "SELECT * FROM deniojo_Questions ");
        select10Questions = connection.prepareStatement(
                "INSERT INTO deniojo_Questions "+"(OrdID, custID, firstName, lastName, phone) "+" VALUES (?,?,?,?,?");
       
        }
        catch (SQLException sqlException){
                sqlException.printStackTrace();
                System.exit(1);
            }
        }
    public ObservableList<Question> getAllQuestions(){
        
        try (ResultSet QResultSet = selectAllQuestions.executeQuery()){
            ObservableList<Question> QResults = FXCollections.observableArrayList();
            
            while (QResultSet.next()){
                QResults.add(new Question(
                    QResultSet.getInt("keyID"),
                    QResultSet.getInt("typeID"),
                    QResultSet.getString("question"),
                    QResultSet.getString("option1"),
                    QResultSet.getString("option2"),
                    QResultSet.getString("option3"),
                    QResultSet.getString("option4"),
                    QResultSet.getString("solution")
                    
                    ));
                
                        
            }
            return QResults;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println(selectAllQuestions);
        return null;
    }
    public void close(){
        try{
            connection.close();
            
        }
        catch (SQLException sqlException){
                sqlException.printStackTrace();
        }
    }
}

