import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class life {
    private boolean[] cells;
    private int rows;
    private int cols;
    private JFrame frame;
    private JPanel main;
    private JButton[] buttons;

    public life(int x, int y) {
        cells = new boolean[x * y];
        rows = x;
        cols = y;
    }

    public void lifeLoop() {
        boolean[] temp = new boolean[rows*cols];
        for (int i = 0; i < cells.length; i++) {
            int neighbors = neighborCount(neighbors(i));
            if(neighbors < 2 && cells[i]) temp[i] = false;
            else if (neighbors == 2 && cells[i]) temp[i] = cells[i];
            else if (neighbors == 3 && cells[i]) temp[i] = cells[i];
            else if (neighbors == 3 && !cells[i]) temp[i] = true;
            else if (neighbors > 3) temp[i] = false;
        }
        cells = temp;
    }

    private List<Integer> neighbors(int index) {
        List<Integer> neighbors = new ArrayList<>();
        if(index % rows != 0) neighbors.add(index - rows - 1);
        neighbors.add(index - rows);
        if(index % rows != rows - 1) neighbors.add(index - rows + 1);
        if(index % rows != 0) neighbors.add(index - 1);
        if(index % rows != rows-1) neighbors.add(index + 1);
        if(index % rows != 0) neighbors.add(index + rows -1);
        neighbors.add(index + rows);
        if(index % rows != rows -1) neighbors.add(index + rows + 1);
        return neighbors;
    }
    private int neighborCount(List<Integer> neighbors) {
        int count = 0;
        for(Integer i : neighbors) {
            if (i > 0 && i < rows*cols && cells[i]) count++;
        }
        return count;
    }
    public void set(int x, int y) {
        int idx = x * rows + y % rows;
        cells[idx] = true;
    }

    public void textDisplay() {
        StringBuilder s = new StringBuilder();
        s.append(' ');
        for (int i = 0; i < rows; i++) s.append(i);
        for(int i = 0; i < cells.length; i++) {
            if(i%rows == 0) s.append("\n" + i/10);
            if(cells[i]) s.append('*');
            else s.append('x');
        }
        s.append('\n');
        System.out.print(s.toString());
    }

    public void guiDisplay() {
        frame = new JFrame();
        main = new JPanel();
        buttons = new JButton[rows * cols];
        for(int i = 0; i < rows * cols; i++) {
            JButton temp = new JButton();
            temp.putClientProperty("idx", i);
            temp.addActionListener(e -> {
                int idx = (int) temp.getClientProperty("idx");
                cells[idx] = !cells[idx];
                if(cells[idx]) temp.setBackground(Color.BLACK);
                else temp.setBackground(Color.WHITE);
            });

            temp.setBackground(Color.WHITE);
            temp.setOpaque(true);
            buttons[i] = temp;
            main.add(temp);
        }

        main.setLayout(new GridLayout(rows,cols));
        frame.add(main, BorderLayout.CENTER);
        JButton function = new JButton("START");
        function.setSize(50, cols*50);
        function.addActionListener(new guiRefresh());
        frame.add(function, BorderLayout.SOUTH);
        frame.setSize((rows + 1)*50,cols*50);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        frame.setVisible(true);

    }



    class guiRefresh implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            System.out.println("button pressed");
            ActionListener loop = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lifeLoop();
                    textDisplay();
                    for(int i = 0; i < rows * cols; i++) {
                        if(cells[i]) buttons[i].setBackground(Color.BLACK);
                        else buttons[i].setBackground(Color.WHITE);
                        buttons[i].setVisible(true);
                    }
                }
            };
            Timer t = new Timer(400, loop);
            t.start();

        }


    }
    public static void main(String[] args) throws InterruptedException {
        life main = new life(10,10);
//        main.set(1,1);
//        main.set(1,2);
//        main.set(1,3);
        main.guiDisplay();

    }
}
