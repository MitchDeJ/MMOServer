package game;

import java.net.InetAddress;


public class Player extends Entity {
	
	private String name;
	private InetAddress ip;
	private int port;
	
	private long ping = 0;
	public long pingSent = 0;
	public int pendingPings = 0;
	
	public Player(String name, InetAddress ip, int port) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}

	public InetAddress getIP() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void tick() {
		//
	}

	public void setPing(long ping) {
		this.ping  = ping;	
	}
	
	public long getPing() {
		return this.ping;
	}

}
