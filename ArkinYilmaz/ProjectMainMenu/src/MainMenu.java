import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * 
 * Main menu panel is the first class where interaction with the user stars.
 * It contains a game title and a few options to start game,to go options, to view high scores,etc..
 * 
 */


@SuppressWarnings("serial")
public class MainMenu extends JPanel{
	
	//constants
	public static final Color backgroundColor = Color.BLACK;
	public static final Color titleColor = new Color(66, 176, 244, 200);
	public static final Color strColor = Color.WHITE;
	public static final Color highlighter = new Color(219, 156, 48);
	public static final Font optionsFont = new Font("Press Start 2P", Font.BOLD,45);
	public static final Font titleFont = new Font("Jumpman",Font.BOLD, 210);
	private final int GRID_HEIGHT = 6;
	private final int GRID_WIDTH = 1;
	
	//variables
	private JLabel titleFirstPart, titleSecPart;
	private JLabel play, options, highscores, quit;
	private GUIPanelManager guiManager;
	private int currentOption; // acts like a pointer, it points out to the current option
	
	//constructor - each component is created and added to panel
	public MainMenu(GUIPanelManager guiManager){
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		//title is divided into two parts because otherwise it doesn't fits in a single grid cell
		titleFirstPart = new JLabel("donkey");
		titleFirstPart.setForeground(titleColor);
		titleFirstPart.setFont(titleFont);
		titleFirstPart.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleFirstPart);
		
		titleSecPart= new JLabel("kong");
		titleSecPart.setForeground(titleColor);
		titleSecPart.setFont(titleFont);
		titleSecPart.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleSecPart);

		play = new JLabel("PLAY");
		play.setForeground(highlighter);
		play.setFont(optionsFont);
		play.setHorizontalAlignment(SwingConstants.CENTER);
		add(play);

		options= new JLabel("OPTIONS");
		options.setForeground(strColor);
		options.setFont(optionsFont);
		options.setHorizontalAlignment(SwingConstants.CENTER);
		add(options);
		
		highscores= new JLabel("HIGH SCORES");
		highscores.setForeground(strColor);
		highscores.setFont(optionsFont);
		highscores.setHorizontalAlignment(SwingConstants.CENTER);
		add(highscores);
		
		quit= new JLabel("QUIT");
		quit.setForeground(strColor);
		quit.setFont(optionsFont);
		quit.setHorizontalAlignment(SwingConstants.CENTER);
		add(quit);
	
		//set layout and size of the panel
		//setPreferredSize(new Dimension(750,750));
		setBackground(backgroundColor);
		setLayout(new GridLayout(GRID_HEIGHT,GRID_WIDTH));	
	}

	public void initializeKeyBindings(){
		InputMap inputMapPause = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMapPause = this.getActionMap();
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "enterPressed");
		actionMapPause.put("enterPressed", new KeyHandler("enter"));
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upPressed");
		actionMapPause.put("upPressed", new KeyHandler("up"));
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downPressed");
		actionMapPause.put("downPressed", new KeyHandler("down"));
		
	}
	
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(name == "enter"){
				if(currentOption == 0) {
					System.out.println("Play Game pressed");
					guiManager.setLevelSelectionPanelVisible();
				}
				
				else if (currentOption == 1) {
					System.out.println("Options pressed");
					guiManager.setOptionsPanelVisible();
				}
				
				else if(currentOption == 2) {
					System.out.println("Highscores pressed");
					guiManager.setHighScoresPanelVisible();
				}
				
				else if(currentOption == 3) {
					System.out.println("Quit pressed");
					System.exit(0);
				}
				
			}
			else if(name == "up") {
				currentOption--;
	        	
	        	if(currentOption == 0) {
	        		options.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		highscores.setForeground(strColor);
	     			options.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			quit.setForeground(strColor);
	     			highscores.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			play.setForeground(strColor);
	     			quit.setForeground(highlighter);
		     	}
	     		
	        	//check bounds
        		if(currentOption < 0) {
        			currentOption = 3;
        			play.setForeground(strColor);
        			quit.setForeground(highlighter);
        		}
			}
			
			else if(name == "down") {
				currentOption++;
	        	
	        	if(currentOption == 0) {
	        		quit.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
	        		
	        	else if(currentOption == 1) {
	        		play.setForeground(strColor);
	        		options.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			options.setForeground(strColor);
	     			highscores.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			highscores.setForeground(strColor);
	     			quit.setForeground(highlighter);
	     		}
	        	
	        	//check bounds
	        	if(currentOption > 3) {
	        		currentOption = 0;
	        		quit.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
			}	
		}
	}	
}
	
	

