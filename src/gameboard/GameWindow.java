package gameboard;

import gameboard.GameBoard.players;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import ai.AI;
import ai.q.Q;
import ai.reinforcement.ReinforcementAI;

public class GameWindow extends JFrame {
	public static final double VERSION = 1.0;
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
	private GameBoard board;
	private boolean gameOver = false;
	private AI computer = new Q();
	private AI computer2 = new Q();
	
	
	private enum mode {twoPlayer, onePlayer, noPlayer}
	
	private mode currentMode;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7339354437547422011L;

	public GameWindow(GameBoard board){
		//Sets the window properties
		super("GATTA TIPNE KHEL (Pebble Game)");
		setSize(WIDTH, HEIGHT);
		setResizable(RESIZABLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-WIDTH)/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height-HEIGHT)/2);
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

		this.board = board;
		setProgress(this.board.getPebblesLeft());
		
		//Welcome message
		addToLog("Welcome! For how to play press r.\n");
		addToLog("Player 1, it is your turn.");
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
		//Close
		item = createMenuItem("Close");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		file.add(item);
		//Add the file menu to the menu bar
		menu.add(file);
		
		//Options Menu
		JMenu options = new JMenu("Options");
		//a group of radio button menu items
		ButtonGroup group = new ButtonGroup();
		//No players
		JRadioButtonMenuItem gameMode = new JRadioButtonMenuItem("No Players");
		currentMode = mode.onePlayer;
		gameMode.setActionCommand("AI");
		gameMode.addActionListener(new ButtonListener());
		group.add(gameMode);
		options.add(gameMode);
		//Single Player
		gameMode = new JRadioButtonMenuItem("Single Player");
		gameMode.setSelected(true);
		currentMode = mode.onePlayer;
		gameMode.setActionCommand("SP");
		gameMode.addActionListener(new ButtonListener());
		group.add(gameMode);
		options.add(gameMode);
		//Multiplauer
		gameMode = new JRadioButtonMenuItem("Multi Player");
		currentMode = mode.onePlayer;
		gameMode.setActionCommand("MP");
		gameMode.addActionListener(new ButtonListener());
		group.add(gameMode);
		options.add(gameMode);
		
		menu.add(options);
		
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
		ImageIcon pic = null;
		if(number==1) pic = new ImageIcon("images/singlePebble.jpg");
		else if(number==2) pic = new ImageIcon("images/doublePebble.jpg");
		else if(number==3) pic = new ImageIcon("images/tripplePebble.jpg");
		JButton button = new JButton(pic);
		button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
		button.setToolTipText("Pick up "+number+" pebble(s)");
		button.setActionCommand(""+number);
		button.addActionListener(new ButtonListener());
		button.addKeyListener(new KeyboardListener());
		button.setBackground(Color.white);
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
		log.setCaretPosition(log.getText().length());
		log.append(msg+'\n');
	}

	private void gameOver(String msg) {
		log.setForeground(Color.red);
		log.append("GAME OVER: "+msg);
		gameOver = true;
		computer.gameOver((board.getCurrentPlayer() == players.player2) ? false : true);
		
	}


	private void takeAway(int remove){
		if(gameOver) return;
		try {
			addToLog("Removing :" + remove);
			board.takeAway(remove);
			setProgress(board.getPebblesLeft());
			board.switchPlayers();
			addToLog((board.getCurrentPlayer() == players.player1) ? "Player 1, it is your turn." : "Player 2, it is your turn.");
			int computersMove = computer.choose(board);
			int computer2sMove = computer2.choose(board);
			switch(currentMode){
			case onePlayer:
				if(board.getCurrentPlayer() == players.player2) takeAway(computersMove);
				break;
			case noPlayer:
				if(board.getCurrentPlayer() == players.player2) takeAway(computersMove);
				else takeAway(computer2sMove);
				break;
			}
		} catch (GameOver e) {
			gameOver(e.getMessage());
		}
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("1") && !(board.getCurrentPlayer()==players.player2 && currentMode == mode.onePlayer) && !(currentMode == mode.noPlayer)){
				takeAway(1);
			} else if(e.getActionCommand().equals("2") && !(board.getCurrentPlayer()==players.player2 && currentMode == mode.onePlayer) && !(currentMode == mode.noPlayer)){
				takeAway(2);
			} else if(e.getActionCommand().equals("3") && !(board.getCurrentPlayer()==players.player2 && currentMode == mode.onePlayer) && !(currentMode == mode.noPlayer)){
				takeAway(3);
			} else if(e.getActionCommand().equals("New")){
				log.setText("Starting new game!\n");
				log.setForeground(Color.black);
				board.newGame();
				setProgress(board.getPebblesLeft());
				gameOver = false;
				if(currentMode == mode.noPlayer) takeAway(computer.choose(board));
			} else if(e.getActionCommand().equals("Close")){	
				System.exit(NORMAL);
			} else if(e.getActionCommand().equals("Rules")){
				JOptionPane.showMessageDialog(null, "Goal:\nGiven a random number of pebbles, force the other \nplayer to pick up the last pebble.\n"
						+ "\nRules:\nChoose one, two or three pebbles to pick up. "
						+ "\nThe player that is forced to pick up the last pebble loses."
						+ "\nThe status bar at the bottom of the screen displays the \nnumber of pebbles left in the pile."
						+ "\n\nHow to Play: \nPress the button corresponding to the number of pebbles \nyou want to remove,"
						+ "or press the key on the keyboard."
						);
			} else if(e.getActionCommand().equals("About")){
				JOptionPane.showMessageDialog(null, "Created by:\nKarl Schmidbauer and Ben Ebert\n\nVersion:\n"+VERSION);
			} else if(e.getActionCommand().equals("AI")){
				currentMode = mode.noPlayer;
				log.setText("Starting new game!\n");
				log.setForeground(Color.black);
				board.newGame();
				gameOver = false;
				takeAway(computer.choose(board));
			} else if(e.getActionCommand().equals("SP")){
				currentMode = mode.onePlayer;
				log.setText("Starting new game!\n");
				log.setForeground(Color.black);
				board.newGame();
				gameOver = false;
			} else if(e.getActionCommand().equals("MP")){
				currentMode = mode.twoPlayer;
				log.setText("Starting new game!\n");
				log.setForeground(Color.black);
				board.newGame();
				gameOver = false;
			} else{
				addToLog(e.getActionCommand());
			}
		}
	}

	private class KeyboardListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) { }
		@Override
		public void keyReleased(KeyEvent e) { }
		@Override
		public void keyTyped(KeyEvent e) {
			if((board.getCurrentPlayer()==players.player2 && currentMode == mode.onePlayer) && currentMode == mode.noPlayer) return;
			switch(e.getKeyChar()){
			case '1':
				takeAway(1);
				break;
			case '2':
				takeAway(2);
				break;
			case '3':
				takeAway(3);
				break;
			default:
			}
		}
	}
}
