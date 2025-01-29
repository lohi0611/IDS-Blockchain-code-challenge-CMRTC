import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Event {
    private String startTime;
    private String endTime;
    private String description;

    public Event(String startTime, String endTime, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description;
    }
}

class EventManager {
    private final List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String startTime, String endTime, String description) {
        Event event = new Event(startTime, endTime, description);
        events.add(event);
    }

    public String displaySchedule() {
        StringBuilder schedule = new StringBuilder();
        for (Event event : events) {
            schedule.append(event.toString()).append("\n");
        }
        return schedule.toString();
    }
}

public class EventManagerApp extends JFrame {
    private EventManager eventManager;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField descriptionField;
    private JTextArea scheduleArea;

    public EventManagerApp() {
        this.eventManager = new EventManager();
        setTitle("Event Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Start Time:"));
        startTimeField = new JTextField();
        inputPanel.add(startTimeField);

        inputPanel.add(new JLabel("End Time:"));
        endTimeField = new JTextField();
        inputPanel.add(endTimeField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String description = descriptionField.getText();
                eventManager.addEvent(startTime, endTime, description);
                startTimeField.setText("");
                endTimeField.setText("");
                descriptionField.setText("");
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Schedule area
        scheduleArea = new JTextArea();
        scheduleArea.setEditable(false);
        add(new JScrollPane(scheduleArea), BorderLayout.CENTER);

        JButton displayButton = new JButton("Display Schedule");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleArea.setText(eventManager.displaySchedule());
            }
        });
        add(displayButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventManagerApp app = new EventManagerApp();
        app.setSize(500, 400);
        app.setVisible(true);
    }
}