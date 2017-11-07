package source;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame{
	
	protected static final int WIDTH = 750;
	protected static final int HEIGHT = 750;
	private static final String TITLE = "Donkey Kong";
	private int level = 1;
	
	GamePanel gamePanel;
	MainMenu mainMenu;
	
	public MainFrame() throws FileNotFoundException{
		setTitle(TITLE);
		setBounds(600, 100, WIDTH, HEIGHT);
		setResizable(false);
		//gamePanel = new GamePanel(level);
		//gamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(gamePanel);
		//gamePanel.setLayout(null);
		mainMenu = new MainMenu();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainMenu);
		
	}
}
