package game.model.entity;

import game.model.map.ObjectDefinition;
import game.model.map.ObjectManager;
import game.model.map.ObjectType;

public class WorldObject extends Entity {
	
	private int id; // object indentifier
	private ObjectType type; // what definition to load
	private ObjectDefinition definition;
	
	public WorldObject(int id, ObjectType type, double x, double y) {
		this.id = id;
		this.type = type;
		this.definition = ObjectManager.getDefinition(type.getId());
		this.x = x;
		this.y = y;
	}
	
	public ObjectDefinition getDefinition() {
		return definition;
	}

	public int getId() {
		return id;
	}

	public ObjectType getType() {
		return type;
	}

}
