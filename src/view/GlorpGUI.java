package view;

import java.awt.Dimension;
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

public class GlorpGUI extends JFrame implements KeyListener, ActionListener {
    
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
    private MazePanel myMazePanel;
    private RiddlePanel myRiddlePanel;
    //TODO: add panels for maze map and sphinx/questions

    public GlorpGUI() {
        super();
        setTitle(TITLE);
        JPanel gamePanel = new JPanel();
        //gamePanel.setLayout(new BoxLayout());
        setContentPane(gamePanel);
        myMazePanel = new MazePanel();
        gamePanel.add(myMazePanel);
        myRoomPanel = new RoomPanel();
        gamePanel.add(myRoomPanel);
        myRiddlePanel = new RiddlePanel();
        gamePanel.add(myRiddlePanel);
        pack();
        addKeyListener(this);
        repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        myRoomPanel.keyPressed(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { 
    	myRoomPanel.keyReleased(e);
        repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
}