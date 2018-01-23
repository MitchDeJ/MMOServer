package server.packets.types;

public enum PacketTypes {
	INVALID(-1),
	NEW_PLAYER(01),
	PING(02);
	
	private int packetId;
	
	private PacketTypes(int packetId) {
		this.packetId = packetId;
	}
	
	public int getId() {
		return packetId;
	}
}
