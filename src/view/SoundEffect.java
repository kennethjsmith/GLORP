package view;

import java.io.*;
import javax.sound.sampled.*;

public enum SoundEffect {
   LOSE("src/sounds/lose.wav");   // explosion
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip myClip;
   private FloatControl myGainControl;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String theSoundFileName) {
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
   
   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
	   if (myClip.isRunning()) myClip.stop();   // Stop the player if it is still running
       myClip.setFramePosition(0); // rewind to the beginning
       myClip.start();     // Start playing
   }
   
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