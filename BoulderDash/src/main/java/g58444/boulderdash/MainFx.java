package g58444.boulderdash;

import g58444.boulderdash.controller.FxController;
import g58444.boulderdash.model.Model;
import g58444.boulderdash.view.FxView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        FxView view = new FxView(model, new FxController(model), stage);
        view.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
