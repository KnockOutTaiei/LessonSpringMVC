package rl.knockout.taiei;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import rl.knockout.taiei.contoller.*;
import rl.knockout.taiei.model.*;
import rl.knockout.taiei.model.dao.*;

import java.sql.*;
public class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    @Test
    public void goHome() {
        assertEquals("home", homeController.home(null, null));
    }

}