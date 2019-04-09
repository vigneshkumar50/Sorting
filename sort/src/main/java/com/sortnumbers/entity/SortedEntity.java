package com.sortnumbers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "Result")
public class SortedEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String unsortedNumbers;
	private String sortedNumbers;
	private long duration;
	private int swapCount;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnsortedNumbers() {
		return unsortedNumbers;
	}
	public void setUnsortedNumbers(String unsortedNumbers) {
		this.unsortedNumbers = unsortedNumbers;
	}
	public String getSortedNumbers() {
		return sortedNumbers;
	}
	public void setSortedNumbers(String sortedNumbers) {
		this.sortedNumbers = sortedNumbers;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public int getSwapCount() {
		return swapCount;
	}
	public void setSwapCount(int swapCount) {
		this.swapCount = swapCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
