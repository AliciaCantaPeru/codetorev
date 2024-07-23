/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.util;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface AmazonClientService {

    String uploadFile(MultipartFile image, String prefix) throws Exception;

    void updateFile(MultipartFile image, String nombre) throws Exception;

    void deleteFile(String imageUrlUrl) throws Exception;

}
