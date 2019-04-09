package com.sortnumbers.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sortnumbers.model.SortResult;
import com.sortnumbers.service.SortService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SortController.class, secure = false)
public class SortControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	SortController controller;

	@MockBean
	SortService sortService;

	List<SortResult> result = null;

	@Before
	public void initialize() {
		result = new ArrayList<>();
		SortResult dummyValue = new SortResult("1,4,3", 
												"1,3,4", 
												0, 
												0, 
												"SUCCESS");
		result.add(dummyValue);
	}

	@Test
	public void testFor_PostingInValidPayLoad() throws Exception {

		SortResult dummyValue = new SortResult("1,4,3", 
												"1,3,4", 
												0, 
												0,
												"SUCCESS");
		result.add(dummyValue);
		String payload = "";

		Mockito.when(sortService.sort(Mockito.anyString()))
				.thenReturn(dummyValue);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/sortnumbers")
				.content(payload)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder)
							.andReturn();
		Assert.assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testFor_PostingEmptyValues() throws Exception {

		SortResult dummyValue = new SortResult("1,4,3", "1,3,4", 0, 0, "SUCCESS");
		result.add(dummyValue);
		String payload = "{\"unSortedNumbers\":\"\"}";
		Mockito.when(sortService.sort(Mockito.anyString())).thenReturn(dummyValue);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sortnumbers")
				.content(payload)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testFor_PostingCorrectValues() throws Exception {

		SortResult dummyValue = new SortResult("1,4,3", "1,3,4", 0, 0, "SUCCESS");
		result.add(dummyValue);
		String payload = "{\"unSortedNumbers\":\"1,4,3\"}";
		Mockito.when(sortService.sort(Mockito.anyString())).thenReturn(dummyValue);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sortnumbers")
				.content(payload)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void testFor_fetchingValuesFromDB() throws Exception {

		Mockito.when(sortService.getAllResults()).thenReturn(result);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sortnumbers")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).
				andReturn();
		String expected = "[{\"unSortedNumbers\":\"1,4,3\",\"sortedNumbers\":\"1,3,4\",\"duration\":0,\"swapCount\":0,\"status\":\"SUCCESS\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
