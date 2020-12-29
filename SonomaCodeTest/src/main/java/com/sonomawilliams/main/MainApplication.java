package com.sonomawilliams.main;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sonomawilliams.model.ZipCodeRange;
import com.sonomawilliams.service.ZipCodeRangeProcessor;
import com.sonomawilliams.util.ZipCodeRangeConstants;


@SpringBootApplication
@ComponentScan(basePackages = "com.sonomawilliams")
public class MainApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
		      .getLogger(MainApplication.class);
	
	@Autowired
	private ZipCodeRangeProcessor zipCodeRangeProcessor;

	

	public static void main(String[] args) {
		
		LOG.info("Starting the Application");
		SpringApplication.run(MainApplication.class, args);
		LOG.info("End of Application");
		        
	}	  

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String testData= "[94133,94133] [94200,94299] [94226,94399] [94400,94499] [94426,94599] ";
		//String testData= "";
		 List<ZipCodeRange> extractedZipRanges = zipCodeRangeProcessor.extractZipCodeRanges(testData,ZipCodeRangeConstants.SQUARE_BRKT_REGEX);
	     
		 List<ZipCodeRange> finalMergedZipRanges = zipCodeRangeProcessor.mergeZipCodeRanges(extractedZipRanges);
		 LOG.info("finalMergedZipRanges "+finalMergedZipRanges);
	}

}
