package com.sonomawilliams.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonomawilliams.model.ZipCodeRange;
import com.sonomawilliams.service.ZipCodeRangeProcessor;
import com.sonomawilliams.service.ZipCodeRangeProcessorImpl;
import com.sonomawilliams.util.ZipCodeRangeConstants;


public class MainApplicationTests {

	private static Logger LOG = LoggerFactory
		      .getLogger(MainApplicationTests.class);
	
	private ZipCodeRangeProcessor zipCodeRangeProcessor;
	
	private List<ZipCodeRange> inputZipCodeRangeList;
	private List<ZipCodeRange> expectedZipCodeRangeList;
	
	@Before
	public void init() {
		LOG.info("startup");
		zipCodeRangeProcessor= new ZipCodeRangeProcessorImpl();
		
		expectedZipCodeRangeList = new ArrayList<ZipCodeRange>();
		expectedZipCodeRangeList.add(new ZipCodeRange(94133,94133));
		expectedZipCodeRangeList.add(new ZipCodeRange(94200,94399));
		expectedZipCodeRangeList.add(new ZipCodeRange(94400,94599));
		
		inputZipCodeRangeList = new ArrayList<ZipCodeRange>();
		inputZipCodeRangeList.add(new ZipCodeRange(94133,94133));
		inputZipCodeRangeList.add(new ZipCodeRange(94200,94299));
		inputZipCodeRangeList.add(new ZipCodeRange(94226,94399));
		inputZipCodeRangeList.add(new ZipCodeRange(94400,94499));
		inputZipCodeRangeList.add(new ZipCodeRange(94426,94599));
	}
	
	@Test
	public void testMergeZipCodeRanges() {
		LOG.info("startup "+inputZipCodeRangeList);
		
        List<ZipCodeRange> mergedZipCodeRangeList = zipCodeRangeProcessor.mergeZipCodeRanges(inputZipCodeRangeList);
        Assert.assertEquals(mergedZipCodeRangeList, expectedZipCodeRangeList);

	}

	
	@Test 
	public void testExtractZipCodeRanges() {
		 
		  String testData= "[94133,94133] [94200,94299] [94226,94399] [94400,94499] [94426,94599] ";
		  List<ZipCodeRange> extractedZipRanges = zipCodeRangeProcessor.extractZipCodeRanges(testData,ZipCodeRangeConstants.SQUARE_BRKT_REGEX);
		  Assert.assertEquals(extractedZipRanges, inputZipCodeRangeList);
	}
	 
	 
}
