package game;

public enum TileType {
	
	GRASS(0),
	DIRT(1),
	STONE_PATH(2),
	PATH_EDGE_TOP(3),
	PATH_EDGE_BOTTOM(4),
	PATH_EDGE_LEFT(5),
	PATH_EDGE_RIGHT(6);
	
	private int id;

	private TileType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static TileType getType(int id) {
		for (TileType t: TileType.values()) {
			if (t.getId() == id) {
				return t;
			}
		}
			return TileType.GRASS;
	}
}
