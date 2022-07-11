package cloudServer.domain.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "FILE")
@Data
@AllArgsConstructor
public class FileData {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="`pathToFile`")
    private String pathToFile;

    @Column(name="`userID`")
    private long userID;

    @Column(name="`fileNAme`")
    private String fileName;

    public FileData() {
        super();
    }

    public FileData(String pathToFile, long userID, String fileName) {
        super();
        this.pathToFile = pathToFile;
        this.userID = userID;
        this.fileName = fileName;
    }

    public static FileData of(MyFile myFile) {
        return new FileData("pathToFile", myFile.getUserID(), myFile.getName());
    }
}
