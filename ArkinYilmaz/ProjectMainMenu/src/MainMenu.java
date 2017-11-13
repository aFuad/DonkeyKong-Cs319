import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * Main menu panel is the first class which first interaction with the user stars.
 * It contains a game title and a few options to start game,to go options, to view high scores,etc..
 * 
 */


public class MainMenu extends JPanel implements ActionListener{
	
	//constants
	private final int GRID_HEIGHT = 6;
	private final int GRID_WIDTH = 1;
	private final Color backgroundColor = Color.BLACK;
	private final Color titleColor = new Color(66, 176, 244, 200);
	private final Color strColor = Color.WHITE;
	private final Color highlighter = new Color(219, 156, 48);
	private final Font optionsFont = new Font("Press Start 2P", Font.BOLD,35);
	private final Font titleFont = new Font("Jumpman",Font.BOLD, 175);
	
	//variables
	private JLabel titleFirstPart, titleSecPart;
	private JButton playGameButton, optionsButton, scoresButton, quitButton;
	private int currentOption = 0; // acts like a pointer, it points out to the current option
	
	//constructor - each component is created and added to panel
	public MainMenu(){
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
		
		playGameButton = new JButton("PLAY GAME");
		playGameButton.setBackground(backgroundColor);
		playGameButton.setForeground(highlighter);
		playGameButton.setBorderPainted(false);
		playGameButton.setContentAreaFilled(false);
		playGameButton.setFocusPainted(false);
		playGameButton.setFont(optionsFont);
		add(playGameButton);
		
		optionsButton = new JButton("OPTIONS");
		optionsButton.setBackground(backgroundColor);
		optionsButton.setForeground(strColor);
		optionsButton.setBorderPainted(false);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setFocusPainted(false);
		optionsButton.setFont(optionsFont);
		add(optionsButton);
		
		scoresButton = new JButton("HIGHSCORES");
		scoresButton.setBackground(backgroundColor);
		scoresButton.setForeground(strColor);
		scoresButton.setBorderPainted(false);
		scoresButton.setContentAreaFilled(false);
		scoresButton.setFocusPainted(false);
		scoresButton.setFont(optionsFont);
		add(scoresButton);
		
		quitButton = new JButton("QUIT");
		quitButton.setBackground(backgroundColor);
		quitButton.setForeground(strColor);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.setFont(optionsFont);
		add(quitButton);
		
		playGameButton.addActionListener(this);
		optionsButton.addActionListener(this);
		scoresButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		// handle button operations with keyboard inputs
		playGameButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					//play game entered
					if(currentOption == 0)
						playGameButton.doClick();
					
					//options entered
		            else if(currentOption == 1)
		            	optionsButton.doClick();
					
					//high scores entered
		            else if (currentOption == 2)
		            	scoresButton.doClick();
					
					//exit entered
		            else if(currentOption == 3)
		            	quitButton.doClick();
		        }
				
		        else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
		        	currentOption++;
		        	
		        	if(currentOption == 0) {
		        		quitButton.setForeground(strColor);
		        		playGameButton.setForeground(highlighter);
		        	}
		        		
		        	else if(currentOption == 1) {
		        		playGameButton.setForeground(strColor);
		        		optionsButton.setForeground(highlighter);
		     		}
		        	
		     		else if(currentOption == 2) {
		     			optionsButton.setForeground(strColor);
		     			scoresButton.setForeground(highlighter);
		     		}
		        	
		     		else if(currentOption == 3){
		     			scoresButton.setForeground(strColor);
		     			quitButton.setForeground(highlighter);
		     		}
		        	
		        	//check bounds
		        	if(currentOption > 3) {
		        		currentOption = 0;
		        		quitButton.setForeground(strColor);
		        		playGameButton.setForeground(highlighter);
		        	}
		        	 
		         }
		         
		         else if(e.getKeyCode()==KeyEvent.VK_UP) {
		        	currentOption--;
		        	
		        	if(currentOption == 0) {
		        		optionsButton.setForeground(strColor);
		        		playGameButton.setForeground(highlighter);
		        	}
		        	
		        	else if(currentOption == 1) {
		        		scoresButton.setForeground(strColor);
		     			optionsButton.setForeground(highlighter);
		     		}
		        	
		     		else if(currentOption == 2) {
		     			quitButton.setForeground(strColor);
		     			scoresButton.setForeground(highlighter);
		     		}
		        	
		     		else if(currentOption == 3){
		     			playGameButton.setForeground(strColor);
		     			quitButton.setForeground(highlighter);
			     	}
		     		
		        	//check bounds
	        		if(currentOption < 0) {
	        			currentOption = 3;
	        			playGameButton.setForeground(strColor);
	        			quitButton.setForeground(highlighter);
	        		}
		         }
		    }
		});
	
		//set layout and size of the panel
		setPreferredSize(new Dimension(750,750));
		setBackground(backgroundColor);
		setLayout(new GridLayout(GRID_HEIGHT,GRID_WIDTH));	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource(); 

		if (source == playGameButton) 
			System.out.println("Play Game pressed");		
		else if (source == optionsButton)
			System.out.println("Options pressed");
		else if (source == scoresButton)
			System.out.println("Highscores pressed");
		else if (source == quitButton)
			System.exit(0);
	}
		
}
	
	

