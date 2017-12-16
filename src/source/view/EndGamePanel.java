package source.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import source.controller.ScoreData;


public class EndGamePanel extends JPanel{
	
	private GUIPanelManager guiManager;
	private ScoreData scoreData;
	private JLabel titleLabel;
	private JTextField textArea;
	private int score;
	
	public EndGamePanel(GUIPanelManager guiManager, int score){
		initializeJTextField();

		this.guiManager = guiManager;
		try {
			scoreData = new ScoreData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.score = score;
		
		titleLabel = new JLabel("ENTER NAME");
		titleLabel.setForeground(MainMenu.strColor);
		titleLabel.setFont(new Font("Press Start 2P", Font.BOLD,60));
		titleLabel.setSize(700, 150);
		titleLabel.setLocation(180, 40);
		add(titleLabel);
		
		setBackground(Color.BLACK);
		setLayout(null);
	}
	
	public void initializeJTextField(){
		textArea = new JTextField();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(MainMenu.strColor);
		textArea.setFont(new Font("Press Start 2P", Font.BOLD,120));
		textArea.setHorizontalAlignment(SwingConstants.CENTER);
		textArea.setBorder(null);
		textArea.setSize(800, 600);
		textArea.setLocation(90, 200);
		
		textArea.setEditable(false);
		add(textArea);
		textArea.requestFocus();
		
		InputMap inputMap = textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = textArea.getActionMap();
		
		inputMap.put(KeyStroke.getKeyStroke("released ENTER"), "enterReleased");
		actionMap.put("enterReleased", new KeyHandler("enter"));
		
		inputMap.put(KeyStroke.getKeyStroke("released BACK_SPACE"), "backSpaceReleased");
		actionMap.put("backSpaceReleased", new KeyHandler("back space"));
		
		inputMap.put(KeyStroke.getKeyStroke("released Q"), "qReleased");
		actionMap.put("qReleased", new KeyHandler("q"));
		
		inputMap.put(KeyStroke.getKeyStroke("released W"), "wReleased");
		actionMap.put("wReleased", new KeyHandler("w"));
		
		inputMap.put(KeyStroke.getKeyStroke("released E"), "eReleased");
		actionMap.put("eReleased", new KeyHandler("e"));
		
		inputMap.put(KeyStroke.getKeyStroke("released R"), "rReleased");
		actionMap.put("rReleased", new KeyHandler("r"));
		
		inputMap.put(KeyStroke.getKeyStroke("released T"), "tReleased");
		actionMap.put("tReleased", new KeyHandler("t"));
		
		inputMap.put(KeyStroke.getKeyStroke("released Y"), "yReleased");
		actionMap.put("yReleased", new KeyHandler("y"));
		
		inputMap.put(KeyStroke.getKeyStroke("released U"), "uReleased");
		actionMap.put("uReleased", new KeyHandler("u"));
		
		inputMap.put(KeyStroke.getKeyStroke("released I"), "iReleased");
		actionMap.put("iReleased", new KeyHandler("i"));
		
		inputMap.put(KeyStroke.getKeyStroke("released O"), "oReleased");
		actionMap.put("oReleased", new KeyHandler("o"));
		
		inputMap.put(KeyStroke.getKeyStroke("released P"), "pReleased");
		actionMap.put("pReleased", new KeyHandler("p"));
		
		inputMap.put(KeyStroke.getKeyStroke("released A"), "aReleased");
		actionMap.put("aReleased", new KeyHandler("a"));
		
		inputMap.put(KeyStroke.getKeyStroke("released S"), "sReleased");
		actionMap.put("sReleased", new KeyHandler("s"));
		
		inputMap.put(KeyStroke.getKeyStroke("released D"), "dReleased");
		actionMap.put("dReleased", new KeyHandler("d"));
		
		inputMap.put(KeyStroke.getKeyStroke("released F"), "fReleased");
		actionMap.put("fReleased", new KeyHandler("f"));
		
		inputMap.put(KeyStroke.getKeyStroke("released G"), "gReleased");
		actionMap.put("gReleased", new KeyHandler("g"));
		
		inputMap.put(KeyStroke.getKeyStroke("released H"), "hReleased");
		actionMap.put("hReleased", new KeyHandler("h"));
		
		inputMap.put(KeyStroke.getKeyStroke("released J"), "jReleased");
		actionMap.put("jReleased", new KeyHandler("j"));
		
		inputMap.put(KeyStroke.getKeyStroke("released K"), "kReleased");
		actionMap.put("kReleased", new KeyHandler("k"));
		
		inputMap.put(KeyStroke.getKeyStroke("released L"), "lReleased");
		actionMap.put("lReleased", new KeyHandler("l"));
		
		inputMap.put(KeyStroke.getKeyStroke("released Z"), "zReleased");
		actionMap.put("zReleased", new KeyHandler("z"));
		
		inputMap.put(KeyStroke.getKeyStroke("released X"), "xReleased");
		actionMap.put("xReleased", new KeyHandler("x"));
		
		inputMap.put(KeyStroke.getKeyStroke("released C"), "cReleased");
		actionMap.put("cReleased", new KeyHandler("c"));
		
		inputMap.put(KeyStroke.getKeyStroke("released V"), "vReleased");
		actionMap.put("vReleased", new KeyHandler("v"));
		
		inputMap.put(KeyStroke.getKeyStroke("released B"), "bReleased");
		actionMap.put("bReleased", new KeyHandler("b"));
		
		inputMap.put(KeyStroke.getKeyStroke("released N"), "nReleased");
		actionMap.put("nReleased", new KeyHandler("n"));
		
		inputMap.put(KeyStroke.getKeyStroke("released M"), "mReleased");
		actionMap.put("mReleased", new KeyHandler("m"));
	}
	
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textArea.getText().length() == 5){
				if(name == "enter"){
					try {
						scoreData.setHighscore(textArea.getText(), score);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					guiManager.setMainMenuPanelVisible();
				}
			}
			
			if(textArea.getText().length() > 0){
				if(name == "back space"){
					textArea.setText(textArea.getText().substring(0, textArea.getText ().length() - 1));
				}
			}
			
			if(textArea.getText().length() < 5){
				if(name == "q"){
					textArea.setText(textArea.getText() + "Q");
				}
				else if(name == "w"){
					textArea.setText(textArea.getText() + "W");
				}
				else if(name == "e"){
					textArea.setText(textArea.getText() + "E");
				}
				else if(name == "r"){
					textArea.setText(textArea.getText() + "R");
				}
				else if(name == "t"){
					textArea.setText(textArea.getText() + "T");
				}
				else if(name == "y"){
					textArea.setText(textArea.getText() + "Y");
				}
				else if(name == "u"){
					textArea.setText(textArea.getText() + "U");
				}
				else if(name == "i"){
					textArea.setText(textArea.getText() + "I");
				}
				else if(name == "o"){
					textArea.setText(textArea.getText() + "O");
				}
				else if(name == "p"){
					textArea.setText(textArea.getText() + "P");
				}
				else if(name == "a"){
					textArea.setText(textArea.getText() + "A");
				}
				else if(name == "s"){
					textArea.setText(textArea.getText() + "S");
				}
				else if(name == "d"){
					textArea.setText(textArea.getText() + "D");
				}
				else if(name == "f"){
					textArea.setText(textArea.getText() + "F");
				}
				else if(name == "g"){
					textArea.setText(textArea.getText() + "G");
				}
				else if(name == "h"){
					textArea.setText(textArea.getText() + "H");
				}
				else if(name == "j"){
					textArea.setText(textArea.getText() + "J");
				}
				else if(name == "k"){
					textArea.setText(textArea.getText() + "K");
				}
				else if(name == "l"){
					textArea.setText(textArea.getText() + "L");
				}
				else if(name == "z"){
					textArea.setText(textArea.getText() + "Z");
				}
				else if(name == "x"){
					textArea.setText(textArea.getText() + "X");
				}
				else if(name == "c"){
					textArea.setText(textArea.getText() + "C");
				}
				else if(name == "v"){
					textArea.setText(textArea.getText() + "V");
				}
				else if(name == "b"){
					textArea.setText(textArea.getText() + "B");
				}
				else if(name == "n"){
					textArea.setText(textArea.getText() + "N");
				}
				else if(name == "m"){
					textArea.setText(textArea.getText() + "M");
				}
			}
		}
	}

}