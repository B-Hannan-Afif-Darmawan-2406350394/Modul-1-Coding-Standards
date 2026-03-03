package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    CarRepository carRepository;
    Car car1;
    Car car2;

    @BeforeEach
    void setUp(){
        carRepository = new CarRepositoryImpl();

        car1 = new Car();
        car1.setCarId("123");
        car1.setCarName("Ferrari");
        car1.setCarColor("Red");
        car1.setCarQuantity(1);

        car2 = new Car();
        car2.setCarId("456");
        car2.setCarName("Lamborghini");
        car2.setCarColor("Black");
        car2.setCarQuantity(1);
    }

    @Test
    void testCreateIfCarIdNull(){
        Car car = new Car();
        car.setCarName("Mustang");
        car.setCarColor("Pink");
        car.setCarQuantity(2);

        carRepository.create(car);
        Iterator<Car> carIterator = carRepository.findAll();

        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();

        assertEquals(savedCar.getCarId(), car.getCarId());
        assertEquals(savedCar.getCarColor(), car.getCarColor());
        assertEquals(savedCar.getCarName(), car.getCarName());
        assertEquals(savedCar.getCarQuantity(), car.getCarQuantity());
    }

    @Test
    void testCreateAndFind(){
        carRepository.create(car1);
        Iterator<Car> carIterator = carRepository.findAll();

        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();

        assertEquals(savedCar.getCarId(), car1.getCarId());
        assertEquals(savedCar.getCarColor(), car1.getCarColor());
        assertEquals(savedCar.getCarName(), car1.getCarName());
        assertEquals(savedCar.getCarQuantity(), car1.getCarQuantity());
    }

    @Test
    void testFindById(){
        carRepository.create(car1);
        carRepository.create(car2);

        Car found1 = carRepository.findById(car1.getCarId());
        Car found2 = carRepository.findById(car2.getCarId());

        assertEquals(car1, found1);
        assertEquals(car2, found2);
    }

    @Test
    void testFindByIdNotFound(){
        carRepository.create(car1);
        Car found = carRepository.findById("not found");

        assertNull(found);
    }

    @Test
    void testCarUpdate(){
        carRepository.create(car2);
        carRepository.create(car1);
        Car updatedCar = new Car();
        updatedCar.setCarId(car1.getCarId());
        updatedCar.setCarQuantity(2);
        updatedCar.setCarColor("Blue");
        updatedCar.setCarName("Porsche");

        carRepository.update(car1.getCarId(), updatedCar);
        Car result = carRepository.findById(car1.getCarId());
        assertEquals(result.getCarName(), updatedCar.getCarName());
        assertEquals(result.getCarQuantity(), updatedCar.getCarQuantity());
        assertEquals(result.getCarColor(), updatedCar.getCarColor());
    }

    @Test
    void testCarUpdateNotFound(){
        Car updatedCar = new Car();
        updatedCar.setCarId("not exist");
        updatedCar.setCarQuantity(2);
        updatedCar.setCarColor("Blue");
        updatedCar.setCarName("Porsche");
        assertNull(carRepository.update("notFound", updatedCar));
    }

    @Test
    void testDelete(){
        carRepository.create(car1);
        carRepository.delete(car1.getCarId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
}
