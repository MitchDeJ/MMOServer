package game.model.map;

public class Tile {
	
	private TileType type;
	
	public Tile(TileType type) {
		this.setType(type);
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
}
