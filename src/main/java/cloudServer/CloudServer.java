package cloudServer;

import cloudServer.domain.file.FileData;
import cloudServer.domain.file.FileRepository;
import cloudServer.domain.file.service.FileService;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.UserRepository;
import cloudServer.domain.user.service.UserService;
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

		/*FileData f1 = new FileData(1, "cat.jpeg");
		FileData f2 = new FileData(1, "Unbenannt.PNG");
		FileData f3 = new FileData( 2, "tmp.txt");

		fileRepository.save(f1);
		fileRepository.save(f2);
		fileRepository.save(f3);*/
	}

}
