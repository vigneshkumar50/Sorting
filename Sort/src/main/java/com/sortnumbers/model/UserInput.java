package com.sortnumbers.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserInput {

	@NotNull
	@NotEmpty
	private String unSortedNumbers;
	/**
	 * @return the unSortedNumbers
	 */
	public String getUnSortedNumbers() {
		return unSortedNumbers;
	}

	/**
	 * @param unsortednumbers
	 *            the unSortedNumbers to set
	 */
	public void setUnsortednumbers(String unSortedNumbers) {
		this.unSortedNumbers = unSortedNumbers;
	}

}
