
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private boolean[] cells;
    public int rows, cols;

    public Model(int r, int c) {
        this.rows = r;
        this.cols = c;
        cells = new boolean[rows * cols];
    }

    public void run() throws Exception {
        cycle();
        textDisplay();
    }

    public void reset() {
        cells = new boolean[rows*cols];
    }
    public void update(int newRows, int newCols) {
        rows = newRows;
        cols = newCols;
        reset();
    }

    public void flipCell(int i) {
        cells[i] = !cells[i];
    }
    public boolean cellValue(int i) {
        return cells[i];
    }
    private void textDisplay() {
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

    private void cycle() throws  Exception {
        boolean[] temp = new boolean[rows*cols];
        for (int i = 0; i < cells.length; i++) {
            int neighbors = neighborCount(neighbors(i));
            if(neighbors < 2 && cells[i]) temp[i] = false;
            else if (neighbors == 2 && cells[i]) temp[i] = cells[i];
            else if (neighbors == 3 && cells[i]) temp[i] = cells[i];
            else if (neighbors == 3 && !cells[i]) temp[i] = true;
            else if (neighbors > 3) temp[i] = false;
        }
        if(Arrays.equals(cells, temp)) throw new Exception("End State");
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
}
