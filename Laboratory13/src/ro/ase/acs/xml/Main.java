package ro.ase.acs.xml;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ro.ase.acs.models.WeatherConditions;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Location: ");
			String location = scanner.nextLine();
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" +
					location + "&appid=7b10426ee90376dc3d6525f847128b35&units=metric&lang=en&mode=xml");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			InputStream inputStream = connection.getInputStream();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xml = builder.parse(inputStream);
			
			NodeList nodeList = xml.getElementsByTagName("temperature");
			Node tempNode = nodeList.item(0);
			String temp = tempNode.getAttributes().getNamedItem("value").getNodeValue();
			System.out.println("Temperature: " + temp);
			
			nodeList = xml.getElementsByTagName("weather");
			Node weatherNode = nodeList.item(0);
			String desc = weatherNode.getAttributes().getNamedItem("value").getNodeValue();
			System.out.println("Description: " + desc);
			
			WeatherConditions conditions = new WeatherConditions();
			conditions.setLocation(location);
			conditions.setTemperature(Float.parseFloat(temp));
			conditions.setDescription(desc);
			conditions.setTimestamp(new Date());
			
			JAXBContext context = JAXBContext.newInstance(WeatherConditions.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(conditions, new File("response.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
