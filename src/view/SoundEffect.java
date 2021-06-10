package view;

import java.io.*;
import javax.sound.sampled.*;

/**
 * An enumerated type for all of the sound effects used in Glorp.
 * @author Ken Smith, Katelynn Oleson, Heather Finch.
 * @version 1.0
 */
public enum SoundEffect {
	ITEM("src/sounds/item.wav"),
	EXPLOSION("src/sounds/explosion.wav"),
	WALK("src/sounds/walk.wav"),
	MOON_WALK("src/sounds/moon_walk.wav"),
	WIN("src/sounds/win.wav"),
    LOSE("src/sounds/lose.wav");   // explosion
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip myClip;
   private FloatControl myGainControl;
   
   // Constructor to construct each element of the enum with its own sound file.
   private SoundEffect(String theSoundFileName) {
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
    * Play or Re-play the sound effect from the beginning, by rewinding.
 	*/
   public void play() {
       myClip.setFramePosition(0); // Rewind to the beginning.
       myClip.start(); // Start playing.
   }
   
   /**
    * Sets the volume for the sound effects.
    * @param float The new volume for the sound effects
 	*/
   public static void setVolume(float theVolume) {
	   for (SoundEffect effect : SoundEffect.values()) { 
		    effect.myGainControl.setValue(theVolume); 
		}
   }
   // TODO: use it or lose it, for music class too
   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}