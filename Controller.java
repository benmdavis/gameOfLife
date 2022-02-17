

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    private Timer t;
    public Controller(Model m, View v) {
        model = m;
        view = v;
    }


    public void initController() {

        view.getReset().addActionListener(e -> reset());
        view.getStart().addActionListener(e -> start());
        view.getUpdateButton().addActionListener(e -> updateGameSettings());
        for (int i = 0; i < model.rows * model.cols; i++) {
            view.getButton(i).addActionListener(e -> {
                JButton temp = (JButton) e.getSource();
                int idx = (int) temp.getClientProperty("idx");
                model.flipCell(idx);
                view.flipButton(idx);

            });
        }
    }

    private void start()  {
        ActionListener loop = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.run();
                update();
            }
        };
        t = new Timer(400, loop);
        t.start();
    }

    private void reset() {
        t.stop();
        model.reset();
        update();
    }

    private void update() {
        for (int i = 0; i < model.rows * model.cols; i++) {
            if (model.cellValue(i)) view.setButton(i, true);
            else view.setButton(i, false);
        }
    }

    private void updateGameSettings() {
        int newRows = Integer.parseInt(view.getRowEntry().getText());
        int newCols = Integer.parseInt(view.getColEntry().getText());
        model.update(newRows, newCols);
        view.changeSettings(newRows, newCols);
        update();
        for (int i = 0; i < model.rows * model.cols; i++) {
            view.getButton(i).addActionListener(e -> {
                JButton temp = (JButton) e.getSource();
                int idx = (int) temp.getClientProperty("idx");
                model.flipCell(idx);
                view.flipButton(idx);

            });
        }
    }
}
