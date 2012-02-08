package gameboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private final int HEIGHT = 600, WIDTH = 400;
	/**
	 * 
	 */
	private final boolean RESIZABLE = false;
	/**
	 * 
	 */
	private JProgressBar progress;
	/**
	 * 
	 */
	private JTextArea log;
	
	private JLabel status;
	
	private JScrollPane scrollable;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7339354437547422011L;
	
	public GameWindow(){
		//Sets the window properties
		super("GATTA TIPNE KHEL (Pebble Game)");
		setSize(WIDTH, HEIGHT);
		setResizable(RESIZABLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new WinowEventListener());
		//Sets the layout to be null
		setLayout(null);
		
		//Adds elements
		addMenuBar();
		addLogWindow();
		addButtons();
		addStatusBar();
		addStatus();
		
		//Sets visible
		setVisible(true);
	}
	
	private void addMenuBar(){
		JMenuBar menu = new JMenuBar();
		JMenuItem item; //Storage item
	
		//File menu
		JMenu file = new JMenu("File");
		//New
		item = createMenuItem("New");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		file.add(item);
		file.addSeparator();
		//Options
		file.add(createMenuItem("Options"));
		file.addSeparator();
		//Close
		item = createMenuItem("Close");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		file.add(item);
		//Add the file menu to the menu bar
		menu.add(file);
		
		//Help menu
		JMenu help = new JMenu("Help");
		//Rules
		item = createMenuItem("Rules");
		item.setAccelerator(KeyStroke.getKeyStroke("R"));
		help.add(item);
		//Help
		item = createMenuItem("About");
		item.setAccelerator(KeyStroke.getKeyStroke("F1"));
		help.add(item);
		//Add the help menu to the menu bar
		menu.add(help);
		
		this.setJMenuBar(menu);
	}

	private JMenuItem createMenuItem(String itemName){
		JMenuItem item = new JMenuItem(itemName);
		item.addActionListener(new ButtonListener());
		item.setActionCommand(itemName);
		return item;
	}

	private void addLogWindow(){
		//Sets up the log
		log = new JTextArea();
		log.setBackground(Color.white);
		log.setForeground(Color.black);
		log.setEditable(false);
		log.addKeyListener(new KeyboardListener());
		
		//Sets up the scroll pane
		scrollable = new JScrollPane(log);
		scrollable.setAutoscrolls(true);
		scrollable.addKeyListener(new KeyboardListener());
		scrollable.setFocusTraversalKeysEnabled(false);
		scrollable.setFocusable(true);
		scrollable.setLocation(25, 15);
		//HEIGHT = 390-25-10 = 355
		scrollable.setSize(WIDTH-50, 355);
		
		//Adds to the Frame
		this.add(scrollable);
	}
	
	private void addButtons(){
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.setSize(WIDTH-50, 100);
		//StatusBarLocation-ButtonHeight-Buffer = 500-200-10 = 290
		buttonPanel.setLocation(25, 380);
		
		//Generate Buttons
		buttonPanel.add(createButtons(1));
		buttonPanel.add(createButtons(2));
		buttonPanel.add(createButtons(3));
		
		//Add the panel
		this.add(buttonPanel);
	}
	
	private JButton createButtons(int number){
		JButton button = new JButton();
		button.setText(""+number);
		button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
		button.setToolTipText("Pick up "+number+" pebble(s)");
		button.setBackground(Color.white);
		button.setForeground(Color.black);
		button.setActionCommand(""+number);
		button.addActionListener(new ButtonListener());
		button.addKeyListener(new KeyboardListener());
		return button;
	}
	
	private void addStatusBar(){
		progress = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		progress.setSize(WIDTH-50, 25);
		progress.setLocation(25, 490);
		progress.setForeground(new Color(50,200,50));
		progress.setBackground(new Color(200, 225, 255));
		progress.addKeyListener(new KeyboardListener());
		//Add to the JFrame
		this.add(progress);
	}
	
	private void addStatus() {
		status = new JLabel();
		status.setText(0+"");
		status.setLocation(25, 515);
		status.setSize(WIDTH-50, 25);
		status.setAlignmentX(CENTER_ALIGNMENT);
		status.setAlignmentY(CENTER_ALIGNMENT);
		status.setBackground(Color.white);
		add(status);
	}

	public void setProgress(int value){
		progress.setValue(value);
		progress.setToolTipText("There are "+value+" pebles left");
		status.setText(value+"");
	}
	
	private void addToLog(String msg){
		scrollable.setFocusable(true);
		log.append(msg+'\n');
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			addToLog(e.getActionCommand());
		}
	}
	
	private class KeyboardListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
			switch(e.getKeyChar()){
			case '1':
				addToLog(e.getKeyChar()+"");
				break;
			case '2':
				addToLog(e.getKeyChar()+"");
				break;
			case '3':
				addToLog(e.getKeyChar()+"");
				break;
			default:
			}
		}
	}
	
	private class WinowEventListener implements WindowListener{
		@Override
		public void windowActivated(WindowEvent e) {
		}
		@Override
		public void windowClosed(WindowEvent e) {
		}
		@Override
		public void windowClosing(WindowEvent e) {
			//TODO
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		@Override
		public void windowIconified(WindowEvent e) {
		}
		@Override
		public void windowOpened(WindowEvent e) {
		}
	}
}
