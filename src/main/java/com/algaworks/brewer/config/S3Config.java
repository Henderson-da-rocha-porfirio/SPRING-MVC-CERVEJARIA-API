package com.algaworks.brewer.config;

import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.regions.*;
import com.amazonaws.services.s3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.*;

@Profile("prod")
@Configuration
@PropertySource(value = { "file://${HOME}/.brewer-s3.properties" }, ignoreResourceNotFound = true)
public class S3Config {
	
	@Autowired
	private Environment env;

	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials credenciais = new BasicAWSCredentials(
				env.getProperty("AWS_ACCESS_KEY_ID"), env.getProperty("AWS_SECRET_ACCESS_KEY"));
		AmazonS3 amazonS3 = new AmazonS3Client(credenciais, new ClientConfiguration());
		Region regiao = Region.getRegion(Regions.US_EAST_1);
		amazonS3.setRegion(regiao);
		return amazonS3;
	}
	
}

	
//	@Bean
//	public AmazonS3 amazonS3() {
//		BasicAWSCredentials credenciais = new BasicAWSCredentials(
//				env.getProperty("AWS_ACCESS_KEY_ID"), 
//				env.getProperty("AWS_SECRET_ACCESS_KEY"));
//		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(credenciais))
//				.withRegion("US_EAST_1")
//				.build();
//		return amazonS3;
//	}

//}
