package org.osgifx.view.controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import org.osgifx.view.util.Requires;
import org.osgifx.view.util.ServiceUtil;

/**
 *
 * @author filipeportes
 */
public class BaseController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            for (Field field : getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Requires.class)) {

                    field.setAccessible(true);
                    field.set(this, ServiceUtil.getService(field.getType().getName()));
                }
            }

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
