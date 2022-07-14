package cloudServer.ports.web.api.file;

import cloudServer.application.services.ApiService;
import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<Resource> getFileById(@PathVariable long id,
                                                @RequestParam(required = false) boolean download) {
        String filename = fileService.getPathFromFile(id);

        if(filename == null || filename == "") {
            return ResponseEntity.notFound().build();
        }

        File file = new File(filename);

        // get MediaType for Response
        String mimeType;
        try {
            mimeType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            log.error("cannot detect mediaType of file {}. Set it to octet-stream", file.getAbsoluteFile());
            mimeType = "application/octet-stream";
        }

        MediaType mediaType = MediaType.valueOf(mimeType);

        HttpHeaders headers = new HttpHeaders();

        if(download) {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + "\"");
        }else {
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getName() + "\"");
        }
        try {
            ByteArrayResource res = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(mediaType)
                    .body(res);

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

        if(fileService.fileExists(file.getOriginalFilename())) {
            return ResponseEntity.badRequest().build();
        }

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
