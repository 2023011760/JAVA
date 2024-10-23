import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double num1, num2, result;

    public Main() {
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        // 버튼 생성
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("×");
        JButton divButton = new JButton("÷");
        JButton equalsButton = new JButton("=");
        JButton clearButton = new JButton("AC");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);

        // 레이아웃 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(clearButton);
        panel.add(new JButton("CE")); // CE 버튼
        panel.add(new JButton("%")); // % 버튼
        panel.add(divButton);

        for (int i = 7; i <= 9; i++) panel.add(numberButtons[i]);
        panel.add(mulButton);

        for (int i = 4; i <= 6; i++) panel.add(numberButtons[i]);
        panel.add(subButton);

        for (int i = 1; i <= 3; i++) panel.add(numberButtons[i]);
        panel.add(addButton);

        panel.add(numberButtons[0]);
        panel.add(new JButton(".")); // . 버튼
        panel.add(equalsButton);

        // 프레임 설정
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (display.getText().equals("0")) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("AC")) {
            display.setText("0");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("+") || command.equals("-") || command.equals("×") || command.equals("÷")) {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("0");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
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
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
