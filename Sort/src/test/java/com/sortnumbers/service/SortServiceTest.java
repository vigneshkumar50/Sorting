package com.sortnumbers.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sortnumbers.dao.SortDao;
import com.sortnumbers.entity.SortedEntity;
import com.sortnumbers.model.SortResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortServiceTest {

	@Autowired
	SortService sortService;

	@Mock
	SortDao dao;

	@Mock
	SortedEntity number;

	@Test
	public void testFor_ValidData() {
		int[] ExpectedsortingNumber = { 1, 2, 3, 4 };
		Mockito.doNothing().when(dao).saveOrUpdate(number);
		SortResult sortResult = sortService.sort("4,3,2,1");
		Assert.assertEquals(Arrays.toString(ExpectedsortingNumber), sortResult.getSortedNumbers());
		Mockito.verify(dao, Mockito.times(0)).saveOrUpdate(number);
	}

	@Test
	public void testFor_InvalidData() {
		SortResult sortResult = sortService.sort("4,3,2,A");
		Assert.assertTrue(sortResult.getStatus().contains("Sort Failed"));
	}

}
