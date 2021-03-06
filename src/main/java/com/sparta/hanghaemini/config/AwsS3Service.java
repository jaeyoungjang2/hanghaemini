package com.sparta.hanghaemini.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:aws.yml")
@Service
public class AwsS3Service {

  private final AmazonS3Client amazonS3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  public String uploadFileV1(String category, MultipartFile multipartFile) {
    validateFileExists(multipartFile);

    String fileName = CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(multipartFile.getContentType());

    try (InputStream inputStream = multipartFile.getInputStream()) {
      amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (IOException e) {
      throw new IllegalArgumentException("용량초과");
    }

    return amazonS3Client.getUrl(bucketName, fileName).toString();
  }

	private void validateFileExists(MultipartFile multipartFile) {
    if (multipartFile.isEmpty()) {
      throw new IllegalArgumentException("멀티파트파일 없음");
    }
  }
}