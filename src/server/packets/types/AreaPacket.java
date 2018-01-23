package server.packets.types;

import game.model.entity.WorldObject;
import game.model.map.Area;
import game.model.map.Tile;

public class AreaPacket extends Packet {
	
	private Area area;

	public AreaPacket(Area area) {
		super(00);
		this.area = area;
	}

	public byte[] getData() {
		
		/* Converting the area's tiles into a string */
		String tilesData = "";
		for (int y = 0; y < area.getHeight(); y++) {
			for (int x = 0; x < area.getWidth(); x++) {
				tilesData += area.getTiles()[x][y].getType().getId()+"#";
			}
		}
		
		
		/* Converting the area's objects into a JSON string */
		String objectsJSON =
				"[";
		
			for (WorldObject o : area.getObjects()) {
				objectsJSON += "{"
						  +   "\"t\":"+o.getType().getId()+","
						  +   "\"x\":"+o.getX()+"," 
						  +   "\"y\":"+o.getY()
						  +  "}";
			}
		
		objectsJSON += "]";
		
		String[] data = new String[] 
				{
						area.getName(),
						""+area.getWidth(),
						""+area.getHeight(),
						tilesData,//tiles
						objectsJSON // objects
				};
		return packData(data).getBytes();
	}

}
