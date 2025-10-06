package GoogleClassroom.GUI.LoginRegister;
import GoogleClassroom.GUI.*;
import GoogleClassroom.LIST.DoublyLinkedList;
import GoogleClassroom.ReferenceClass.CourseCode;
import GoogleClassroom.ReferenceClass.CourseSched;
import GoogleClassroom.ReferenceClass.Professor;
import GoogleClassroom.ReferenceClass.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;

class LoginRegisterGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Login
    private JTextField loginIdField;
    private JPasswordField loginPasswordField;

    // Register
    private JTextField registerIdField;
    private JPasswordField registerPasswordField;
    private JTextField birthdayField;

    // User database using DoublyLinkedList
    private DoublyLinkedList<User> userDatabase;

    private static final Pattern ID_PATTERN = Pattern.compile("^\\d{7}$");

    public LoginRegisterGUI() {
        setTitle("Google Classroom - Login");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize user database
        userDatabase = new DoublyLinkedList<>();

        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create both panels
        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createRegisterPanel(), "REGISTER");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(66, 133, 244));
        headerPanel.setPreferredSize(new Dimension(400, 120));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("Google Classroom");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Subject Monitoring System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createVerticalGlue());

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // ID Number
        JLabel idLabel = new JLabel("ID Number");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        loginIdField = new JTextField();
        loginIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        loginIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel idHintLabel = new JLabel("Must be 6 digits");
        idHintLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        idHintLabel.setForeground(Color.GRAY);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        loginPasswordField = new JPasswordField();
        loginPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        loginPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginButton.setBackground(new Color(66, 133, 244));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> handleLogin());

        // Switch to register
        JButton switchToRegisterButton = new JButton("Need an account? Register");
        switchToRegisterButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        switchToRegisterButton.setBackground(Color.WHITE);
        switchToRegisterButton.setForeground(new Color(66, 133, 244));
        switchToRegisterButton.setFont(new Font("Arial", Font.PLAIN, 12));
        switchToRegisterButton.setFocusPainted(false);
        switchToRegisterButton.setBorderPainted(false);
        switchToRegisterButton.addActionListener(e -> cardLayout.show(mainPanel, "REGISTER"));

        formPanel.add(idLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(loginIdField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        formPanel.add(idHintLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(passwordLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(loginPasswordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(loginButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(switchToRegisterButton);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(66, 133, 244));
        headerPanel.setPreferredSize(new Dimension(400, 120));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Register for Google Classroom");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createVerticalGlue());

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // ID Number
        JLabel idLabel = new JLabel("ID Number");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerIdField = new JTextField();
        registerIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        registerIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel idHintLabel = new JLabel("Must be 7 digits");
        idHintLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        idHintLabel.setForeground(Color.GRAY);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerPasswordField = new JPasswordField();
        registerPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        registerPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Birthday
        JLabel birthdayLabel = new JLabel("Birthday (YYYY-MM-DD)");
        birthdayLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        birthdayField = new JTextField();
        birthdayField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        birthdayField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        registerButton.setBackground(new Color(66, 133, 244));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> handleRegister());

        // Switch to login
        JButton switchToLoginButton = new JButton("Already have an account? Login");
        switchToLoginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        switchToLoginButton.setBackground(Color.WHITE);
        switchToLoginButton.setForeground(new Color(66, 133, 244));
        switchToLoginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        switchToLoginButton.setFocusPainted(false);
        switchToLoginButton.setBorderPainted(false);
        switchToLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));

        formPanel.add(idLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(registerIdField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        formPanel.add(idHintLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(passwordLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(registerPasswordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(birthdayLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(birthdayField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formPanel.add(registerButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(switchToLoginButton);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private void handleLogin() {
        String idNumber = loginIdField.getText().trim();
        String password = new String(loginPasswordField.getPassword());

        if (!ID_PATTERN.matcher(idNumber).matches()) {
            JOptionPane.showMessageDialog(this,
                    "ID Number must be exactly 6 digits!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Password cannot be empty!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Authentication
        User foundUser = findUserById(idNumber);
        if (foundUser == null) {
            JOptionPane.showMessageDialog(this,
                    "User ID not found! Please register first.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!foundUser.authenticate(password)) {
            JOptionPane.showMessageDialog(this,
                    "Invalid password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Login successful!\nWelcome, User " + idNumber,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        // Clear fields and redirect to homepage
        loginIdField.setText("");
        loginPasswordField.setText("");
        redirectToHomepage();
    }

    private User findUserById(String idNumber) {
        for (int i = 0; i < userDatabase.getSize(); i++) {
            User user = userDatabase.getElementAtIndex(i);
            if (user.getIdNumber().equals(idNumber)) {
                return user;
            }
        }
        return null;
    }

    private boolean userExists(String idNumber) {
        return findUserById(idNumber) != null;
    }

    private void redirectToHomepage() {
        DoublyLinkedList<CourseCode<CourseSched<Professor>>> coursesList = new DoublyLinkedList<>();

        // Open the homepage with empty list
        SwingUtilities.invokeLater(() -> {
            new Homepage(coursesList);
            this.dispose(); // Close
        });
    }

    private void handleRegister() {
        String idNumber = registerIdField.getText().trim();
        String password = new String(registerPasswordField.getPassword());
        String birthday = birthdayField.getText().trim();

        if (!ID_PATTERN.matcher(idNumber).matches()) {
            JOptionPane.showMessageDialog(this,
                    "ID Number must be exactly 6 digits!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Password cannot be empty!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (birthday.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Birthday cannot be empty!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if user already exists
        if (userExists(idNumber)) {
            JOptionPane.showMessageDialog(this,
                    "User ID already exists! Please use a different ID or login.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create new user and add to database
        User newUser = new User(idNumber, password, birthday);
        userDatabase.insert(newUser);

        JOptionPane.showMessageDialog(this,
                "Registration successful!\nID: " + idNumber + "\nYou can now login.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        // Clear fields and switch to login
        registerIdField.setText("");
        registerPasswordField.setText("");
        birthdayField.setText("");
        cardLayout.show(mainPanel, "LOGIN");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginRegisterGUI());
    }
}