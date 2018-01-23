package game.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.model.entity.WorldObject;

public class Area {
	
	private String name;
	private int width;
	private int height;
	private Tile[][] tiles;
	private List<WorldObject> objects;
	
	public Area(int id, String name, int width, int height, List<Tile> tiles, List<WorldObject> objects) {
		 this.name = name;
		 this.width = width;
		 this.height = height;
		 this.tiles = new Tile[width][height];
		 this.objects = objects;
		 init(width, height, tiles);
	}
	
	public void init(int width, int height, List<Tile> tileList) {
		int i = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = tileList.get(i);
				i++;
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public List<WorldObject> getObjects() {
		return objects;
	}
	
	public void addWorldObject(ObjectType type, int x, int y) {
		new WorldObject(
				objects.size(),
				type,
				x,
				y		
		);
	}
	
	public void removeWorldObject(int id) {
		for(WorldObject o : objects) {
			if (o.getId() == id) 
				objects.remove(o);
		}
	}

	public void tick() {
		
	}

}
