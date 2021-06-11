/*
 * GLORP: Revenge of the Sphinx
 */

package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * An enumerated type for all of the music used in GLORP.
 * @author Ken Smith, Heather Finch, Katelynn Oleson
 * @version 1.0
 */
public enum Music {
	WLAE("src/sounds/wlae.wav"),
	ATHF("src/sounds/athf.wav");   // Explosion.
	   
	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip myClip;
    private FloatControl myGainControl;
	   
    // Constructor to construct each element of the enum with its own sound file.
    private Music(String theSoundFileName) {
    	try {
    		File file = new File(theSoundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            // Get a clip resource.
            myClip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            myClip.open(audioInputStream);
            myGainControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
            myGainControl.setValue(Volume.MEDIUM.getGain());
        } catch (UnsupportedAudioFileException e) {
        	e.printStackTrace();
    	} catch (IOException e) { 
    		e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
        }
    }
	   
    /**
     * Stops all music, then plays this.
	 */
	public void play() {
		stop();
		if (myClip.isRunning()) myClip.stop();   // Stop the player if it is still running
		myClip.setFramePosition(0); // rewind to the beginning
		myClip.loop(Clip.LOOP_CONTINUOUSLY); 
	}
	   
	/**
	 * Stops the music currently playing.
	 */
	public static void stop() {
		for (Music music : Music.values()) { 
			music.myClip.stop(); 
		}
    }
	   
	/**
	 * Sets the volume for the music.
	 * @param float The new volume
	 */
	public static void setVolume(float theVolume) {
		for (Music music :Music.values()) { 
			music.myGainControl.setValue(theVolume); 
		}
	}
	   
	/**
	 * Optional static method to pre-load all the sound files.
	 */
	public static void init() {
		values(); // calls the constructor for all the elements
	}
}
