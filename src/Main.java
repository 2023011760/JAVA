import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double num1, num2, result;
    private boolean isNewCalculation = true; // 새로운 계산을 위한 플래그

    public Main() {
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 48));
        display.setForeground(Color.BLACK);
        display.setPreferredSize(new Dimension(400, 100));

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        JButton addButton = createButton("+");
        JButton subButton = createButton("-");
        JButton mulButton = createButton("×");
        JButton divButton = createButton("÷");
        JButton equalsButton = createButton("=");
        JButton clearButton = createButton("AC");
        JButton ceButton = createButton("CE");
        JButton percentButton = createButton("%");
        JButton decimalButton = createButton(".");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(clearButton);
        panel.add(ceButton);
        panel.add(percentButton);
        panel.add(divButton);

        for (int i = 7; i <= 9; i++) panel.add(numberButtons[i]);
        panel.add(mulButton);

        for (int i = 4; i <= 6; i++) panel.add(numberButtons[i]);
        panel.add(subButton);

        for (int i = 1; i <= 3; i++) panel.add(numberButtons[i]);
        panel.add(addButton);

        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(equalsButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(panel);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        });

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isNewCalculation) {
                display.setText(command);
                isNewCalculation = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("AC")) {
            display.setText("0");
            num1 = num2 = result = 0;
            operator = "";
            isNewCalculation = true;
        } else if (command.equals("CE")) {

            display.setText(String.valueOf(num1));
        } else if (command.equals("%")) {
            num1 = Double.parseDouble(display.getText());
            result = num1 / 100;
            display.setText(String.valueOf(result));
            operator = "";
            isNewCalculation = true; // 새로운 계산을 시작
        } else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("×") || command.equals("÷")) {
            if (!operator.isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
            } else {
                num1 = Double.parseDouble(display.getText());
            }
            operator = command;
            display.setText("0");
            isNewCalculation = true; // 새로운 숫자를 입력할 준비
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            calculate();
            operator = "";
            isNewCalculation = true;
        }
    }

    private void calculate() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "×":
                result = num1 * num2;
                break;
            case "÷":
                result = num1 / num2;
                break;
        }
        display.setText(String.valueOf(result));
        num1 = result;
    }

    public static void main(String[] args) {
        new Main();
    }
}
