package com.sonomawilliams.service;

import java.util.List;

import com.sonomawilliams.model.ZipCodeRange;

public interface ZipCodeRangeProcessor {
	
	 /**
     * <p>
     *     This method will parse the input array of string zip ranges and validate
     *     each zip range element. It returns a set of valid {@link ZipRange}s
     * </p>
     *
     * @param array of string zip ranges
     * @return the consolidated zip ranges
     */
	 List<ZipCodeRange> mergeZipCodeRanges( List<ZipCodeRange> rangeList );
	 List<ZipCodeRange> extractZipCodeRanges(String inputData, String pattern);
	 void validateZipCodeRange(ZipCodeRange zipCodeRange);
}
