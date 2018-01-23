package game.model.map;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;
import json.parser.ParseException;

public class ObjectManager {
	
	public static HashMap<Integer, ObjectDefinition> definitions = loadDefinitions();
	
	private static HashMap<Integer, ObjectDefinition> loadDefinitions() {
		//make a new hashmap to store definitions in
		HashMap<Integer, ObjectDefinition> defs = new HashMap<Integer, ObjectDefinition>();
		
		//Create json objects
        JSONParser parser = new JSONParser();
		JSONArray a = null;
		try {
			a = (JSONArray) parser.parse(new FileReader("data/object_definitions.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		//loop through all definitions
		  for (Object o : a)
		  {
			  JSONObject definition = (JSONObject) o;
			  int defType = Math.toIntExact((Long) definition.get("type"));
			  defs.put(defType, new ObjectDefinition(
					  (String) definition.get("name"), //name
					  Math.toIntExact((Long) definition.get("size")) // size
					  ));
		  }
		  
		return defs;
	}
	
	public static ObjectDefinition getDefinition(int type) {
		return definitions.get(type);
	}
	
}
