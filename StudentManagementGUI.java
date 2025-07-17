import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    public Object[] toObjectArray() {
        return new Object[]{rollNumber, name, grade};
    }
}

public class StudentManagementGUI extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private final String FILE_NAME = "students.dat";

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Table setup
        String[] columns = {"Roll Number", "Name", "Grade"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Button Panel
        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton refreshButton = new JButton("Refresh");

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        panel.add(refreshButton);
        add(panel, BorderLayout.SOUTH);

        // Load existing data
        loadStudentsFromFile();
        refreshTable();

        // Button Actions
        addButton.addActionListener(e -> addStudentDialog());
        deleteButton.addActionListener(e -> deleteStudentDialog());
        searchButton.addActionListener(e -> searchStudentDialog());
        refreshButton.addActionListener(e -> refreshTable());
    }

    private void addStudentDialog() {
        JTextField rollField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();

        Object[] fields = {
            "Roll Number:", rollField,
            "Name:", nameField,
            "Grade:", gradeField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int roll = Integer.parseInt(rollField.getText().trim());
                String name = nameField.getText().trim();
                String grade = gradeField.getText().trim();

                if (name.isEmpty() || grade.isEmpty()) {
                    showMessage("Name and Grade can't be empty!");
                    return;
                }

                for (Student s : students) {
                    if (s.getRollNumber() == roll) {
                        showMessage("Student with this roll number already exists!");
                        return;
                    }
                }

                students.add(new Student(name, roll, grade));
                saveStudentsToFile();
                refreshTable();
                showMessage("Student added successfully!");
            } catch (NumberFormatException e) {
                showMessage("Invalid Roll Number!");
            }
        }
    }

    private void deleteStudentDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter Roll Number to Delete:");
        try {
            int roll = Integer.parseInt(input.trim());
            boolean removed = students.removeIf(s -> s.getRollNumber() == roll);
            if (removed) {
                saveStudentsToFile();
                refreshTable();
                showMessage("Student removed.");
            } else {
                showMessage("Student not found.");
            }
        } catch (Exception e) {
            showMessage("Invalid input.");
        }
    }

    private void searchStudentDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter Roll Number to Search:");
        try {
            int roll = Integer.parseInt(input.trim());
            for (Student s : students) {
                if (s.getRollNumber() == roll) {
                    showMessage("Found: " + s.getName() + " (" + s.getGrade() + ")");
                    return;
                }
            }
            showMessage("Student not found.");
        } catch (Exception e) {
            showMessage("Invalid input.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(s.toObjectArray());
        }
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            showMessage("Error saving data.");
        }
    }

    private void loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI().setVisible(true);
        });
    }
}
