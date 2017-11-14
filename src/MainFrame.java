package source;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/*
 * This class is not necessary anymore. We are using it to test GamePanel. When we establish GUIManager interection, it will be deleted.
 */
public class MainFrame extends JFrame{
	
	protected static final int WIDTH = 1005;
	protected static final int HEIGHT = 1030;
	private static final String TITLE = "Donkey Kong";
	private int level = 1;
	
	GamePanel gamePanel;
	MainMenu mainMenu;
	
	public MainFrame() throws FileNotFoundException{
		setTitle(TITLE);
		setBounds(500, 0, WIDTH, HEIGHT);
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
