package com.sonomawilliams.util;

import java.util.Comparator;

import com.sonomawilliams.model.ZipCodeRange;

public class ZipRangeComparator implements Comparator<ZipCodeRange>{

	@Override
	public int compare(ZipCodeRange range1, ZipCodeRange range2) {
		// TODO Auto-generated method stub
		 return range1.getLowerBound()-range2.getLowerBound(); 
	}

}
