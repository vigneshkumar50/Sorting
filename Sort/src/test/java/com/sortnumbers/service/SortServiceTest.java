package com.sortnumbers.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sortnumbers.model.SortResult;
import com.sortnumbers.service.SortService;

import java.util.Arrays;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortServiceTest {

	@Autowired
	SortService sortService;
	
	@Test
	public void testSortPositive() {
		int[] sortingNumber = {1,2,3,4};
		SortResult sortResult = sortService.sort("4,3,2,1");
		Assert.assertEquals(Arrays.toString(sortingNumber),sortResult.getSortedNumbers());
	}
	
	@Test
	public void testSortNegative() {
		SortResult sortResult = sortService.sort("4,3,2,A");
		Assert.assertTrue(sortResult.getStatus().contains("Sort Failed"));
	}

	@Test
	public void testSortByRandomNumberZero() {
		int[] sortingNumber = {1,2,3,4};
		int count = sortService.sortByRandomNumber(sortingNumber);
		Assert.assertEquals(0,count);
	}
	
	@Test
	public void testSortByRandomNumber() {
		int[] sortingNumber = {4,3,2,1};
		int count = sortService.sortByRandomNumber(sortingNumber);
		Assert.assertEquals(6,count);
	}

}
