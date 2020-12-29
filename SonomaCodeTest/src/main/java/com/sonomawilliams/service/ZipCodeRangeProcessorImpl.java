package com.sonomawilliams.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.sonomawilliams.exception.ZipRangeException;
import com.sonomawilliams.model.ZipCodeRange;
import com.sonomawilliams.util.ZipRangeComparator;
import com.sonomawilliams.util.ZipCodeRangeConstants;

@Service
public class ZipCodeRangeProcessorImpl implements ZipCodeRangeProcessor {
	

	@Override
	public List<ZipCodeRange> mergeZipCodeRanges(List<ZipCodeRange> rangeList) {
		// TODO Auto-generated method stub
		  
				// Check if given list is not empty 
		        if (rangeList.size() <= 0)  
		           throw new ZipRangeException(ZipCodeRangeConstants.EMPTY_ZIPRANGE_EXCEPTION_MSG);  
		    
		        // Create an empty stack of ZipCodeRange  
		        Stack<ZipCodeRange> zipCodeRangeStack=new Stack<>(); 
		    
		        // sort the ZipCodeRanges by ascending order of lower bound  
		        Collections.sort(rangeList,new ZipRangeComparator()); 
		        
		        // push the first ZipCodeRange to stack  
		        zipCodeRangeStack.push(rangeList.get(0)); 
		        // iterate through the range list and merge if necessary  
		        for (ZipCodeRange zipCodeRangeFromList : rangeList)  
		        {  
		            // get top ZipCodeRange from stack  
		        	ZipCodeRange zipCodeRangeFromStack = zipCodeRangeStack.peek();  
		        	
					/*
					 * if current ZipCodeRange is not overlapping with stack's top zipCodeRange, push it to the
					 * stack, otherwise check if upper bound of stack's top zipCodeRange is less than current list's zip code range
					 */            
		        	if (zipCodeRangeFromStack.getUpperBound() < zipCodeRangeFromList.getLowerBound())
		            {
		        		zipCodeRangeStack.push(zipCodeRangeFromList);  
		            }
		            else if (zipCodeRangeFromStack.getUpperBound() < zipCodeRangeFromList.getUpperBound())  
		            {  
		            	zipCodeRangeFromStack.setUpperBound(  zipCodeRangeFromList.getUpperBound()) ;  
		                zipCodeRangeStack.pop();  
		                zipCodeRangeStack.push(zipCodeRangeFromStack);  
		            }  
		        }  
		        List<ZipCodeRange> mergedZipCodeRangeList = new ArrayList<ZipCodeRange>(zipCodeRangeStack);
		        
		    return mergedZipCodeRangeList;
	}

	@Override
	public List<ZipCodeRange> extractZipCodeRanges(String inputData,String pattern) {
		// TODO Auto-generated method stub
		  
		  if(inputData==null || inputData.isEmpty()){
			  throw new ZipRangeException(ZipCodeRangeConstants.EMPTY_DATA_EXCEPTION_MSG);  
			  
		  }
		  	//Creates a pattern with given regular expression
	        Pattern regExPattern = Pattern.compile(pattern);
	        //Creates a pattern matcher with given matcher applied to inputData
	        Matcher matcher = regExPattern.matcher(inputData);
	        ZipCodeRange zipCodeRange= null;
	        List<ZipCodeRange> zipCodeRangeList =new ArrayList<ZipCodeRange>();
	        //iterates through matches and creates ZipCodeRange List
	        while(matcher.find()) {
	            String[] zipCodeSet=matcher.group(1).split(",");
	            zipCodeRange=  new ZipCodeRange(Integer.parseInt(zipCodeSet[0]),Integer.parseInt(zipCodeSet[1]));
	            validateZipCodeRange(zipCodeRange);
	            zipCodeRangeList.add(zipCodeRange);
	        }
		return zipCodeRangeList;
	}
	//throws exception if zip code range lower/upper bound length is less than 5
	@Override
	public void validateZipCodeRange(ZipCodeRange zipCodeRange) {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<ZipCodeRange>> violations = validator.validate(zipCodeRange);
	    if (!violations.isEmpty()) {
	      throw new ConstraintViolationException(violations);
	    }
	  }
}
