package org.osgifx.view.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.osgifx.core.model.Person;
import org.osgifx.core.service.PersonService;
import org.osgifx.view.util.Requires;

/**
 *
 * @author filipeportes
 */
public class PersonController extends BaseController {

    @Requires
    private PersonService userService;
    
    private ObservableList<Person> persons;

    public TextField name;
    public TextField email;
    public TextField password;
    public TableView table;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        
        persons = FXCollections.observableArrayList(userService.findAll());
        table.setItems(persons);
    }

    public void save(ActionEvent event) {

        Person user = new Person();
        user.setName(name.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());

        userService.persist(user);
        persons.add(user);
        clean();
    }

    public void clean() {
        name.setText(null);
        email.setText(null);
        password.setText(null);
    }
}
