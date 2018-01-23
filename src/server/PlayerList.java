package server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import game.model.entity.player.Player;

public class PlayerList {
	
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	private Server server;
	
	public PlayerList(Server server) {
		this.server = server;
	}
	
	public int add(Player p) {
		if (freeSlot() == -1)
			return -1;
		int id = freeSlot();
		players.put(id, p);
		server.log(p.getName()+" connected ("+id+").");
		return id;
	}
	
	public void remove(Player p, String reason) {
		for (int i = 0; i < Config.playerLimit; i++) {
			if (players.get(i) == p) {
				server.log(p.getName() + " was removed from the server ("+reason+").");
				players.remove(i);
			}
		}
	
	}
	
	public void clear() {
		for (int i = 0; i < Config.playerLimit; i++) {
			if (players.containsKey(i))
				players.remove(i);
		}
	}


	private int freeSlot() {
		for (int i = 0; i < Config.playerLimit; i++) {
			if (players.get(i) == null)
				return i;
		}
		return -1;
	}
	
	public Player getPlayerByConnection(InetAddress ip, int port) {
		
		for(Entry<Integer, Player> entry : players.entrySet()) {
		    Player player = entry.getValue();

			if (player.getIP().toString().equalsIgnoreCase(ip.toString()) && player.getPort() == port) {
				return player;
			}
		}
		return null;
	}

	public List<Player> all() {
		List<Player> all = new ArrayList<Player>();
		for(Entry<Integer, Player> entry : players.entrySet()) {
		    Player player = entry.getValue();
		    all.add(player);
		}
		return all;
	}
	

}
