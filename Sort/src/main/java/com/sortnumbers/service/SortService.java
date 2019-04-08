package com.sortnumbers.service;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sortnumbers.constants.Constants;
import com.sortnumbers.model.SortResult;

/**
 * Sort a given amount of numerical values by randomly change position of values 
 * in ascending order implemented by using insertion sort algorithm. 
 * Calculates the sorting time duration in Nano Seconds. 
 * Count the changes of position.
 * 
 * @author vigneshkumar G 
 */
@Service
public class SortService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	

	/**
	 * @param randomNumbers
	 * @return SortResult
	 */
	public SortResult sort(String randomNumbers) {
		int[] arrayOfRandomNumbers = null;
		try {		
			arrayOfRandomNumbers = Arrays.stream(randomNumbers.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
		} catch (NumberFormatException e) {
			logger.error(Constants.NUMERIC_VALUE_MSG + randomNumbers);
			return new SortResult(randomNumbers, "", 0, 0, (Constants.FAILURE_STATUS+Constants.GIVEN_VALUE+ randomNumbers+Constants.INVALID_NUMBER));
		}
		
		String unSortedNumbers = Arrays.toString(arrayOfRandomNumbers);
		LocalTime startTime = LocalTime.now();
		// invoke sort
		int swapCount = sortByRandomNumber(arrayOfRandomNumbers);
		LocalTime endTime = LocalTime.now();
		
		// measures the duration between startTime and endTime.
		Duration duration = Duration.between(startTime, endTime);
		
		return new SortResult(unSortedNumbers, Arrays.toString(arrayOfRandomNumbers), duration.getNano(), swapCount, Constants.SUCCESS_STATUS);
	}

	/**
	 * @param randomNums
	 * @return swapCount
	 */
	public int sortByRandomNumber(int randomNums[]) {
		int swapCount = 0;
		Random random = new Random(randomNums.length);

		// traverse the array elements until sorted in ascending order
		while (!ArrayUtils.isSorted(randomNums)) {
			int randomIndex = random.nextInt(randomNums.length);

			int key = randomNums[randomIndex];
			int j = randomIndex - 1;

			// Move the elements to right till we find the correct position for
			// the key
			for (; j >= 0 && randomNums[j] > key; j--) {
				randomNums[j + 1] = randomNums[j];
				swapCount++;
			}
			randomNums[j + 1] = key;
		}
		return swapCount;

	}

}
