package DisplayScreen;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Listeners.LoginButtonListener;
import Objects.User;

import javax.swing.JButton;

public class LoginPanel extends JPanel {
	private JTextField loginTextField;
	private JTextField passwordTextField;
	private MainWindow mainWindow;
	public String getLogin() {
		return loginTextField.getText();
	}
	public String getPassword() {
		return passwordTextField.getText();
	}
	public void setConnectionFailed() {
		JOptionPane.showMessageDialog(null, "Connection failed");
	}
	public void setConnectionSuccessful() {
		JOptionPane.showMessageDialog(null, "Connection successful");
		mainWindow.setUser(User.getLogin(), User.isPremium());
		mainWindow.setDisplayedPanel(-1);
	}
	/**
	 * Create the panel.
	 */
	public LoginPanel(MainWindow mainframe) {
		mainWindow = mainframe;
		setBounds(150, 0, 1000, 600);
		setLayout(null);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setBounds(400, 200, 58, 30);
		add(loginLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(400, 239, 58, 30);
		add(passwordLabel);
		
		loginTextField = new JTextField();
		loginTextField.setBounds(475, 205, 157, 20);
		add(loginTextField);
		loginTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(475, 244, 157, 20);
		add(passwordTextField);
		
		JButton submitButton = new JButton("Login");
		submitButton.setBounds(475, 290, 89, 23);
		add(submitButton);
		
		LoginButtonListener lbl = new LoginButtonListener(this);
		submitButton.addMouseListener(lbl);
	}
}
