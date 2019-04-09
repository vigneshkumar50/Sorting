package com.sortnumbers.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sortnumbers.dao.SortDao;
import com.sortnumbers.entity.SortedEntity;
import com.sortnumbers.model.SortResult;

/**
 * Sort a given number of numerical values by randomly changing position of
 * values in ascending order implemented by using insertion sort algorithm.
 * Calculates the sorting time duration in Nano Seconds. Count the changes of
 * position.
 * 
 * @author vigneshkumar G
 */
@Service
public class SortService {

	@Autowired
	SortDao dao;

	private Logger logger = Logger.getLogger(this.getClass().getName());
	public final static String SUCCESS_STATUS = "success";
	public final static String FAILURE_STATUS = "Sort Failed: ";
	public final static String NUMERIC_VALUE_MSG = "The given numbers should be in numerial values :";
	public final static String GIVEN_VALUE = "The given numbers ";
	public final static String INVALID_NUMBER = "are invalid.";

	/**
	 * @param randomNumbers
	 * @return SortResult
	 */
	public SortResult sort(String randomNumbers) {
		SortedEntity number = new SortedEntity();
		int[] arrayOfRandomNumbers = null;
		try {
			arrayOfRandomNumbers = getArrayofIntegerRandomNumbers(randomNumbers);

		} catch (NumberFormatException e) {
			number.setUnsortedNumbers(randomNumbers);
			number.setStatus(FAILURE_STATUS);
			dao.saveOrUpdate(number);
			logger.error(NUMERIC_VALUE_MSG + randomNumbers);
			return new SortResult(randomNumbers, 
					"",
					0,
					0,
					(FAILURE_STATUS + GIVEN_VALUE + randomNumbers + INVALID_NUMBER));
		}

		String unSortedNumbers = Arrays.toString(arrayOfRandomNumbers);
		LocalTime startTime = LocalTime.now();
		// invoke sort
		int swapCount = getSwapCount(arrayOfRandomNumbers);
		LocalTime endTime = LocalTime.now();

		// measures the duration between startTime and endTime.
		Duration duration = Duration.between(startTime, endTime);

		number.setUnsortedNumbers(unSortedNumbers);
		number.setSortedNumbers(Arrays.toString(arrayOfRandomNumbers));
		number.setDuration(duration.getNano());
		number.setSwapCount(swapCount);
		number.setStatus(SUCCESS_STATUS);
		dao.saveOrUpdate(number);
		return new SortResult(unSortedNumbers,
				Arrays.toString(arrayOfRandomNumbers),
				duration.getNano(),
				swapCount,
				SUCCESS_STATUS);
	}

	/**
	 * @param randomNumbers
	 * @return
	 */
	private int[] getArrayofIntegerRandomNumbers(String randomNumbers) {
		return Arrays.stream(randomNumbers.split(","))
				.map(String::trim)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	/**
	 * @param randomNums
	 * @return swapCount
	 */
	// name change to sort
	private int getSwapCount(int randomNums[]) {
		int swapCount = 0;
		Random random = new Random(randomNums.length);

		// traverse the array elements until sorted in ascending order
		while (!ArrayUtils.isSorted(randomNums)) {
			int randomIndex = random.nextInt(randomNums.length);

			int key = randomNums[randomIndex];
			int countIndex = randomIndex - 1;

			// Move the elements to right till we find the correct position for
			// the key
			for (; countIndex >= 0 && randomNums[countIndex] > key; countIndex--) {
				randomNums[countIndex + 1] = randomNums[countIndex];
				swapCount++;
			}
			randomNums[countIndex + 1] = key;
		}
		return swapCount;

	}

	/**
	 * Retrieve All the Values by connecting to database
	 * 
	 * @return List<SortResult>
	 */
	public List<SortResult> getAllResults() {

		List<SortResult> results = new ArrayList<>();
		List<SortedEntity> sortedNumber = dao.getAllSortNumbers();
		sortedNumber.forEach(resultRow -> {
			SortResult result = new SortResult(resultRow.getUnsortedNumbers(),
					resultRow.getSortedNumbers(),
					resultRow.getDuration(),
					resultRow.getSwapCount(),
					resultRow.getStatus());
			results.add(result);
		});

		return results;
	}
}
