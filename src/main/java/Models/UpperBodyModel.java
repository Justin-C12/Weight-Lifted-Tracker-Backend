package Models;

import View.View;
import com.google.gson.JsonArray;
import spark.Response;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UpperBodyModel {
    private static AtomicInteger id = new AtomicInteger();


    private static View view = new View();


    //POST
    public static String createExercise(String exerciseName, String weight, String date) throws ClassNotFoundException, SQLException {
        //Params for DB connection

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/workout_app";
        String userName = "root";
        String password = "Tobey8599";

        String upperBodyQuery = "INSERT INTO upper_body (id, exercise_name, weightLifted, dateLifted) " +
                "VALUES (" + "'" + id.getAndIncrement() + "'" + ", " + "'" + exerciseName + "'" + ", " + "'" + weight + "'" + ", " + "'" + date + "'" + ");";

        //Create DB connection
        Connection conn = DriverManager.getConnection(url, userName, password);

        Statement st = conn.createStatement();

        //Inserts data into upper_body table
        st.executeUpdate(upperBodyQuery);
        String json = view.createJsonResponse(id.toString(), exerciseName, weight, date);


        return json;
    }

        //GET
    public static JsonArray getUpperBodyExercises() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/workout_app";
        String userName = "root";
        String password = "Tobey8599";
        String query = "SELECT * FROM upper_body;";

        //Create DB connection
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement st = conn.createStatement();

        //Execute query and format in json
        ResultSet resultSet = st.executeQuery(query);
        JsonArray json = view.formatToJson(resultSet);

        return json;
    }

    //DELETE
    public static String deleteExercise(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/workout_app";
        String userName = "root";
        String password = "Tobey8599";
        String query = "DELETE FROM upper_body WHERE id = " + "'" + id + "';";

        //Create DB connection
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement st = conn.createStatement();

        st.executeUpdate(query);

        return "{ \"code" + ":" + "200}";
    }

    //PUT
    public static String updateExercise(String id, String exerciseName, String weight, String dateLifted) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/workout_app";
        String userName = "root";
        String password = "Tobey8599";
        String query = "UPDATE upper_body SET " + "exercise_name" + " =  " + "'" + exerciseName + "'" + ", " +
                "weightLifted = " + "'" + weight + "'" + ", " +
                "dateLifted = " + "'" + dateLifted + "'" + " WHERE id = " +  id + ";";

        //Create DB connection
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement st = conn.createStatement();

        st.executeUpdate(query);
        String json = view.createJsonResponse(id, exerciseName, weight, dateLifted);

        return json;
    }
}
