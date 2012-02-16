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
import ai.randomai.RandomAI;
import ai.reinforcement.ReinforcementAI;


public class GameWindow extends JFrame {
	/**
	 * Version Id
	 */
	public static final double VERSION = 3.0;
	/**
	 * Windows locked height and width
	 */
	private final int HEIGHT = 600, WIDTH = 400;
	/**
	 * Do not allow resizing
	 */
	private final boolean RESIZABLE = false;
	/**
	 * Visual representation of the number of pabbles left
	 */
	private JProgressBar progress;
	/**
	 * Area to display log text.
	 */
	private JTextArea log;
	/**
	 * JLabel showing the number of pebbles under the progress bar
	 */
	private JLabel status;
	/**
	 * Scroll pane containing the log
	 */
	private JScrollPane scrollable;
	/**
	 * Game board instance
	 */
	private GameBoard board;
	/**
	 * Tracks if the game has ended
	 */
	private boolean gameOver = false;
	/**
	 * Storage for the random AI 1 instance
	 */
	private AI randomAI1 = new RandomAI();
	/**
	 * Storage for the random AI 2 instance
	 */
	private AI randomAI2 = new RandomAI();
	/**
	 * Storage for the reinforcement AI 1 instance
	 */
	private AI reinforcementAI1 = new ReinforcementAI();
	/**
	 * Storage for the reinforcement AI 1 instance
	 */
	private AI reinforcementAI2 = new ReinforcementAI();
	/**
	 * Storage for the queue AI 1 instance
	 */
	private AI queueAI1 = new Q();
	/**
	 * Storage for the queue AI 2 instance
	 */
	private AI queueAI2 = new Q();
	/**
	 * Current AI solution 1
	 */
	private AI computer1 = reinforcementAI1;
	/**
	 * Current AI solution 2
	 */
	private AI computer2 = reinforcementAI2;
	
	/**
	 * @author Karl Schmidbauer <schmidbauerk@msoe.edu>
	 *
	 */
	private enum mode {twoPlayer, onePlayer, noPlayer}
	
	/**
	 * Stores the current game mode
	 */
	private mode currentMode;
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -7339354437547422011L;

	/**
	 * Creates a new instance and sets-up the window
	 * @param board - game board instance
	 */
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
		//Stores the board
		this.board = board;
		setProgress(this.board.getPebblesLeft());
		//Welcome message
		addToLog("Welcome! For how to play press r.\n");
		addToLog("Player 1, it is your turn.");
	}

	/**
	 * Initilizes the menubar
	 */
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
		ButtonGroup modeGroup = new ButtonGroup();
		//No players
		JRadioButtonMenuItem gameMode = new JRadioButtonMenuItem("No Players");
		gameMode.setActionCommand("AI");
		gameMode.addActionListener(new ButtonListener());
		modeGroup.add(gameMode);
		options.add(gameMode);
		//Single Player
		gameMode = new JRadioButtonMenuItem("Single Player");
		gameMode.setSelected(true);
		currentMode = mode.onePlayer;
		gameMode.setActionCommand("SP");
		gameMode.addActionListener(new ButtonListener());
		modeGroup.add(gameMode);
		options.add(gameMode);
		//Multiplauer
		gameMode = new JRadioButtonMenuItem("Multi Player");
		gameMode.setActionCommand("MP");
		gameMode.addActionListener(new ButtonListener());
		modeGroup.add(gameMode);
		options.add(gameMode);
		//Add seperator
		options.addSeparator();
		//Use the random ai
		ButtonGroup aiGroup = new ButtonGroup();
		gameMode = new JRadioButtonMenuItem("Random AI");
		gameMode.setActionCommand("Random");
		gameMode.addActionListener(new ButtonListener());
		aiGroup.add(gameMode);
		options.add(gameMode);
		//Use the queue ai
		gameMode = new JRadioButtonMenuItem("Queue AI");
		currentMode = mode.onePlayer;
		gameMode.setActionCommand("Queue");
		gameMode.addActionListener(new ButtonListener());
		aiGroup.add(gameMode);
		options.add(gameMode);
		//Use the reinforencement ai
		gameMode = new JRadioButtonMenuItem("Reinforcement AI");
		gameMode.setSelected(true);
		gameMode.setActionCommand("Reinforcement");
		gameMode.addActionListener(new ButtonListener());
		aiGroup.add(gameMode);
		options.add(gameMode);
		//Add a sperator
		options.addSeparator();
		//Add pebble count
		JMenuItem max = new JMenuItem("Number of Pebbles");
		max.setSelected(true);
		max.setActionCommand("max");
		max.addActionListener(new ButtonListener());
		options.add(max);
		//Add everything to the menu bar
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
		
		//Adds the menu bar to the window
		this.setJMenuBar(menu);
	}

	/**
	 * Menu Item factory element
	 * 
	 * @param itemName
	 * @return
	 */
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
		button.setName(""+number);
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
		computer1.gameOver((board.getCurrentPlayer() == players.player2) ? false : true);
		
	}


	private void takeAway(int remove){
		if(gameOver) return;
		try {
			addToLog("Removing :" + remove);
			board.takeAway(remove);
			setProgress(board.getPebblesLeft());
			board.switchPlayers();
			addToLog((board.getCurrentPlayer() == players.player1) ? "Player 1, it is your turn." : "Player 2, it is your turn.");
			int computersMove = computer1.choose(board);
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
				if(currentMode == mode.noPlayer) takeAway(computer1.choose(board));
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
				takeAway(computer1.choose(board));
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
			} else if(e.getActionCommand().equals("Random")){
				computer1 = randomAI1;
				computer2 = randomAI2;
			} else if(e.getActionCommand().equals("Queue")){
				computer1 = queueAI1;
				computer2 = queueAI2;
			} else if(e.getActionCommand().equals("Reinforcement")){
				computer1 = reinforcementAI1;
				computer2 = reinforcementAI2;
			} else if(e.getActionCommand().equals("max")){
				try{
				board = new GameBoard(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a number of pebbles to start with:")));
				}catch(Exception ex){
				}
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
