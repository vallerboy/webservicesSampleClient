import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        // Uzyskanie listy userow
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://localhost:8080/api/user").openConnection();
            urlConnection.addRequestProperty("App-Password", "tajnehaslo");
            InputStream stream = urlConnection.getInputStream();
            int bajt = 0;
            while ((bajt = stream.read()) != -1){
                System.out.print((char)bajt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Zapisanie usera do bazy
        String myJsonUser = "{\"nick\":\"oskar3\",\"password\":\"asd\",\"email\":\"test\"}";
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://localhost:8080/api/user").openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.getOutputStream().write(myJsonUser.getBytes());
            urlConnection.connect();
            System.out.println("Komunikat: " + urlConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
