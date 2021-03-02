package nl.cc.task.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

//@Service
public class FileStoreService {

    private final AmazonS3 amazonS3;

    public FileStoreService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void save(String path, String fileName, Map<String, String> metaDada, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        Optional.ofNullable(metaDada).ifPresent(map -> {
            map.forEach(objectMetadata::addUserMetadata);
        });
        try {
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        }catch(AmazonServiceException amazonServiceException) {
            throw amazonServiceException;
        }

    }


}
