/**
 * 
 */
package com.sonomawilliams.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author kisho
 *
 */
public class ZipCodeRange {
	
	@Min(value = 10000, message = "Zip Code lowerBound should not be less than 10000")
	@Max(value = 99999, message = "Zip Code lowerBound should not be greater than 99999")
	private int lowerBound;
	
	@Min(value = 10000, message = "Zip Code upperBound should not be less than 10000")
	@Max(value = 99999, message = "Zip Code upperBound should not be greater than 99999")
	private int upperBound;
	
	public ZipCodeRange(int lowerBound, int upperBound) {
		// TODO Auto-generated constructor stub
		this.lowerBound=lowerBound;
		
		this.upperBound=upperBound;
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
	@Override
	public String toString() {
		return "ZipCodeRange [lowerBound=" + lowerBound + ", upperBound=" + upperBound + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true   
        if (obj == this) { 
            return true; 
        } 
  
        // Check if obj is an instance of ZipCodeRange or not 
        if (!(obj instanceof ZipCodeRange)) { 
            return false; 
        } 
          
        // typecast obj to ZipCodeRange 
        ZipCodeRange zipCodeRange = (ZipCodeRange) obj; 
          
        // Compare the data members and return accordingly  
        return Integer.compare(this.lowerBound, zipCodeRange.getLowerBound()) == 0
                && Integer.compare(this.upperBound, zipCodeRange.getUpperBound()) == 0; 
    } 

}
