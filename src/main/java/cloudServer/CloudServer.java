package cloudServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CloudServer {

	public static void main(String[] args) {
		SpringApplication.run(CloudServer.class, args);
	}


}
