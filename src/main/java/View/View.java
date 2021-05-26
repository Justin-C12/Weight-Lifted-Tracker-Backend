package View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class View {

    public View(){

    }

    public JsonArray formatToJson(ResultSet rs) throws SQLException {
        JsonArray jsonArray = new JsonArray();

        while(rs.next()) {
            JsonObject jsonObject = new JsonObject();
            int id = rs.getInt("id");
            String exerciseName = rs.getString("exercise_name");
            String weight = rs.getString("weightLifted");
            String date = rs.getString("dateLifted");

            jsonObject.addProperty("id", id);
            jsonObject.addProperty("Name", exerciseName);
            jsonObject.addProperty("Weight", weight);
            jsonObject.addProperty("Date", date);

            jsonArray.add(jsonObject);
        }


        return jsonArray;
    }


    public String createJsonResponse(String id, String exerciseName, String weight, String date){
        return "{" + "\"id" + "\":" + id + ", " + "\"exercise name" + "\":" + "\"" + exerciseName + "\"" + ", " + "\"weight" + "\":" + "\"" + weight + "\"" +
                ", " + "\"date" + "\":" + "\"" + date + "\"" + "}";
    }


}
