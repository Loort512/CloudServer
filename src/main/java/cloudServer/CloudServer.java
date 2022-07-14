package cloudServer;

import cloudServer.domain.file.FileRepository;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class CloudServer {

	private final UserRepository userRepository;
	private final FileRepository fileRepository;

	public static void main(String[] args) {
		SpringApplication.run(CloudServer.class, args);
	}

	@PostConstruct()
	public void initTestData() {
		MyUser u1 = new MyUser("Mark", "abc", "token-token-token", "Mark", "Muster", true);
		MyUser u2 = new MyUser("Marion", "abc", "token1-token1-token1", "Marion", "Bauer", false);
		userRepository.save(u1);
		userRepository.save(u2);
	}

}
