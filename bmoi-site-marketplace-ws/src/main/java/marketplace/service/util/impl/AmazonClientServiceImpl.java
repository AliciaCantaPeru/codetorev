/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.util.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import javax.annotation.PostConstruct;
import marketplace.service.util.AmazonClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Service
public class AmazonClientServiceImpl implements AmazonClientService {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    @Override
    public String uploadFile(MultipartFile image, String prefix) throws Exception {
        String fileUrl = "";
        File file = convertMultiPartToFile(image);
        String fileName = generateFileName(image, prefix);
        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        uploadFileTos3bucket(fileName, file);
        file.delete();
        return fileUrl;
    }

    @Override
    public void updateFile(MultipartFile image, String nombre) throws Exception {
        File file = convertMultiPartToFile(image);
        uploadFileTos3bucket(nombre, file);
        file.delete();
    }

    @Override
    public void deleteFile(String imageUrl) throws Exception {
        String[] split = imageUrl.split("/");
        String fileName = split[split.length - 2] + "/" + split[split.length - 1];
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private File convertMultiPartToFile(MultipartFile file) throws Exception {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart, String prefix) {
        return prefix + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

}
