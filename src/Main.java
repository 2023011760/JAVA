import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double firstNumber;
    private String operator;

    public Main() {
        setTitle("계산기");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());




        display = new JTextField();
        display.setEditable(false);
        display.setBackground(Color.GREEN);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        currentInput = new StringBuilder();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if (command.equals("=")) {
            if (currentInput.length() > 0) {
                double secondNumber = Double.parseDouble(currentInput.toString());
                double result = calculate(firstNumber, secondNumber, operator);
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
            }
        } else {
            if (currentInput.length() > 0) {
                firstNumber = Double.parseDouble(currentInput.toString());
                operator = command;
                currentInput.setLength(0);
            }
        }
    }

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main calculator = new Main();
            calculator.setVisible(true);
        });
    }
}
