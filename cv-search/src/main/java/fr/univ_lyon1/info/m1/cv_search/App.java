package fr.univ_lyon1.info.m1.cv_search;

import java.io.File;

import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception { //À MODIFIER 
        //Construction of model
        ApplicantList model = new ApplicantListBuilder(new File(".")).build();
        
        //Construction of controller
        Controller controller = new Controller(model);


        //first view
        new JfxView(stage, 600, 600, controller);

        //second view
        new JfxView(new Stage(), 400, 400, controller);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(final String[] args) {
        Application.launch(args);
        
    }
}
