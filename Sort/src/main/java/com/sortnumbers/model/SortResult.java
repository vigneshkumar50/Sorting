package com.sortnumbers.model;

public class SortResult {

	private String unSortedNumbers;
	private String sortedNumbers;
	private long duration;
	private int swapCount;
	private String status;

	/**
	 * @param unSortedNumbers
	 * @param sortedNumbers
	 * @param duration
	 * @param swapCount
	 * @param status
	 */
	public SortResult(String unSortedNumbers,
					 String sortedNumbers,
					 long duration, 
					 int swapCount,
					 String status) {

		this.unSortedNumbers = unSortedNumbers;
		this.sortedNumbers = sortedNumbers;
		this.duration = duration;
		this.swapCount = swapCount;
		this.status = status;
	}

	/**
	 * @return the unSortedNumbers
	 */
	public String getUnSortedNumbers() {
		return unSortedNumbers;
	}

	/**
	 * @param unSortedNumbers the unSortedNumbers to set
	 */
	public void setUnSortedNumbers(String unSortedNumbers) {
		this.unSortedNumbers = unSortedNumbers;
	}

	/**
	 * @return the sortedNumbers
	 */
	public String getSortedNumbers() {
		return sortedNumbers;
	}

	/**
	 * @param sortedNumbers the sortedNumbers to set
	 */
	public void setSortedNumbers(String sortedNumbers) {
		this.sortedNumbers = sortedNumbers;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return the swapCount
	 */
	public int getSwapCount() {
		return swapCount;
	}

	/**
	 * @param swapCount the swapCount to set
	 */
	public void setSwapCount(int swapCount) {
		this.swapCount = swapCount;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "Sort Result : (UnSorted Numbers=" + unSortedNumbers + ","
				+ " Sorted Numbers=" + sortedNumbers
				+ ", Duration=" + duration + " ms" 
				+ ", Changes of Positions=" + swapCount + ")";
	}
}
