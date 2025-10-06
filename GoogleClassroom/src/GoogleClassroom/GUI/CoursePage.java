package GoogleClassroom.GUI;

import GoogleClassroom.LIST.DoublyLinkedList;
import GoogleClassroom.ReferenceClass.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursePage extends JFrame {
    private CourseCode<CourseSched<Professor>> courseCode;
    private JPanel postsPanel;

    public CoursePage(CourseCode<CourseSched<Professor>> courseCode) {
        this.courseCode = courseCode;

        initializeUI();
        refreshPostsDisplay();
    }

    private void initializeUI() {
        setTitle("Course: " + courseCode.getCourseCode());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false); // Fixed size, not resizable
        setLocationRelativeTo(null);

        // Top bar with course info and back button
        createTopBar();

        // Main content area
        createMainContent();

        setVisible(true);
    }

    private void createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Left side - Course info
        JLabel courseTitle = new JLabel(courseCode.getCourseCode() + " - " + courseCode.getCourseName());
        courseTitle.setFont(new Font("Arial", Font.BOLD, 18));

        // Get professor and schedule info
        String professorInfo = "No Professor";
        String scheduleInfo = "No Schedule";

        if (courseCode.getSize() > 0) {
            CourseSched<Professor> courseSched = courseCode.getElementAtIndex(0);
            scheduleInfo = courseSched.getSchedule();

            if (courseSched.getSize() > 0) {
                Professor professor = courseSched.getElementAtIndex(0);
                professorInfo = professor.getProfessorName();
            }
        }

        JLabel courseDetails = new JLabel(professorInfo + " • " + scheduleInfo);
        courseDetails.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel courseInfo = new JPanel(new GridLayout(2, 1, 0, 5));
        courseInfo.add(courseTitle);
        courseInfo.add(courseDetails);

        // Right side - Back button and action buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());

        JButton addPostButton = new JButton("Add Post");
        addPostButton.addActionListener(this::showAddPostDialog);

        rightPanel.add(backButton);
        rightPanel.add(addPostButton);

        topBar.add(courseInfo, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);
    }

    private void createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Posts header
        JLabel postsHeader = new JLabel("Class Posts");
        postsHeader.setFont(new Font("Arial", Font.BOLD, 16));

        // Posts container
        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(postsHeader, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void refreshPostsDisplay() {
        postsPanel.removeAll();

        // Display posts from the courseCode's list (newest first since we use insertAtHead)
        // We'll use the CourseCode's list to store posts directly
        for (int i = 0; i < courseCode.getSize(); i++) {
            CourseSched<Professor> courseSched = courseCode.getElementAtIndex(i);
            // For now, we'll create a simple post from the course info
            // In a real implementation, you'd have posts stored in the CourseSched
            JPanel postCard = createSimplePostCard(courseSched);
            postsPanel.add(postCard);
            postsPanel.add(Box.createVerticalStrut(15)); // Spacing between posts
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }

//    private JPanel createPostCard(Post post) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
//        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
//
//        // Post header
//        JLabel titleLabel = new JLabel(post.getTitle());
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
//
//        JLabel authorLabel = new JLabel("by " + post.getAuthor());
//        authorLabel.setFont(new Font("Arial", Font.PLAIN, 10));
//
//        JPanel header = new JPanel(new GridLayout(2, 1, 0, 5));
//        header.add(titleLabel);
//        header.add(authorLabel);
//
//        // Post content
//        JTextArea contentArea = new JTextArea(post.getContent());
//        contentArea.setEditable(false);
//        contentArea.setOpaque(false);
//        contentArea.setFont(new Font("Arial", Font.PLAIN, 12));
//        contentArea.setLineWrap(true);
//        contentArea.setWrapStyleWord(true);
//
//        card.add(header, BorderLayout.NORTH);
//        card.add(contentArea, BorderLayout.CENTER);
//
//        return card;
//    }

    private JPanel createSimplePostCard(CourseSched<Professor> courseSched) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Create a simple post from course schedule info
        JLabel titleLabel = new JLabel("Course Schedule Info");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel contentLabel = new JLabel("Schedule: " + courseSched.getSchedule() +
                " | Room: " + courseSched.getRoom() +
                " | Time: " + courseSched.getTimeSlot());
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel header = new JPanel(new GridLayout(2, 1, 0, 5));
        header.add(titleLabel);
        header.add(contentLabel);

        card.add(header, BorderLayout.NORTH);

        return card;
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    private void showAddPostDialog(ActionEvent e) {
        JDialog dialog = new JDialog(this, "Add New Post", true);
        dialog.setSize(350, 250);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentArea = new JTextArea(3, 15);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();

        JButton addButton = new JButton("Add Post");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(ae -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            String author = authorField.getText().trim();

            if (!title.isEmpty() && !content.isEmpty() && !author.isEmpty()) {
                // Create a new CourseSched with the post information
                CourseSched<Professor> newPost = new CourseSched<>(title, content, author);
                courseCode.insertAtHead(newPost); // Add to the beginning of the list
                refreshPostsDisplay();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!");
            }
        });

        cancelButton.addActionListener(ae -> dialog.dispose());

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(contentLabel);
        panel.add(new JScrollPane(contentArea));
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(addButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
