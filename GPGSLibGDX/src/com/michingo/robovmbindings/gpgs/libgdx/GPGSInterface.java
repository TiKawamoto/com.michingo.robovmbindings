package com.michingo.robovmbindings.gpgs.libgdx;

public interface GPGSInterface {
	
	/** Logs you into the services. Only call after an actual user click. */
	public void login();
	
	/** Logs you out of the services. Only call after an actual user click. */
	public void logout();
	
	/** Saves the game in the cloud.
	 * @param statekey the save slot. Choose from 0 to 3.
	 * @param the data. */
	public void updateGameState(int statekey, byte[] data);
	
	/** Retrieves the savegame from the cloud.
	 * @param statekey the save slot. Choose from 0 to 3. */
	public void getGameState(int statekey);
}
