package ro.ase.acs.nosql;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase mongoDb = mongoClient.getDatabase("test");
		
		if(mongoDb.getCollection("employees") != null) {
			mongoDb.getCollection("employees").drop();
		}
		
		mongoDb.createCollection("employees");
		
		Document employee1 = new Document().append("name", "Popescu Ion").
				append("address", "Bucharest").append("salary", 4000);
		
		MongoCollection<Document> collection = mongoDb.getCollection("employees");
		collection.insertOne(employee1);
		
		Document employee2 = new Document().append("name", "Ionescu Vasile").
				append("salary", 4500);
		collection.insertOne(employee2);
		
		FindIterable<Document> result = collection.find();
		for(Document doc : result) {
			System.out.println(doc);
		}
		
		mongoClient.close();
	}

}
