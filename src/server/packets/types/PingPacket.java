package server.packets.types;

public class PingPacket extends Packet {

	public PingPacket() {
		super(01);
	}

	@Override
	public byte[] getData() {
		String[] data = {""};
		return packData(data).getBytes();
	}

}
