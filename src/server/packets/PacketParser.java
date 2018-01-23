package server.packets;

import java.net.InetAddress;
import java.util.ArrayList;

import game.model.entity.player.Player;
import game.model.map.Area;
import server.Server;
import server.packets.types.AreaPacket;
import server.packets.types.Packet;
import server.packets.types.PacketTypes;

public class PacketParser {

	private Server server;

	public PacketParser(Server server) {
		this.server = server;
	}

	public void parsePacket(byte[] data, InetAddress address, int port) {

		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet;
		Player p = server.getPlayerList().getPlayerByConnection(address, port);
		switch (type) {
					
		case NEW_PLAYER:
			Area area = server.getWorld().getDefaultArea();
			packet = new AreaPacket(area);
			packet.sendData(server, address, port);
			
			server.getPlayerList().add(
					new Player(
							parseIndex(message, 0),
							address,
							port
					)
			);			
			break;
			
		case PING:
			p.setPing((System.currentTimeMillis() - p.pingSent) / 2);
			p.pendingPings = 0;
			break;
		
		case KEYSTATE:
			server.log(message);
			break;
			
		default:
			server.log("Received unhandled packet!");
			break;

		}
	}

	private String parseIndex(String message, int index) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		char divider = ';';
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == divider) {
				list.add(i);
			}
		}
		int[] div = new int[list.size()];

		for (int i = 0; i < div.length; i++) {
			div[i] = list.get(i).intValue();
		}

		return message.substring(div[index] + 1, div[index + 1]);
	}
}
