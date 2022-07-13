package cloudServer.ports.web.api.file;

import cloudServer.application.services.ApiService;
import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cloudServer.ports.Constants.STORAGE_PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8080")//°°mr°° TODO: http://localhost:8080
public class FileController {

    private final FileService fileService;
    private final HttpSession session;

    private final ApiService apiService;

    @GetMapping()
    public ResponseEntity<List<MyFile>> getFileNames() {
        List<MyFile> response = new ArrayList<>();
        int uuid = apiService.getUserIdFromSession(session);

        response.addAll(fileService.getFileNamesFromUser(uuid));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFileById(@PathVariable long id,
                                              @RequestParam(required = false) boolean download) {
        String filename = fileService.getPathFromFile(id);

        if(filename == null || filename == "") {
            return ResponseEntity.notFound().build();
        }

        File file = new File(filename);
        FileInputStream resource = null;
        try {
            resource = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            resource.read(arr);
            resource.close();

            return ResponseEntity.ok().
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + "\"").
                    body(new ByteArrayResource(arr));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return ResponseEntity.internalServerError().build();
    }


    @PostMapping()
    public ResponseEntity<Void> uploadFile(@RequestPart("file") MultipartFile file) {
        int userID = apiService.getUserIdFromSession(session);

        if(!fileService.uploadFile(file, userID)) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping()
    public ResponseEntity<MyFile> updateFile(@RequestBody MyFile toUpdate) {
        long sessionUID = apiService.getUserIdFromSession(session);

        Optional<MyFile> file = fileService.getFileById(toUpdate.getId());

        if(!file.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // just update if it's the user's file
        if(file.get().getUserID() != sessionUID) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(fileService.updateFile(toUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable long id) {
        boolean success = fileService.deleteFile(id);

        if(!success) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
