package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {

    HomeController homeController;

    @BeforeEach
    void setUp(){
        homeController = new HomeController();
    }

    @Test
    void testCreateProductPage() {
        String viewName = homeController.homePage();
        assertEquals("homePage", viewName);
    }
}
