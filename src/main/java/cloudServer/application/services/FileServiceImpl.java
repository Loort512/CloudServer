package cloudServer.application.services;

import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cloudServer.ports.Constants.STORAGE_PATH;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<MyFile> getFileNames() {
        return null;
    }

    @Override
    public List<MyFile> getFileNamesFromUser(long userID) {
        return null;
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
}
