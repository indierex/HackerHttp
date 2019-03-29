import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class WebRequest {
    /*
     * Complete the function below.
     */
    static int getNumberOfMovies(String substr) throws Exception {
        /*
         * Endpoint: "https://jsonmock.hackerrank.com/api/movies/search/?Title=substr"
         */


        int total=0;

        try {

            URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search?Title="+substr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                String JSONResponse = response.toString();

                Gson gson = new Gson();

                IMDB imdb = gson.fromJson(JSONResponse, IMDB.class);

                total = imdb.getTotal();


            } else {
                System.out.println("GET request not worked");
            }


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return total;

    }

    public static void main(String[] args) throws IOException {
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
        // BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res = 0;
//        String _substr;
//        try {
//            _substr = in.nextLine();
//        } catch (Exception e) {
//            _substr = null;
//        }
        String _substr = "maze";
        try {
            res = getNumberOfMovies(_substr);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        System.out.println(res);
        //bw.write(String.valueOf(res));
//        bw.newLine();
//
//        bw.close();
    }
}
