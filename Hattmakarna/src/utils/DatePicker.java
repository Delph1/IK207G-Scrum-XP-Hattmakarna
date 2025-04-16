package utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.stream.*;
import java.util.Arrays;

public class DatePicker extends JDialog {
    private JComboBox<String> monthBox;
    private JComboBox<Integer> yearBox;
    private JPanel dayPanel;
    private LocalDate selectedDate = null;
    private JTextField linkedInput;

    public DatePicker(JFrame parent, JTextField textField) {
        super(parent, "Välj ett datum", true);
        this.linkedInput = textField;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false); 

        JPanel topPanel = new JPanel();
        String[] months = Arrays.stream(Month.values())
            .map(Month::name)
            .map(n -> n.charAt(0) + n.substring(1).toLowerCase())
            .toArray(String[]::new);
        monthBox = new JComboBox<>(months);

        yearBox = new JComboBox<>();
        IntStream.rangeClosed(1900, 2100).forEach(yearBox::addItem);
        yearBox.setSelectedItem(LocalDate.now().getYear());
        monthBox.setSelectedIndex(LocalDate.now().getMonthValue() - 1);

        topPanel.add(yearBox);
        topPanel.add(monthBox);

        add(topPanel, BorderLayout.NORTH);

        dayPanel = new JPanel(new GridLayout(6, 7, 5, 5)); 
        dayPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(dayPanel, BorderLayout.CENTER);

        monthBox.addActionListener(e -> renderCalendar());
        yearBox.addActionListener(e -> renderCalendar());

        renderCalendar();
        pack();
    }

    private void renderCalendar() {
        dayPanel.removeAll();
        int year = (int) yearBox.getSelectedItem();
        int month = monthBox.getSelectedIndex() + 1;
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int startDay = firstDay.getDayOfWeek().getValue();
        int daysInMonth = firstDay.lengthOfMonth();
        LocalDate today = LocalDate.now();

        for (int i = 1; i < startDay; i++) {
            dayPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= 31; day++) {
            final int currentDay = day;
            JButton button = new JButton(String.valueOf(currentDay));
            button.setPreferredSize(new Dimension(40, 30));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setFont(new Font("SansSerif", Font.PLAIN, 12));

            if (currentDay > daysInMonth) {
                button.setEnabled(false);
                button.setBackground(Color.LIGHT_GRAY);
            } else {
                LocalDate thisDate = LocalDate.of(year, month, currentDay);
                if (thisDate.equals(today)) {
                    button.setBackground(new Color(220, 240, 255)); 
                }
                else {
                     button.setBackground(Color.WHITE);
                }
                button.addActionListener(e -> {
                    selectedDate = thisDate;
                    linkedInput.setText(selectedDate.toString());
                    dispose();
                });
            }
            dayPanel.add(button);
        }

        dayPanel.revalidate();
        dayPanel.repaint();
    }

    public static void attachToTextField(JFrame parent, JTextField textField) {
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DatePicker dp = new DatePicker(parent, textField);

                Point locationOnScreen = textField.getLocationOnScreen();
                dp.setLocation(locationOnScreen.x, locationOnScreen.y + textField.getHeight());

                dp.setVisible(true);
            }
        });
    }
} 

