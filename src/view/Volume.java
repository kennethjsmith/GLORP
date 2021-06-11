package view;

/**
 * An enumerated type for the different volume options
 * for sound effects and music.
 * @author Ken Smith, Heather Finch, Katelynn Oleson.
 * @version 1.0.
 */
public enum Volume {
    MUTE(0, -80f), LOW(1, -50f), MEDIUM(2, -40f), HIGH(3, -20f);

	private final int myLevel;
	private final float myGain;
	
	// Constructs the Volume.
	private Volume(int i, float j) {
		myLevel = i;
		myGain = j;
	}
	
	/**
	 * Getter for the volumes level.
	 * @return The volume level, an int
	 */
	public int getLevel() {
		return myLevel;
	}
	
	/**
	 * Getter for the gain.
	 * @return myGain, a float
	 */
	public float getGain() {
		return myGain;
	}
 }
