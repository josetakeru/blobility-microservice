package tb.tierblobilitymicroservice.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tb.tierblobilitymicroservice.service.api.AWSS3Service;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListFileController {

    @Autowired
    private AWSS3Service awss3Service;

    @Autowired
    private AmazonS3 amazonS3;

    @RequestMapping("/list-objects")
    public ResponseEntity<String> printObjects() {

        String xmlString = "<!DOCTYPE html><html><body><h1 style=\"color: green\">TIER Blobility</h1>";
        List<Bucket> buckets = amazonS3.listBuckets();

        for (Bucket b : buckets) {
            String bucketName = b.getName();

            xmlString += "<h2>Objects in bucket " + bucketName + ":</h2>";

            ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            List<String> list = objects.stream().map(item -> {
                return item.getKey();
            }).collect(Collectors.toList());

            xmlString += "<ul>";
            for (int i = 0; i < list.size(); i++) {
                xmlString += "<li>" + list.get(i) + "</li>";
            }
            xmlString += "</ul>";
        }
        xmlString += "</body></html>";
        ResponseEntity<String> objects = new ResponseEntity<String>(xmlString, HttpStatus.OK);

        return objects;
    }
}
