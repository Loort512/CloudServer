package cloudServer.domain.file;

import cloudServer.ports.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {

    private long id;
    private String name;
    private long userID;
    private String url;


    public MyFile(String name, long userID) {
        this.name = name;
        this.userID = userID;
    }
    public static MyFile of(FileData file) {
        MyFile f = new MyFile();
        f.setId(file.getId());
        f.setName(file.getFileName());
        f.setUrl(Constants.FILE_HOST + "/" + file.getId());
        f.setUserID(file.getUserID());

        return f;
    }
}
