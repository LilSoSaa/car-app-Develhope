package com.example.car_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Create new Car
    @PostMapping("/create")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    // Ottiene tutte le Car
    @GetMapping("/get-all")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Get a car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return ResponseEntity.of(Optional.of(car));
    }

    //Update a car's details
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();

            // Update the existing car with the new data
            existingCar.setModelName(car.getModelName());
            existingCar.setType(car.getType());

            // Save the updated car in the repository
            Car updatedCar = carRepository.save(existingCar);

            // Return the updated car in the response body
            return ResponseEntity.ok(updatedCar);
        }

        // If car is not found, return 404 Not Found
        return ResponseEntity.notFound().build();
    }

    // Delete Car by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Delete all Cars
    @DeleteMapping("/deleteAll")
    public void deleteAllCars() {
        carRepository.deleteAll();
    }

}
