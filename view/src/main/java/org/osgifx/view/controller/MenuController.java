package org.osgifx.view.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.osgifx.view.App;

/**
 *
 * @author filipeportes
 */
public class MenuController {

    @FXML
    private MenuBar menuBar;

    @FXML
    protected void processMenuItem(ActionEvent event) {
        MenuItem item = (MenuItem) event.getTarget();
        App.getInstance().replaceContent(item.getId());
    }

    @FXML
    protected void processExit(ActionEvent event) {
        try {
            System.out.println("JavaFx OSGi view test stoped");
            App.getInstance().stop();
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
