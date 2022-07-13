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

    @Column(name="`userID`")
    private long userID;

    @Column(name="`fileName`")
    private String fileName;

    public FileData() {
        super();
    }

    public FileData(long userID, String fileName) {
        super();
        this.userID = userID;
        this.fileName = fileName;
    }

    public static FileData of(MyFile myFile) {
        return new FileData(myFile.getUserID(), myFile.getName());
    }
}
