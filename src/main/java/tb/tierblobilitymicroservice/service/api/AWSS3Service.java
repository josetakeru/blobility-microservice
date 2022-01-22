package tb.tierblobilitymicroservice.service.api;

import java.util.List;

public interface AWSS3Service {
    List<String> getObjectsFromS3(String bucketName);
}
