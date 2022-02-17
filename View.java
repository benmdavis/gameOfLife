import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
public class View {
    private int rows, cols;
    private JFrame frame;
    private JPanel main;
    private JPanel controls;
    private JButton start, reset, updateButton;
    private JButton[] buttons;
    private JLabel rowLabel, colLabel;
    private JTextField rowEntry, colEntry;


    public View(String title, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((rows + 1)*50,cols*50);

        start = new JButton("Start");
        reset = new JButton("Reset");
        rowLabel = new JLabel("Rows: " + rows);
        colLabel = new JLabel("Cols: " + cols);
        rowEntry = new JTextField();
        rowEntry.setColumns(5);
        colEntry = new JTextField();
        colEntry.setColumns(5);
        updateButton = new JButton("Update");

        main = new JPanel();
        buttons = new JButton[rows * cols];

        for(int i = 0; i < rows * cols; i++) {
            JButton temp = new JButton();
            temp.putClientProperty("idx", i);
            temp.setBackground(Color.WHITE);
            temp.setOpaque(true);
            buttons[i] = temp;
            main.add(temp);
        }

        main.setLayout(new GridLayout(rows,cols));
        frame.add(main, BorderLayout.CENTER);

        controls = new JPanel();
        controls.add(start);
        controls.add(reset);

        controls.add(rowLabel);
        controls.add(rowEntry);
        controls.add(colLabel);
        controls.add(colEntry);
        controls.add(updateButton);

        frame.add(controls, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
    public JButton getButton(int i) {
        return buttons[i];
    }
    public JButton getStart() {
        return start;
    }

    public JButton getReset() {
        return reset;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

        public JTextField getRowEntry() {
        return rowEntry;
    }

    public JTextField getColEntry() {
        return colEntry;
    }

    public void flipButton(int i) {
        if(buttons[i].getBackground() == Color.WHITE) buttons[i].setBackground(Color.BLACK);
        else buttons[i].setBackground(Color.WHITE);
    }

    public void setButton(int i, boolean value) {
        if(value) buttons[i].setBackground(Color.BLACK);
        else buttons[i].setBackground(Color.WHITE);
    }

    public void changeSettings(int newRows, int newCols) {
        rows = newRows;
        cols = newCols;

        buttons = null;
        buttons = new JButton[rows * cols];

        frame.remove(main);
        main = new JPanel();

        for(int i = 0; i < rows * cols; i++) {
            JButton temp = new JButton();
            temp.putClientProperty("idx", i);
            temp.setBackground(Color.WHITE);
            temp.setOpaque(true);
            buttons[i] = temp;
            main.add(temp);
        }

        main.setLayout(new GridLayout(rows,cols));

        frame.add(main, BorderLayout.CENTER);
        frame.setSize((rows + 1)*50,cols*50);

        frame.repaint();
    }
}
