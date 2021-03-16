package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.ProductImgDto;
import com.fabrakadabra.webapp.model.Product;
import com.fabrakadabra.webapp.model.ProductImg;
import com.fabrakadabra.webapp.repository.ProductImgRepository;
import com.fabrakadabra.webapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ImageService  {
    private final String folder = "src/img/";
    private ProductImgRepository productImgRepository;
    private ProductRepository productRepository;

    public void saveImageToFileSys(MultipartFile imageFile, Long id) throws Exception {
        byte [] bytes = imageFile.getBytes();
        String[] splittedName = imageFile.getOriginalFilename().split("\\.");
        Path path = Paths.get(folder + id + "." + splittedName[1]);
        Files.write(path,bytes);
        saveImageToProductFileSys(imageFile.getOriginalFilename(),id);
    }

    private void saveImageToProductFileSys(String imageName, Long id){
        File file = new File(folder + "/" + id);
        String absolutePath = file.getAbsolutePath();
        ProductImg save = new ProductImg();
        save.setURL(absolutePath);
        Product product = productRepository.findById(id).get();
        save.setProductId(product.getID());
        product.getImages().add(save);
        productImgRepository.save(save);
        productRepository.save(product);
    }

    public ProductImgDto saveImage(ProductImgDto dto, Long id){
        ProductImg img = productImgRepository.save(imgDtotoModell(dto,id));
        Product product = productRepository.findById(id).get();
        product.getImages().add(img);
        productRepository.save(product);
        dto.setID(img.getID());
        return dto;
    }

    private ProductImg imgDtotoModell(ProductImgDto dto,Long productID){
        ProductImg img = new ProductImg(dto.getURL(),productID);
        return img;
    }

}
