package cloudServer.application.services;

import cloudServer.domain.file.FileData;
import cloudServer.domain.file.FileRepository;
import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import cloudServer.ports.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cloudServer.ports.Constants.STORAGE_PATH;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public List<MyFile> getFileNamesFromUser(long userID) {
        List<MyFile> files = new ArrayList<>();
        for(FileData a : fileRepository.findAllByUserID(userID)) {
            files.add(MyFile.of(a));
        }
        return files;
    }

    @Override
    public Optional<MyFile> getFileById(long id) {
        Optional<FileData> fileData = fileRepository.findById(id);

        if(fileData.isPresent()) {
            return Optional.of(MyFile.of(fileData.get()));
        }

        return Optional.empty();
    }

    @Override
    public String getPathFromFile(long id) {
        return STORAGE_PATH + "Unbenannt.PNG";
    }

    @Override
    public MyFile updateFile(MyFile toUpdate) {
        Optional<FileData> fileData = fileRepository.findById(toUpdate.getId());

        if(!fileData.isPresent()) {
            return null;
        }


        File oldFile = new File(fileData.get().getFileName());
        Path source = Paths.get(STORAGE_PATH + fileData.get().getFileName());

        try {
            Files.move(source, source.resolveSibling(toUpdate.getName()));
            fileData.get().setFileName(toUpdate.getName());
            fileRepository.save(fileData.get());
            log.info("Updated Document id: {} to {}",
                    fileData.get().getId(),
                    fileData.get().getFileName());
        } catch (IOException e) {
            log.error("Cannot update name from doc id: {} oldName: {}",
                    fileData.get().getId(),
                    fileData.get().getFileName(),
                    e);
            return null;
        }


        return MyFile.of(fileData.get());
    }

    @Override
    public boolean uploadFile(MultipartFile file, long userID) {
        String currentDirectory = STORAGE_PATH + file.getOriginalFilename();
        File convFile = new File(currentDirectory);
        try {
            file.transferTo(convFile);
            MyFile myFile = new MyFile(file.getOriginalFilename(), userID);
            fileRepository.save(FileData.of(myFile));
        } catch (IOException e) {
            return false;
        }


        return true;
    }

    @Override
    public boolean deleteFile(long fileId) {
        Optional<FileData> fileData = fileRepository.findById(fileId);

        if(!fileData.isPresent()) {
            log.error("File with id {} not found. Deleting Db entry", fileId);
            return false;
        }

        // delete in DB
        fileRepository.delete(fileData.get());

        // delete physical File
        Path path = Paths.get(STORAGE_PATH + fileData.get().getFileName());
        File file = new File(path.toString());

        if(!file.delete()) {
            log.error("Cannot delete File {}", path.toAbsolutePath());
            return false;
        }

        log.debug("File {} deleted", path.toAbsolutePath());
        return true;
    }
}
