package cloudServer.ports.web.api.file;

import cloudServer.domain.file.MyFile;
import cloudServer.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cloudServer.ports.Constants.STORAGE_PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @GetMapping()
    public ResponseEntity<List<MyFile>> getFileNames(@RequestParam(required = false) Long userID) {
        List<MyFile> response = new ArrayList<>();
        if(userID != null) {
            response = fileService.getFileNamesFromUser(userID);
        }else {
            response = fileService.getFileNames();
        }

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
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            log.error(String.valueOf(e));
            return ResponseEntity.internalServerError().build();
        }

        HttpHeaders headers = new HttpHeaders();

        if(download) {
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        }else {
            headers.add("content-disposition", "inline;filename=" +file.getName());
        }

        //headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        //headers.add("Pragma", "no-cache");
        //headers.add("Expires", "0");

        ResponseEntity<Object>
                responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }


    @PostMapping()
    public ResponseEntity<Void> uploadFile(@RequestPart("file") MultipartFile file) {
        String currentDirectory = STORAGE_PATH + file.getOriginalFilename();
        File convFile = new File(currentDirectory);
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<MyFile> updateFile(@RequestBody MyFile toUpdate) {
        Optional<MyFile> file = fileService.getFileById(toUpdate.getId());

        if(!file.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fileService.updateFile(toUpdate));
    }

}
