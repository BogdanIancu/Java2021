package ro.ase.acs.json;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Location: ");
			String location = scanner.nextLine();
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" +
					location + "&appid=7b10426ee90376dc3d6525f847128b35&units=metric&lang=en");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader buffer = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject jsonObject = new JSONObject(sb.toString());
			JSONObject mainObject = jsonObject.getJSONObject("main");
			float temperature = mainObject.getFloat("temp");
			System.out.println("Temperature: " + temperature);
			
			JSONArray weatherArray = jsonObject.getJSONArray("weather");
			JSONObject weatherObject = weatherArray.getJSONObject(0);
			String description = weatherObject.getString("description");
			System.out.println("Description: " + description);
			
			JSONObject weatherResponse = new JSONObject();
			weatherResponse.put("location", location);
			weatherResponse.put("temperature", temperature);
			weatherResponse.put("conditions", description);
			
			JSONObject response = new JSONObject();
			response.put("timestamp", new Date().getTime());
			response.put("weather", weatherResponse);
			
			FileWriter fileWriter = new FileWriter("response.json");
			fileWriter.write(response.toString());
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
