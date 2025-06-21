package linhlt.project.backend_service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendServiceApplication {

//	@Value("${jwt.secretkey}")
//	private String secretkey;

	public static void main(String[] args) {
		SpringApplication.run(BackendServiceApplication.class, args);
	}

//	@PostConstruct
//	private void test() {
//		System.out.println("secretkey: " + secretkey);
//	}
}
