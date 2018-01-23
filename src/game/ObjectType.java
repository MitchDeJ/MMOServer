package game;

public enum ObjectType {
	
	BUSH(0),
	SIGN(1);
	
	private int id;

	private ObjectType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static ObjectType getType(int id) {
		for (ObjectType t: ObjectType.values()) {
			if (t.getId() == id) {
				return t;
			}
		}
			return ObjectType.BUSH;
	}
}