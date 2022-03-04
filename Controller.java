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
        update();
    }

    private void start()  {
        ActionListener loop = e -> {
            try {
                model.run();
                update();

            } catch (Exception exception) {
                if(exception.getMessage() == "End State") endState();
                else exception.printStackTrace();

            }
        };
        t = new Timer(400, loop);
        t.start();
    }

    private void reset() {
        try {
            t.stop();
        } catch (Exception ignored) {
        }
        model.reset();
        update();
    }
    private void endState() {
        t.stop();

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
