package cloudServer.domain.file;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long>{

    FileData save(FileData fileData);

    List<FileData> findAll();

    List<FileData> findAllByUserID(long userID);
}
