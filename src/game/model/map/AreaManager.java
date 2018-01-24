package game.model.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.model.entity.WorldObject;
import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;
import json.parser.ParseException;
import server.Config;

public class AreaManager {

	public static List<Area> loadAreas() {
		//make a new hashmap to store definitions in
		List<Area> areas = new ArrayList<Area>();
			
		//Create json objects
	    JSONParser parser = new JSONParser();
		JSONArray a = null;
		InputStream in; 
		BufferedReader reader;
		
		try {
			if (!Config.developerMode) {
				in = AreaManager.class.getResourceAsStream("/data/areas.json");
				reader = new BufferedReader(new InputStreamReader(in));
				a = (JSONArray) parser.parse(reader);
			} else {
				a = (JSONArray) parser.parse(new FileReader("data/areas.json"));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
			
		//loop through all areas
			for (Object o : a)
			{
				JSONObject area = (JSONObject) o;
				String name = (String) area.get("name");
				int width = Math.toIntExact((Long) area.get("width"));
				int height = Math.toIntExact((Long) area.get("height"));
				String rawTiles = (String) area.get("tiles");
			    List<Tile> tiles = new ArrayList<Tile>();
			    JSONArray rawObjects = (JSONArray) area.get("objects");
			    List<WorldObject> objects = new ArrayList<WorldObject>();
			    
			    /*parse tiles*/
				String[] tilesArray = rawTiles.split("#");
				for(String tile :  Arrays.copyOfRange(tilesArray, 1 , tilesArray.length)) {
					tiles.add(
							new Tile(
									TileType.getType(
											Integer.parseInt(
													tile.trim()
											)
									))
							);
				}
			    
			    /*parse objects*/
			    for (Object c : rawObjects)
			    {
					JSONObject object = (JSONObject) c;
					objects.add(
							new WorldObject(
									objects.size(),
									ObjectType.getType
									(
											Math.toIntExact((long) object.get("t"))
									),
									(long) object.get("x"),
									(long) object.get("y")
									)
							);
			    }
				areas.add(
						  new Area(areas.size(), name, width, height, tiles, objects)
						  );
			  }
		return areas;
	}

}
