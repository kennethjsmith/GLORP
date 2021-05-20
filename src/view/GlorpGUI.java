package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import model.Player;

public class GlorpGUI extends JFrame {
    
    // static final fields (class constants)

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 0;

    /**
     * The window title.
     */
    private static final String TITLE = "GLORP";
    
    private RoomPanel myRoomPanel;
    private MapPanel myMazePanel;
    private RiddlePanel myRiddlePanel;
    //TODO: add panels for maze map and sphinx/questions

    public GlorpGUI() {
        super();
        setTitle(TITLE);
        setPreferredSize(new Dimension(840, 840));
        setResizable(false);
        this.setLayout(new BorderLayout());
        myMazePanel = new MapPanel();
        add(myMazePanel, BorderLayout.WEST);
        myRoomPanel = new RoomPanel();
        add(myRoomPanel, BorderLayout.EAST);
        myRiddlePanel = new RiddlePanel();
        add(myRiddlePanel, BorderLayout.SOUTH);
        pack();
        repaint();
    }
    //TODO: this method should pass a room to paint, not just a character
    public void paintRoomPanel(Player thePlayer) {
        myRoomPanel.setCurrentPlayer(thePlayer);
        repaint();
    }

}