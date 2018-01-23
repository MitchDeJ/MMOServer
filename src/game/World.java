package game;

import java.util.List;

import server.Server;
import server.packets.types.AreaPacket;
import server.packets.types.Packet;

public class World {

	private List<Area> areas = AreaManager.loadAreas();
	private Area defaultArea = areas.get(0);
	private Server server;
	
	public World(Server server) {
		this.server = server;
		server.log("Loaded "+ObjectManager.definitions.size()+" object definitions.");
		server.log("Loaded "+areas.size()+" areas.");
		server.log("Default area set to: " + defaultArea.getName());
	}
	
	public Server getServer() {
		return server;
	}

	public void tick() {
		for (Area a : areas) {
			a.tick();
		}
	}
	
	public Area getDefaultArea() {
		return this.defaultArea;
	}
	
}
