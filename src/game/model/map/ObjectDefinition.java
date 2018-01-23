package game.model.map;

public class ObjectDefinition {
	
	private String name;
	private int size;

	public ObjectDefinition(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
		
	public int getSize() {
		return size;
	}
}
