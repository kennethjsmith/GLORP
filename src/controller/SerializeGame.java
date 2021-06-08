package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public final class SerializeGame {
    private static FileOutputStream myFileOutput;
    private static ObjectOutputStream myObjectOut;
    private static FileInputStream myFileInput;
    private static ObjectInputStream myObjectIn;

    //Saving of object in a file    
    public static void serializeMe(Object theObject, String theObjectName) {
        //System.out.println(theObject);   
        
        try {
            myFileOutput = new FileOutputStream("./SavedGame/" + theObjectName + ".txt");
            myObjectOut = new ObjectOutputStream(myFileOutput); 
            
            myObjectOut.writeObject(theObject);
            
            myObjectOut.close();
            myFileOutput.close(); 
            
            System.out.println("Serialized " + theObjectName);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            System.out.println("Error Serializing " + theObjectName);
            e2.printStackTrace();
       } 
    }
    
    // Reading the object from a file
    public static Object deserializeMe(Object theObject, String theObjectName) { 
        Object inDeserializedObject = null;
        
        try {
            myFileInput = new FileInputStream("./SavedGame/" + theObjectName + ".txt");
            myObjectIn = new ObjectInputStream(myFileInput);    
            
            // Method for deserialization of object             
            inDeserializedObject = myObjectIn.readObject();  
            
            myObjectIn.close();             
            myFileInput.close();
            
            System.out.println("Deserialized " + theObjectName);
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Error deserializing " + theObjectName);
            e.printStackTrace();
        }             
         
        
        return inDeserializedObject;
    }

}
