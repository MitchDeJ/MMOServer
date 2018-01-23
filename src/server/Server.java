package server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

import game.model.entity.player.Player;
import game.model.map.World;
import server.packets.PacketParser;
import server.packets.PacketReceiver;

public class Server implements Runnable {
	
	private DatagramSocket serverSocket;
	private PacketReceiver packetReceiver;
	private PacketParser packetParser;
	
	private Config config = new Config();
	private boolean running = false; // is the server running?
	private Thread thread; // server thread
	
	private World world;
	private NetworkManager networkManager;
	
	private PlayerList players;
	
	public static void main(String[] args) {
		new Server().start();
	}
	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		log("Started server. Listening for packets on port "+config.serverPort+".");
					
		/*server loop*/
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
		}
	}

	private void init() {
		log("Creating world...");
		world = new World(this);
		
		log("Attempting to open DatagramSocket...");
		try {
			serverSocket = new DatagramSocket(config.serverPort);
		} catch (SocketException e) {
			log("Could not open the DatagramSocket (maybe there is an instance of the server still running?)");
			System.exit(0);
		}
		
		log("Starting packet receiver...");
		packetReceiver = new PacketReceiver(this);
		packetReceiver.start();
		log("Instantiating packet parser...");
		packetParser = new PacketParser(this);
		log("Starting network manager...");
		networkManager = new NetworkManager(this);
		log("Instantiating player list...");
		players = new PlayerList(this);
	}

	private void tick() {
		world.tick();
		networkManager.tick();
		for (Player p : players.all())
			p.tick();
	}
	
	public void log(String message) {
		System.out.println("["+getConfig().serverPrefix+"] > "+message);
	}

	public Config getConfig() {
		return config;
	}

	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public PacketParser getPacketParser() {
		return packetParser;
	}
	
	public World getWorld() {
		return this.world;		
	}

	public void sendData(byte[] data, InetAddress ip, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		try {
			serverSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PlayerList getPlayerList() {
		return players;
	}
	
}