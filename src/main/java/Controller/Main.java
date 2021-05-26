package Controller;

import Models.UpperBodyModel;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        get("/upperBody/exercises", "application/json",  (req, res) -> UpperBodyModel.getUpperBodyExercises());

        post("/upperBody", "application/json",(req, res) -> UpperBodyModel.createExercise(req.queryParams("exerciseName"), req.queryParams("weight"), req.queryParams("date")));

        delete("/upperBody/exercise/:id", "application/json",(req, res) -> UpperBodyModel.deleteExercise(req.params("id")));

        put("/upperBody/exercise/:id", "application/json",(req, res) -> UpperBodyModel.updateExercise(req.params("id"), req.queryParams("exerciseName"), req.queryParams("weight"), req.queryParams("date")));
    }
}
