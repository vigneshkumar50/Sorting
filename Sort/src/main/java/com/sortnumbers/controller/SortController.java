package com.sortnumbers.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sortnumbers.model.SortResult;
import com.sortnumbers.model.UserInput;
import com.sortnumbers.service.SortService;

@RestController
public class SortController {
	@Autowired
	private SortService sortService;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * This functionality delegates the given input to SortService.
	 * 
	 * @param userInput
	 * @returns the SortResult
	 */
	@RequestMapping(value = "/sortnumbers", method = RequestMethod.POST)
	public ResponseEntity<SortResult> sortNumbers(@Valid @RequestBody UserInput userInput) {
		SortResult resultOfSorting = null;
		resultOfSorting = sortService.sort(userInput.getUnSortedNumbers());
		logger.info(resultOfSorting);
		return ResponseEntity.ok(resultOfSorting);
	}
}
