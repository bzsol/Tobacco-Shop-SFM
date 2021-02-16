import javax.swing.*;

public class Login extends JPanel {
    public static void main(String[] args) {


        // Create the Panel and the Frame too
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        // The title of the GUI page
        frame.setTitle("Login Panel");
        // The size of the Panel
        frame.setSize(290, 200);
        // The close operation is the exit button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(panel);
        // Center the position to the center of the screen
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);


        // The username and the password labels
        JLabel Username = new JLabel("Username:");
        JLabel Password = new JLabel("Password:");
        JLabel success = new JLabel("You logged in successfully!!");

        // The text field and the password field
        JTextField userText = new JTextField();
        JPasswordField passText = new JPasswordField();
        JButton button = new JButton("Login");

        // Position the objects
        Username.setBounds(10, 15, 80, 25);
        Password.setBounds(10, 65, 80, 25);
        userText.setBounds(90, 15, 165, 25);
        passText.setBounds(90, 65, 165, 25);
        button.setBounds(100, 110, 100, 25);

        // Add to the panels
        panel.add(Username);
        panel.add(Password);
        panel.add(userText);
        panel.add(passText);
        panel.add(button);


        // This ActionListener wait for your action to get the Password and Username
        button.addActionListener(e -> {
            String username = userText.getText();
            char[] password = passText.getPassword();
            System.out.println(username + "\n" + new String(password));
            if(username.equals("admin") && new String(password).equals("123")){
                System.out.println("You're in");
                panel.add(success);
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }
}
