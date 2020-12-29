package com.sonomawilliams.exception;

import java.util.Collection;

import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class ZipRangeException extends RuntimeException{
	
	  private Collection<String> messages;
	  
	  public ZipRangeException(String message){
	        super(message);
	    }
	  
	  @Override
	    public String getMessage(){
	        
		  String message;

	        if(this.messages!=null && !this.messages.isEmpty()){
	        	message="[";

	            for(String exMessage : this.messages){
	            	message+=exMessage+",";
	            }
	            message=  StringUtils.deleteAny(message, ",")+"]";
	        }else message= super.getMessage();

	        return message;
	    }
}
