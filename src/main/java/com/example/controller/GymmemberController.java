package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.gymmember;
import com.example.repository.gymmemberRepository;

@RestController
@RequestMapping("/api/v1")
public class GymmemberController {
	@Autowired
	private gymmemberRepository gymmemberRepository;

	@GetMapping("/gymmembers")
	public List<com.example.repository.gymmember> getAllgymmembers() {
		return gymmemberRepository.findAll();
	}

	@GetMapping("/gymmembers/{id}")
	public ResponseEntity<com.example.repository.gymmember> getgymmemberById(@PathVariable(value = "id") Long gymmemberId)
			throws ResourceNotFoundException {
		com.example.repository.gymmember gymmember = gymmemberRepository.findById(gymmemberId)
				.orElseThrow(() -> new ResourceNotFoundException("gymmember not found for this id :: " + gymmemberId));
		return ResponseEntity.ok().body(gymmember);
	}

	@PostMapping("/gymmembers")
	public gymmember creategymmember(@Validated @RequestBody gymmember gymmember) {
		return gymmemberRepository.save(gymmember);
	}

	@PutMapping("/gymmembers/{id}")
	public ResponseEntity<com.example.repository.gymmember> updategymmember(@PathVariable(value = "id") Long gymmemberId,
			@Validated @RequestBody gymmember gymmemberDetails) throws ResourceNotFoundException {
		com.example.repository.gymmember gymmember = gymmemberRepository.findById(gymmemberId)
				.orElseThrow(() -> new ResourceNotFoundException("gymmember not found for this id :: " + gymmemberId));

		gymmember.setEmailId(gymmemberDetails.getEmailId());
		gymmember.setFirstName(gymmemberDetails.getLastName());
		gymmember.setFirstName(gymmemberDetails.getFirstName());
		final com.example.repository.gymmember updatedgymmember = gymmemberRepository.save(gymmember);
		return ResponseEntity.ok(updatedgymmember);
	}

	@DeleteMapping("/gymmembers/{id}")
	public Map<String, Boolean> deletegymmember(@PathVariable(value = "id") Long gymmemberId)
			throws ResourceNotFoundException {
		com.example.repository.gymmember gymmember = gymmemberRepository.findById(gymmemberId)
				.orElseThrow(() -> new ResourceNotFoundException("gymmember not found for this id :: " + gymmemberId));

		gymmemberRepository.delete(gymmember);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
