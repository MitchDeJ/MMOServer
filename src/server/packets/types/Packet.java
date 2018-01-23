package server.packets.types;

import java.net.InetAddress;

import server.Server;

public abstract class Packet {

	public byte packetId;
	
	public Packet(int packetId) {
		this.packetId = (byte) packetId;
	}
	
	public abstract byte[] getData();
	
	public String packData(String[] data) {
		/*converting the packet id to a double digit string (01, 00, etc)*/
		String idString; 
		if (packetId > -1)
			idString = (packetId < 10 ? "0" : "") + packetId + ";";	
		else //if the packet is invalid
			idString = "-1";				
		
		String message = idString;
		
		/*Adding the array of data*/
		for (int i = 0; i < data.length; i++) {
			message += data[i] + ";";
		}
		return message;
	}
	
	public void sendData(Server server, InetAddress address, int port) {
		server.sendData(getData(), address, port);
	}
	
	public static PacketTypes lookupPacket(String packetId) {
		try {
		return lookupPacket(Integer.parseInt(packetId));
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return PacketTypes.INVALID;
	}
	
	public static PacketTypes lookupPacket(int id) {
		for (PacketTypes p: PacketTypes.values()) {
			if (p.getId() == id) {
				return p;
			}
		}
			return PacketTypes.INVALID;
	}
	
}
