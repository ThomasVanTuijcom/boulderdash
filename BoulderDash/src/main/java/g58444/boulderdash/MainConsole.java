package g58444.boulderdash;

import g58444.boulderdash.controller.ConsoleController;
import g58444.boulderdash.model.Model;
import g58444.boulderdash.view.ConsoleView;

public class MainConsole {
    public static void main(String[] args) {
        Model model = new Model();
        ConsoleController controller = new ConsoleController(model, new ConsoleView(model));
        controller.start();
    }
}
