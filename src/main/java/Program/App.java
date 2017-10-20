package Program;

import Settings.BankJSON;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Path("/bank")
public class App {


        String tmp = "{\"ssn\":1605789787,\"creditScore\":598,\"loanAmount\":1000.0,\"loanDuration\":360}";

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return "<h1> WELCOME TO THE JSON BANK </h1>\n " +
                "<p>To use this bank application, you have to send a post request to:</p> \n" +
                "<a> http://94.130.57.246:9000/bankjson/bank/interestrate </a>";
    }

    @POST
    @Path("/interestrate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBankDetails(InputStream json) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(json));
        StringBuilder out = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(out.toString());
        int creditScore = jsonObject.getInt("creditScore");
        double loanAmount = jsonObject.getDouble("loanAmount");
        double years = jsonObject.getDouble("loanDuration");
        BankJSON bankJSON = new BankJSON();
        List<Object> temp = bankJSON.returnJSONBank(creditScore, loanAmount ,years);

        JsonObject value = Json.createObjectBuilder()
            .add("loanResponse", Json.createObjectBuilder()
                    .add("bankName", temp.get(0).toString())
                    .add("interestRate", temp.get(1).toString())
                    .add("refund", temp.get(2).toString()))
            .build();

        return Response.ok().entity(value.toString()).build();
    }

}
