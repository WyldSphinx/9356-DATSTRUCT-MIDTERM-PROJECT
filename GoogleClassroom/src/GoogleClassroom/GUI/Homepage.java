package GoogleClassroom.GUI;

import GoogleClassroom.LIST.DoublyLinkedList;
import GoogleClassroom.ReferenceClass.CourseCode;
import GoogleClassroom.ReferenceClass.CourseSched;
import GoogleClassroom.ReferenceClass.Professor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Homepage extends JFrame {
    private DoublyLinkedList<CourseCode<CourseSched<Professor>>> coursesList;
    private JPanel cardPanel;

    public Homepage(DoublyLinkedList<CourseCode<CourseSched<Professor>>> coursesList) {
        this.coursesList = coursesList;
        setTitle("Home Page");
        setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,600);

        // top bar
        JPanel topBar = new JPanel(new BorderLayout());

        // Left side - My Courses label
        JLabel titleLabel = new JLabel("My Courses");
        topBar.add(titleLabel, BorderLayout.WEST);

        // Right side - Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Method 1: Using Unicode symbols (easiest)
        JButton addCourseButton = new JButton("+"); // Plus icon
        JButton removeCourseButton = new JButton("−"); // Minus icon (en-dash)
        JButton updateCourseButton = new JButton("↻"); // Update/refresh icon

        buttonPanel.add(addCourseButton);
        buttonPanel.add(removeCourseButton);
        buttonPanel.add(updateCourseButton);
        topBar.add(buttonPanel, BorderLayout.EAST);

        //add a action event GUI
        addCourseButton.addActionListener(e -> showAddCourseDialog());
        removeCourseButton.addActionListener(e -> showRemoveCourseDialog());
        updateCourseButton.addActionListener(e -> showUpdateCourseDialog());
        add(topBar, BorderLayout.NORTH);


        // card container
        cardPanel = new JPanel(new GridLayout(0,3,15,15));

        // Create cards from the courses list
        for(int i = 0; i < coursesList.getSize(); i++ ) {
            final CourseCode<CourseSched<Professor>> courseCode = coursesList.getElementAtIndex(i);

            JPanel card = new JPanel(new BorderLayout());
            card.setPreferredSize(new Dimension(200,120));
            card.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));

            // Use course data from the CourseCode object
            JLabel courseLabel = new JLabel(courseCode.getCourseCode(), SwingConstants.LEFT);
            JLabel courseNameLabel = new JLabel(courseCode.getCourseName(), SwingConstants.LEFT);

            // Get professor and schedule info from the CourseSched list
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

            JLabel professorLabel = new JLabel(professorInfo, SwingConstants.LEFT);
            JLabel scheduleLabel = new JLabel(scheduleInfo, SwingConstants.LEFT);

            // Create a panel to stack the labels vertically in the top 1/4
            JPanel labelsPanel = new JPanel(new GridLayout(3, 1, 10, 6));
            labelsPanel.setPreferredSize(new Dimension(200, 50));
            labelsPanel.add(courseLabel);
            labelsPanel.add(professorLabel);
            labelsPanel.add(scheduleLabel);

            card.add(labelsPanel, BorderLayout.NORTH);

            // Add click listener to the entire card
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Open CoursePage with the specific course and its posts
                    new CoursePage(courseCode);
                }
            });

            // Make the card appear clickable
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));

            cardPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        add(scrollPane,BorderLayout.CENTER);

        setVisible(true);
    }

    private void showAddCourseDialog() {
        JDialog dialog = new JDialog(this, "Add New Course", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel courseCodeLabel = new JLabel("Course Code:");
        JTextField courseCodeField = new JTextField();

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField();

        JLabel professorLabel = new JLabel("Professor:");
        JTextField professorField = new JTextField();

        JLabel scheduleLabel = new JLabel("Schedule:");
        JTextField scheduleField = new JTextField();

        JButton addButton = new JButton("Add Course");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            String courseCode = courseCodeField.getText().trim();
            String courseName = courseNameField.getText().trim();
            String professor = professorField.getText().trim();
            String schedule = scheduleField.getText().trim();

            if (!courseCode.isEmpty() && !courseName.isEmpty() && !professor.isEmpty() && !schedule.isEmpty()) {
                // Create new course using reference classes
                CourseCode<CourseSched<Professor>> newCourseCode = new CourseCode<>(courseCode, courseName);
                Professor newProfessor = new Professor(professor, "ID001", "Department");
                CourseSched<Professor> newSchedule = new CourseSched<>(schedule, "Room TBD", "Time TBD");
                newSchedule.insert(newProfessor);
                newCourseCode.insert(newSchedule);

                coursesList.insert(newCourseCode);
                refreshCourseCards();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Course added successfully!");
            } else {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(courseCodeLabel);
        panel.add(courseCodeField);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(professorLabel);
        panel.add(professorField);
        panel.add(scheduleLabel);
        panel.add(scheduleField);
        panel.add(addButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showRemoveCourseDialog() {
        if (coursesList.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "No courses to remove!");
            return;
        }

        String[] courseOptions = new String[coursesList.getSize()];
        for (int i = 0; i < coursesList.getSize(); i++) {
            CourseCode<CourseSched<Professor>> courseCode = coursesList.getElementAtIndex(i);
            courseOptions[i] = courseCode.getCourseCode() + " - " + courseCode.getCourseName();
        }

        String selectedCourse = (String) JOptionPane.showInputDialog(
                this,
                "Select course to remove:",
                "Remove Course",
                JOptionPane.QUESTION_MESSAGE,
                null,
                courseOptions,
                courseOptions[0]
        );

        if (selectedCourse != null) {
            int selectedIndex = -1;
            for (int i = 0; i < courseOptions.length; i++) {
                if (courseOptions[i].equals(selectedCourse)) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex != -1) {
                coursesList.deleteAtIndex(selectedIndex);
                refreshCourseCards();
                JOptionPane.showMessageDialog(this, "Course removed successfully!");
            }
        }
    }

    private void showUpdateCourseDialog() {
        if (coursesList.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "No courses to update!");
            return;
        }

        String[] courseOptions = new String[coursesList.getSize()];
        for (int i = 0; i < coursesList.getSize(); i++) {
            CourseCode<CourseSched<Professor>> courseCode = coursesList.getElementAtIndex(i);
            courseOptions[i] = courseCode.getCourseCode() + " - " + courseCode.getCourseName();
        }

        String selectedCourse = (String) JOptionPane.showInputDialog(
                this,
                "Select course to update:",
                "Update Course",
                JOptionPane.QUESTION_MESSAGE,
                null,
                courseOptions,
                courseOptions[0]
        );

        if (selectedCourse != null) {
            int selectedIndex = -1;
            for (int i = 0; i < courseOptions.length; i++) {
                if (courseOptions[i].equals(selectedCourse)) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex != -1) {
                CourseCode<CourseSched<Professor>> courseToUpdate = coursesList.getElementAtIndex(selectedIndex);
                showUpdateCourseForm(courseToUpdate, selectedIndex);
            }
        }
    }

    private void showUpdateCourseForm(CourseCode<CourseSched<Professor>> courseCode, int index) {
        JDialog dialog = new JDialog(this, "Update Course", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel courseCodeLabel = new JLabel("Course Code:");
        JTextField courseCodeField = new JTextField(courseCode.getCourseCode());

        JLabel courseNameLabel = new JLabel("Course Name:");
        JTextField courseNameField = new JTextField(courseCode.getCourseName());

        JLabel professorLabel = new JLabel("Professor:");
        JTextField professorField = new JTextField("Professor Name");

        JLabel scheduleLabel = new JLabel("Schedule:");
        JTextField scheduleField = new JTextField("Schedule Info");

        JButton updateButton = new JButton("Update Course");
        JButton cancelButton = new JButton("Cancel");

        updateButton.addActionListener(e -> {
            String newCourseCode = courseCodeField.getText().trim();
            String newCourseName = courseNameField.getText().trim();
            String professor = professorField.getText().trim();
            String schedule = scheduleField.getText().trim();

            if (!newCourseCode.isEmpty() && !newCourseName.isEmpty() && !professor.isEmpty() && !schedule.isEmpty()) {
                courseCode.setCourseCode(newCourseCode);
                courseCode.setCourseName(newCourseName);
                refreshCourseCards();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Course updated successfully!");
            } else {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        panel.add(courseCodeLabel);
        panel.add(courseCodeField);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(professorLabel);
        panel.add(professorField);
        panel.add(scheduleLabel);
        panel.add(scheduleField);
        panel.add(updateButton);
        panel.add(cancelButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void refreshCourseCards() {
        cardPanel.removeAll();

        // Recreate cards from the updated courses list
        for(int i = 0; i < coursesList.getSize(); i++ ) {
            final CourseCode<CourseSched<Professor>> courseCode = coursesList.getElementAtIndex(i);

            JPanel card = new JPanel(new BorderLayout());
            card.setPreferredSize(new Dimension(200,120));
            card.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));

            // Use course data from the CourseCode object
            JLabel courseLabel = new JLabel(courseCode.getCourseCode(), SwingConstants.LEFT);
            JLabel courseNameLabel = new JLabel(courseCode.getCourseName(), SwingConstants.LEFT);

            // Get professor and schedule info from the CourseSched list
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

            JLabel professorLabel = new JLabel(professorInfo, SwingConstants.LEFT);
            JLabel scheduleLabel = new JLabel(scheduleInfo, SwingConstants.LEFT);

            // Create a panel to stack the labels vertically in the top 1/4
            JPanel labelsPanel = new JPanel(new GridLayout(3, 1, 10, 6));
            labelsPanel.setPreferredSize(new Dimension(200, 50));
            labelsPanel.add(courseLabel);
            labelsPanel.add(professorLabel);
            labelsPanel.add(scheduleLabel);

            card.add(labelsPanel, BorderLayout.NORTH);

            // Add click listener to the entire card
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Open CoursePage with the specific course and its posts
                    new CoursePage(courseCode);
                }
            });

            // Make the card appear clickable
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));

            cardPanel.add(card);
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DoublyLinkedList<CourseCode<CourseSched<Professor>>> coursesList = new DoublyLinkedList<>();

            // Create sample courses using reference classes
            CourseCode<CourseSched<Professor>> cs101 = new CourseCode<>("CS101", "Introduction to Programming");
            CourseCode<CourseSched<Professor>> math201 = new CourseCode<>("MATH201", "Calculus II");
            CourseCode<CourseSched<Professor>> eng101 = new CourseCode<>("ENG101", "English Composition");
            CourseCode<CourseSched<Professor>> phys101 = new CourseCode<>("PHYS101", "Physics I");

            // Create professors
            Professor profSmith = new Professor("Dr. Smith", "SMITH001", "Computer Science");
            Professor profJohnson = new Professor("Prof. Johnson", "JOHN001", "Mathematics");
            Professor profWilliams = new Professor("Dr. Williams", "WILL001", "English");
            Professor profBrown = new Professor("Prof. Brown", "BROW001", "Physics");

            // Create course schedules
            CourseSched<Professor> cs101Schedule = new CourseSched<>("MWF 9:00-10:00", "Room 101", "Morning");
            CourseSched<Professor> math201Schedule = new CourseSched<>("TTH 11:00-12:30", "Room 205", "Late Morning");
            CourseSched<Professor> eng101Schedule = new CourseSched<>("MWF 2:00-3:00", "Room 301", "Afternoon");
            CourseSched<Professor> phys101Schedule = new CourseSched<>("TTH 1:00-2:30", "Lab 401", "Afternoon");

            // Add professors to schedules
            cs101Schedule.insert(profSmith);
            math201Schedule.insert(profJohnson);
            eng101Schedule.insert(profWilliams);
            phys101Schedule.insert(profBrown);

            // Add schedules to courses
            cs101.insert(cs101Schedule);
            math201.insert(math201Schedule);
            eng101.insert(eng101Schedule);
            phys101.insert(phys101Schedule);

            // Add courses to the main list
            coursesList.insert(cs101);
            coursesList.insert(math201);
            coursesList.insert(eng101);
            coursesList.insert(phys101);

            new Homepage(coursesList);
        });
    }
}
