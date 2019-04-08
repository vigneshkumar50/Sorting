package com.sortnumbers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sortnumbers.service.SortServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ SortServiceTest.class })
public class AllTests {

}
