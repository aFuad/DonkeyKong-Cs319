import javax.swing.JFrame;

public class MainFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Donkey Kong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainMenu());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

}
