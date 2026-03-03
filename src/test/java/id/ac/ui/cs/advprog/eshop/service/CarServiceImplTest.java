package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    Car car;

    @BeforeEach
    void setUp(){
        car = new Car();
        car.setCarColor("Black");
        car.setCarName("Lamborghini");
        car.setCarId("123");
        car.setCarQuantity(1);
    }

    @Test
    void testCreate(){
        when(carRepository.create(car)).thenReturn(car);

        Car created = carService.create(car);

        assertNotNull(created);
        assertEquals("123", created.getCarId());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll(){
        List<Car> carLists = new ArrayList<>();
        carLists.add(car);

        Iterator<Car> iterator = carLists.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        assertEquals(1, result.size());
        assertEquals(car, result.get(0));
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById(){
        when(carRepository.findById("123")).thenReturn(car);

        Car result = carService.findById("123");

        assertNotNull(result);
        assertEquals("123", result.getCarId());
        verify(carRepository, times(1)).findById("123");
    }

    @Test
    void testUpdate(){
        when(carRepository.update(car.getCarId(), car)).thenReturn(car);

        carService.update(car.getCarId(), car);

        verify(carRepository, times(1)).update("123", car);
    }

    @Test
    void testDeleteCarById(){
        doNothing().when(carRepository).delete("123");

        carService.deleteCarById("123");

        verify(carRepository, times(1)).delete("123");
    }
}