package com.aniket.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.app.model.evStation;
import com.aniket.app.model.evStationRequest;
import com.aniket.app.repository.evRepo;

@RestController
public class EvController {

	@Autowired
	private evRepo evRepo;

	@GetMapping("/")
	public ResponseEntity<List<evStation>> getAll() {
		try {
			return new ResponseEntity<>(evRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@PostMapping("/")
	public ResponseEntity<evStation> createStation(evStationRequest stationReq) {
		try {
			evStation station = new evStation(stationReq);

			return new ResponseEntity<>(evRepo.save(station), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
