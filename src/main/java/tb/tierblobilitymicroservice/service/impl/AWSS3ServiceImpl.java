package tb.tierblobilitymicroservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tb.tierblobilitymicroservice.service.api.AWSS3Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {
    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public List<String> getObjectsFromS3(String bucketName){
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(item ->{
            return item.getKey();
        }).collect(Collectors.toList());
        return list;
    }
}
