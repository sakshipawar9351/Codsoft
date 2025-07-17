import java.awt.*;
import javax.swing.*;

public class ATM_Interface {
    private double balance = 1000.0;

    public ATM_Interface() {
        // Create main frame
        JFrame frame = new JFrame("🌟 Smart ATM");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // -------- Title Panel --------
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(35, 57, 91));
        JLabel titleLabel = new JLabel("💳 Welcome to Smart ATM");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // -------- Left Panel (Menu/Buttons) --------
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 15, 15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        menuPanel.setBackground(new Color(240, 248, 255));

        JButton checkBalanceBtn = new JButton("💼 Check Balance");
        JButton depositBtn = new JButton("➕ Deposit");
        JButton withdrawBtn = new JButton("➖ Withdraw");
        JButton exitBtn = new JButton("❎ Exit");

        Font btnFont = new Font("Segoe UI", Font.PLAIN, 16);
        for (JButton btn : new JButton[]{checkBalanceBtn, depositBtn, withdrawBtn, exitBtn}) {
            btn.setFont(btnFont);
            btn.setBackground(new Color(220, 230, 250));
            btn.setFocusPainted(false);
            menuPanel.add(btn);
        }

        frame.add(menuPanel, BorderLayout.WEST);

        // -------- Right Panel (Display Area) --------
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        JLabel balanceLabel = new JLabel("Current Balance: ₹" + balance, SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        balanceLabel.setForeground(new Color(34, 139, 34));
        displayPanel.add(balanceLabel, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea("🧾 Transaction history will appear here...");
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        displayPanel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.CENTER);

        // -------- Button Actions --------
        checkBalanceBtn.addActionListener(e -> {
            String msg = "✅ Your current balance is: ₹" + balance;
            JOptionPane.showMessageDialog(frame, msg);
            balanceLabel.setText("Current Balance: ₹" + balance);
            infoArea.append("\n[✔] Checked balance: ₹" + balance);
        });

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
                    infoArea.append("\n[+] Deposited: ₹" + amount);
                    JOptionPane.showMessageDialog(frame, "₹" + amount + " deposited successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Enter a positive amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "❌ Invalid input.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    balanceLabel.setText("Current Balance: ₹" + balance);
                    infoArea.append("\n[-] Withdrawn: ₹" + amount);
                    JOptionPane.showMessageDialog(frame, "₹" + amount + " withdrawn successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "❌ Insufficient balance or invalid amount.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "❌ Invalid input.");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "👋 Thank you for using Smart ATM!");
            System.exit(0);
        });

        // -------- Display Frame --------
        frame.setVisible(true);
    }

    // ---- Main Method ----
    public static void main(String[] args) {
        new ATM_Interface();
    }
}
