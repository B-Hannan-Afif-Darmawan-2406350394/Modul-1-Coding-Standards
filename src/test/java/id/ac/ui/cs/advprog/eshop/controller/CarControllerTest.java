package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarServiceImpl carService;

    @Mock
    Model model;

    @InjectMocks
    CarController carController;

    Car car;

    @BeforeEach
    void setUp(){
        car = new Car();
        car.setCarId("123");
        car.setCarName("Test Car");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);

        assertEquals("createCar", viewName);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        String viewName = carController.createCarPost(car, model);

        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);

        when(carService.findAll()).thenReturn(cars);

        String viewName = carController.carListPage(model);

        assertEquals("carList", viewName);
        verify(model, times(1)).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPage() {
        when(carService.findById("123")).thenReturn(car);

        String viewName = carController.editCarPage("123", model);

        assertEquals("editCar", viewName);
        verify(model, times(1)).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        String viewName = carController.editCarPost(car, model);

        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).update("123", car);
    }

    @Test
    void testDeleteCar() {
        String viewName = carController.deleteCar("123");

        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).deleteCarById("123");
    }
}