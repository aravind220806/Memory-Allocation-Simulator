import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MemorySimulator extends JFrame {
    // Initial memory configuration
    final int[] initialBlocks = {100, 500, 200, 300, 600};
    int[] currentBlocks;

    public MemorySimulator() {
        currentBlocks = initialBlocks.clone();
        
        setTitle("OS Memory Allocation Simulator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UI Components
        JPanel topPanel = new JPanel();
        JTextField processInput = new JTextField(5);
        JButton btnFirst = new JButton("First Fit");
        JButton btnBest = new JButton("Best Fit");
        JButton btnWorst = new JButton("Worst Fit");
        JButton btnReset = new JButton("Reset");

        topPanel.add(new JLabel("Process Size:"));
        topPanel.add(processInput);
        topPanel.add(btnFirst);
        topPanel.add(btnBest);
        topPanel.add(btnWorst);
        topPanel.add(btnReset);

        MemoryPanel canvas = new MemoryPanel();
        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        // --- FIRST FIT LOGIC ---
        btnFirst.addActionListener(e -> {
            int size = Integer.parseInt(processInput.getText());
            for (int i = 0; i < currentBlocks.length; i++) {
                if (currentBlocks[i] >= size) {
                    currentBlocks[i] -= size;
                    break;
                }
            }
            canvas.repaint();
        });

        // --- BEST FIT LOGIC (Corrected) ---
        btnBest.addActionListener(e -> {
            int size = Integer.parseInt(processInput.getText());
            int bestIdx = -1;
            for (int i = 0; i < currentBlocks.length; i++) {
                if (currentBlocks[i] >= size) {
                    if (bestIdx == -1 || currentBlocks[i] < currentBlocks[bestIdx]) {
                        bestIdx = i;
                    }
                }
            }
            if (bestIdx != -1) currentBlocks[bestIdx] -= size;
            canvas.repaint();
        });

        // --- WORST FIT LOGIC ---
        btnWorst.addActionListener(e -> {
            int size = Integer.parseInt(processInput.getText());
            int worstIdx = -1;
            for (int i = 0; i < currentBlocks.length; i++) {
                if (currentBlocks[i] >= size) {
                    if (worstIdx == -1 || currentBlocks[i] > currentBlocks[worstIdx]) {
                        worstIdx = i;
                    }
                }
            }
            if (worstIdx != -1) currentBlocks[worstIdx] -= size;
            canvas.repaint();
        });

        btnReset.addActionListener(e -> {
            currentBlocks = initialBlocks.clone();
            canvas.repaint();
        });

        setVisible(true);
    }

    class MemoryPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 50;
            for (int i = 0; i < currentBlocks.length; i++) {
                // Draw Block
                g.setColor(Color.BLACK);
                g.drawRect(x, 100, 80, 150);
                
                // Draw "Used" part
                int usedHeight = (int) ((1.0 - (double)currentBlocks[i]/initialBlocks[i]) * 150);
                g.setColor(Color.RED);
                g.fillRect(x, 100, 80, usedHeight);
                
                // Draw labels
                g.setColor(Color.BLACK);
                g.drawString(currentBlocks[i] + "K", x + 20, 270);
                g.drawString("Block " + i, x + 15, 90);
                x += 100;
            }
        }
    }

    public static void main(String[] args) {
        new MemorySimulator();
    }
}