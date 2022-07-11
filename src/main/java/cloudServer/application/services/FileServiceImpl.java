package cloudServer.application.services;

import cloudServer.domain.file.FileData;
import cloudServer.domain.file.FileRepository;
import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.UserRepository;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cloudServer.ports.Constants.STORAGE_PATH;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final UserService userService;

    @Override
    public List<MyFile> getFileNames() {
        List<MyFile> res = new ArrayList<>();
        res.addAll(fileRepository.findAll().stream().map(fd -> MyFile.of(fd)).collect(Collectors.toList()));

        return res;
    }

    @Override
    public List<MyFile> getFileNamesFromUser(String token) {
        List<MyFile> files = new ArrayList<>();
        Optional<MyUser> user = userService.getUserByToken(token);
        if(user.isPresent()) {
            for(FileData a : fileRepository.findAllByUserID(user.get().getId())) {
                files.add(MyFile.of(a));
            }
        }
        return files;
    }

    @Override
    public Optional<MyFile> getFileById(long id) {
        return Optional.empty();
    }

    @Override
    public String getPathFromFile(long id) {
        return STORAGE_PATH + "Unbenannt.PNG";
    }

    @Override
    public MyFile updateFile(MyFile toUpdate) {
        return null;
    }

    @Override
    public boolean uploadFile(String fileName, long userID) {
        MyFile file = new MyFile(fileName, userID);
        fileRepository.save(FileData.of(file));

        return true;
    }
}
