package server;

public class Config {

	public int serverPort = 1337;
	public String serverPrefix = "MMO-Server";
	
	public static int playerLimit = 100;
	public static boolean developerMode = false; //set to false when exporting for loading external files
	public int pingMissLimit = 3; // the amount of pings a player can miss before being removed
	
}
