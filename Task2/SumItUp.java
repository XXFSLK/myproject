
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SumItUp extends JFrame {
    ImageIcon rabbitIcon = new ImageIcon("rabbit.png");
    ImageIcon plusIcon = new ImageIcon("plus.png");
    boolean keepPlaying = true;
    JLabel resultLabel;
    JPanel rabbitPanel;
    JPanel rabbitPanel2;

    public SumItUp() {
        super();
        setTitle("Welcome to SumItUp!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create rabbit panels with random number of rabbits
        rabbitPanel = new JPanel(new GridLayout(0, 4));
        rabbitPanel2 = new JPanel(new GridLayout(0, 4));

        // Generate random numbers of rabbits
        generateRandomRabbits();

        // Create the plus label
        JLabel plusLabel = new JLabel(plusIcon);

        // Create input spinners and check button
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(1, 1, 20, 1); // Extended range to accommodate sum

        JSpinner inputBox1 = new JSpinner(model1);
        JSpinner inputBox2 = new JSpinner(model2);
        JSpinner inputBox3 = new JSpinner(model3);
        JButton checkButton = new JButton("Check!");

        // Add action listener to check button
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int num1 = (int) inputBox1.getValue();
                int num2 = (int) inputBox2.getValue();
                int result = num1 + num2;
                int userAnswer = (int) inputBox3.getValue();

                int numRabbits1 = getNumberOfRabbits(rabbitPanel);
                int numRabbits2 = getNumberOfRabbits(rabbitPanel2);

                if (result == userAnswer && num1 == numRabbits1 && num2 == numRabbits2) {
                    resultLabel.setText("Correct! Have another go?");
                    generateRandomRabbits();
                } else {
                    resultLabel.setText("Incorrect, try again.");
                }

                // Reset the input spinners
                inputBox1.setValue(1);
                inputBox2.setValue(1);
                inputBox3.setValue(1);
            }
        });

        // Create main panel and add components
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputBox1);
        inputPanel.add(new JLabel("+"));
        inputPanel.add(inputBox2);
        inputPanel.add(new JLabel("="));
        inputPanel.add(inputBox3);
        inputPanel.add(checkButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(rabbitPanel, BorderLayout.WEST);
        mainPanel.add(plusLabel, BorderLayout.CENTER);
        mainPanel.add(rabbitPanel2, BorderLayout.EAST);

        // Create a panel for the result label and center it horizontally
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultLabel = new JLabel("Enter two operands and result, then click on 'Check!'");
        resultPanel.add(resultLabel);

        // Create a vertical BoxLayout for the main content pane
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(boxLayout);

        getContentPane().add(resultPanel);
        getContentPane().add(mainPanel);
        getContentPane().add(inputPanel);

        // Pack and show the window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateRandomRabbits() {
        rabbitPanel.removeAll();
        rabbitPanel2.removeAll();

        Random rand = new Random();
        int numRabbits1 = rand.nextInt(9) + 1;
        int numRabbits2 = rand.nextInt(9) + 1;

        for (int i = 0; i < numRabbits1; i++) {
            rabbitPanel.add(new JLabel(rabbitIcon));
        }

        for (int i = 0; i < numRabbits2; i++) {
            rabbitPanel2.add(new JLabel(rabbitIcon));
        }

        rabbitPanel.revalidate();
        rabbitPanel.repaint();
        rabbitPanel2.revalidate();
        rabbitPanel2.repaint();
    }

    private int getNumberOfRabbits(JPanel panel) {
        return panel.getComponentCount();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SumItUp();
            }
        });
    }
}