package view;

public enum Volume {
    MUTE(0, -80f), LOW(1, -50f), MEDIUM(2, -40f), HIGH(3, -20f);

	private final int myLevel;
	private final float myGain;
	
	Volume(int i, float j) {
		myLevel = i;
		myGain = j;
	}
	
	/**
	 * @return myLevel
	 */
	public int getLevel() {
		return myLevel;
	}
	
	/**
	 * @return myGain
	 */
	public float getGain() {
		return myGain;
	}
 }
