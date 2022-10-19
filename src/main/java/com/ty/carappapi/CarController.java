package com.ty.carappapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
	@Autowired
	CarRepository carRepository;
	@PostMapping("/cars")
	public ResponseStructure<Car> storeCar(@RequestBody Car car) {
		ResponseStructure<Car> responseStructure  =  new ResponseStructure<Car>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(carRepository.save(car));
		return responseStructure;
	}
	/* @PostMapping("/savecar")
	public Car saveCar(@RequestBody Car car) {
		Car car1=carRepository.save(car);
		return car;
	}*/
	
	@GetMapping("/cars/{id}")
	public ResponseStructure<Car> getMyCar(@PathVariable int id) {
		Optional<Car> opt  = carRepository.findById(id);
		ResponseStructure<Car> responseStructure  =  new ResponseStructure<Car>();
		if(opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(opt.get());
			return responseStructure;
		}
		else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID Not Found");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
	
	/*@GetMapping("/getcar")
	public Car getMyCar(@RequestParam int id) {
		Optional<Car> opt = carRepository.findById(id);
		if(opt.isPresent()) {
			Car c =opt.get();
			return c;
		} 
		else {
			return null;
		 }
		
	  }*/
	
	@GetMapping("/getall")
	public ResponseStructure<List<Car>> getAllCar() {
		ResponseStructure<List<Car>> responseStructure  = new ResponseStructure<List<Car>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(carRepository.findAll());
		return responseStructure;
		
     }
	
	@DeleteMapping("/cars/{id}")
	public String deleteCar(@PathVariable int id) {
		Optional<Car> opt = carRepository.findById(id);
		
		ResponseStructure<Car> responseStructure  =  new ResponseStructure<Car>();
		if(opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(opt.get());
			return "Data deleted";
		}
		else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID Not Found");
			responseStructure.setData(null);
			return "Data not found";
		}
		
		/*if(opt.isPresent()) {
			Car c =opt.get();
			carRepository.delete(c);
		return "Car is deleted";
		} else {
		return "Id not found";
		}*/
	}
	@PutMapping("/cars")
	public ResponseStructure<Car> updateMyCar(@RequestParam int id,@RequestBody Car car) {
		Optional<Car> opt = carRepository.findById(id);
		
		ResponseStructure<Car> responseStructure  =  new ResponseStructure<Car>();
		if(opt.isPresent()) {
			Car ret=opt.get();
			ret.setName(car.getName());
			ret.setBrand(car.getBrand());
			ret.setCost(car.getCost());
			carRepository.save(ret);
			ret.setType(car.getType());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("SUCCESS");
			responseStructure.setData(carRepository.save(ret));
			return responseStructure;
		}
		else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID Not Found");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
}
		
		
		/*if(opt.isPresent()) {
			Car ret=opt.get();
			ret.setName(car.getName());
			ret.setBrand(car.getBrand());
			ret.setCost(car.getCost());
			carRepository.save(ret);
			ret.setType(car.getType());
			return ret;
		} else {
			return null;
		}
	}*/


