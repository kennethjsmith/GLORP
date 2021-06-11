/*
 * GLORP: Revenge of the Sphinx
 */

package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;


/**
 * Serializes a component for saving and loading the game.
 * @author Katelynn Oleson, Ken Smith, Heather Finch.
 * @version 1.0.
 */
public final class SerializeGame {
	// Streams used for writing to and reading from files. 
    private static FileOutputStream myFileOutput;
    private static ObjectOutputStream myObjectOut;
    private static FileInputStream myFileInput;
    private static ObjectInputStream myObjectIn;

    /**
     * Saves an object in a file.
     * @param theObject The object to be saved
     * @param theObjectName The name of the object being saved
     */
    public static void serializeMe(Object theObject, String theObjectName) {
        Objects.requireNonNull(theObject);
        Objects.requireNonNull(theObjectName);
        try {
            myFileOutput = new FileOutputStream("./SavedGame/" + theObjectName + ".txt");
            myObjectOut = new ObjectOutputStream(myFileOutput); 
            myObjectOut.writeObject(theObject);
            myObjectOut.close();
            myFileOutput.close(); 
            
            System.out.println("Serialized " + theObjectName);
        } catch (IOException e2) {
            System.out.println("Error Serializing " + theObjectName);
            e2.printStackTrace();
       } 
    }
    
    /**
     * Deserialized an object by reading from a file.
     * @param theObject The object to be read
     * @param theObjectName The name of the objecting being read
     * @return Object the object that was deserialized
     */
    public static Object deserializeMe(Object theObject, String theObjectName) { 
        Objects.requireNonNull(theObject);
        Objects.requireNonNull(theObjectName);
    	Object inDeserializedObject = null;
        try {
            myFileInput = new FileInputStream("./SavedGame/" + theObjectName + ".txt");
            myObjectIn = new ObjectInputStream(myFileInput);    
            inDeserializedObject = myObjectIn.readObject(); // Method for deserialization of object             
            myObjectIn.close();             
            myFileInput.close();            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing " + theObjectName);
            e.printStackTrace();
        }             
        return Objects.requireNonNull(inDeserializedObject);
    }

}
