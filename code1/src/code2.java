import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class PersonalTaskManagerGUI extends JFrame {

    private JLabel taskLabel, timeLabel;
    private JTextField taskField, timeField;
    private JButton addButton, removeButton, clearButton;
    private JList<String> taskList;
    private JScrollPane scrollPane;
    private DefaultListModel<String> listModel;

    public PersonalTaskManagerGUI() {
        setTitle("Personal Task Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        taskLabel = new JLabel("Task:");
        taskField = new JTextField();
        timeLabel = new JLabel("Time (mins):");
        timeField = new JTextField();
        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });
        clearButton = new JButton("Clear All");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAllTasks();
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        listModel = new DefaultListModel<String>();
        taskList = new JList<String>(listModel);
        scrollPane = new JScrollPane(taskList);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void addTask() {
        String taskName = taskField.getText();
        String timeStr = timeField.getText();
        if (!taskName.isEmpty() && !timeStr.isEmpty()) {
            int time = Integer.parseInt(timeStr);
            String task = taskName + " (" + time + " mins)";
            listModel.addElement(task);
            taskField.setText("");
            timeField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both task and time.");
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }

    private void clearAllTasks() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all tasks?",
                "Confirm Clear", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            listModel.clear();
        }
    }

    public static void main(String[] args) {
        PersonalTaskManagerGUI taskManager = new PersonalTaskManagerGUI();
        taskManager.setVisible(true);
    }
}
