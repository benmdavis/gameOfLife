public class App {
    public static void main(String[] args) {
        Model m = new Model(10,10); //TODO convert so rows and columns aren't passed to initiazlize the model, but are options presented.
        View v = new View("Game of Life", 10, 10);
        Controller c = new Controller(m, v);
        c.initController();
    }
}
