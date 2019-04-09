package com.sortnumbers.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sortnumbers.model.SortResult;
import com.sortnumbers.model.UserInput;
import com.sortnumbers.service.SortService;

@RestController
public class SortController {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private SortService sortService;

	/**
	 * This functionality delegates the given input to SortService.
	 * 
	 * @param userInput
	 * @returns the SortResult
	 */
	@PostMapping(value = "/sortnumbers")
	public ResponseEntity<SortResult> sortNumbers(@Valid @RequestBody UserInput userInput) {
		SortResult resultOfSorting = sortService.sort(userInput.getUnSortedNumbers());

		logger.info(resultOfSorting);

		return ResponseEntity.ok(resultOfSorting);
	}

	// Retrieves all persisted value from database

	@GetMapping(value = "/sortnumbers")
	public List<SortResult> getValuesFromDatabase() {

		return sortService.getAllResults();

	}

}
