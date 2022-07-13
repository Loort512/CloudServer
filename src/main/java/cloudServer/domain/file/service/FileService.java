package cloudServer.domain.file.service;

import cloudServer.domain.file.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public interface FileService {

    List<MyFile> getFileNames();

    List<MyFile> getFileNamesFromUser(long userID);

    Optional<MyFile> getFileById(long id);


    /**
     * returns Path to File on server Storage
     * */
    String getPathFromFile(long id);

    MyFile updateFile(MyFile toUpdate);

    boolean uploadFile(MultipartFile file, long userID);

    boolean deleteFile(long fileId);
}
