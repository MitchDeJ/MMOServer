package server;

import java.util.ArrayList;
import java.util.List;

import game.model.entity.player.Player;
import server.packets.types.Packet;
import server.packets.types.PingPacket;

public class NetworkManager {
	
	private Server server;
	private int pingTicks = 0;

	public NetworkManager(Server server) {
		this.server = server;
	}
	
	public void ping() {
		List<Player> toRemove = new ArrayList<Player>();
		for (Player p : server.getPlayerList().all()) {
			Packet packet = new PingPacket();
			packet.sendData(server, p.getIP(), p.getPort());
			p.pingSent = System.currentTimeMillis();
			p.pendingPings ++;
			
			if (p.pendingPings > server.getConfig().pingMissLimit) {
				toRemove.add(p);
			}
		}
		
		for (Player p: toRemove) {
			server.getPlayerList().remove(p, "Lost connection");
		}
	}

	public void tick() {
		pingTicks++;
		if (pingTicks == 200) {
			pingTicks = 0;
			ping();
		}
	}

}
