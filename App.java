public class App {
    public static void main(String[] args) {
        Model m;
        View v;
        if (args.length == 1) {
            m = new Model(args[0]);
            v = new View("Game of Life", m.cols, m.rows);
        }

        else {
            m = new Model(10, 10);
            v = new View("Game of Life", 10, 10);
        }

        Controller c = new Controller(m, v);
        c.initController();
    }
}
