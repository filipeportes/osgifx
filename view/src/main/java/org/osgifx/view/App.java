package org.osgifx.view;

import static java.io.File.separator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;

public class App extends Application {

    private static final String MAIN_SCREEN = "main" + separator + "Main";

    private static final String FXML_ROOT_FOLDER = "fxml" + separator;

    private static final String FXML_COMPONENT_FOLDER = "view";

    private static final String FXML_SUFIX = ".fxml";

    private Parent root;

    private Stage stage;

    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        //load the Main.fxml layout
        root = loadFxml(MAIN_SCREEN, null);

        //load the styleSheet of the project
        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getClassLoader().getResource("css/test.css").toString());

        primaryStage.setScene(scene);
        //TODO change to a properties value
        primaryStage.setTitle("OSGi JavaFX application");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            Bundle bundle = FrameworkUtil.getBundle(Activator.class);
            bundle.getBundleContext().getBundle(0).stop();
            super.stop();
        } catch (BundleException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void replaceScene(String fxml) {

        Parent page = loadFxml(fxml, null);
        root = page;

        stage.getScene().setRoot(root);
    }

    public void replaceContent(String fxml) {

        Parent page = loadFxml(fxml, FXML_COMPONENT_FOLDER);

        BorderPane content = (BorderPane) root;
        content.setCenter(page);

    }

    private Parent loadFxml(String fxml, String folder) {
        try {

            StringBuilder fxmlUrl = new StringBuilder();
            fxmlUrl.append(FXML_ROOT_FOLDER);

            if (folder != null && !"".equals(folder)) {
                fxmlUrl.append(folder).append(separator);
            }

            fxmlUrl.append(fxml);
            fxmlUrl.append(FXML_SUFIX);

            //set the osgi ClassLoader to the FXMLLoader.
            FXMLLoader.setDefaultClassLoader(Activator.class.getClassLoader());
            return FXMLLoader.load(getClass().getClassLoader().getResource(fxmlUrl.toString()));

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
