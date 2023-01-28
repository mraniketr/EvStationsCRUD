package com.aniket.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.app.model.evStation;
import com.aniket.app.model.evStationRequest;
import com.aniket.app.repository.evRepo;

@RestController
public class EvController {

	@Autowired
	private evRepo evRepo;

	@GetMapping("/")
	public ResponseEntity<List<evStation>> getAll(@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer offset, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String param) {
		try {
			List<evStation> res = null;
			Sort sortob = null;
			if (sort != null && param != null) {
				sortob = Sort.by(sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, param);

			}

			if (limit != null) {
				if (sortob == null) {
					Page<evStation> res1 = evRepo.findAll(PageRequest.of(offset == null ? 0 : offset, limit));
					res = res1.getContent();
				} else {
					Page<evStation> res1 = evRepo.findAll(PageRequest.of(offset == null ? 0 : offset, limit, sortob));
					res = res1.getContent();
				}
			} else {
				res = evRepo.findAll();
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<evStation> getOne(@PathVariable("id") Long id) {
		try {
			Optional<evStation> res = evRepo.findById(id);
			if (res.isPresent()) {
				return new ResponseEntity<>(res.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
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

	@PutMapping("/{id}/edit")
	public ResponseEntity<evStation> updateStation(@PathVariable("id") Long id, evStationRequest stationReq) {
		try {
			evStation station = new evStation(stationReq);
			Optional<evStation> res = evRepo.findById(id);
			if (res.isPresent()) {
//				evRepo.deleteById(id);
				station.setStation_id(id);
				return new ResponseEntity<>(evRepo.save(station), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") Long id) {
		try {
			Optional<evStation> res = evRepo.findById(id);
			if (res.isPresent()) {
				evRepo.deleteById(id);
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
