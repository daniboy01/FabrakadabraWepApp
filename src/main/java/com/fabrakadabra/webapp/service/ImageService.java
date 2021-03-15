package com.fabrakadabra.webapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService  {
//    private final String folder = "src/img/";
//    private PlayGroundImgRepository playGroundImgRepository;
//    private PlayGroundRepository playGroundRepository;
//
//    public void saveImageToFileSys(MultipartFile imageFile,Long id) throws Exception {
//        byte [] bytes = imageFile.getBytes();
//        Path path = Paths.get(folder + imageFile.getOriginalFilename());
//        Files.write(path,bytes);
//        saveImageToPlayGroundImageFileSys(imageFile.getOriginalFilename(),id);
//    }
//
//    private void saveImageToPlayGroundImageFileSys(String imageName,Long id){
//        File file = new File(folder + "/" + imageName);
//        String absolutePath = file.getAbsolutePath();
//        PlayGroundImg save = new PlayGroundImg();
//        save.setURL(absolutePath);
//        PlayGround playGround = playGroundRepository.findById(id).get();
//        save.setPlayGround(playGround);
//        playGround.getPlayGroundImgs().add(save);
//        playGroundImgRepository.save(save);
//        playGroundRepository.save(playGround);
//    }
//
//    public PlayGroundImgDTO saveImage(PlayGroundImgDTO dto,Long id){
//        PlayGroundImg img = playGroundImgRepository.save(imgDtotoModell(dto,id));
//        PlayGround playGround = playGroundRepository.findById(id).get();
//        playGround.getPlayGroundImgs().add(img);
//        playGroundRepository.save(playGround);
//        dto.setID(img.getId());
//        return dto;
//    }
//
//    private PlayGroundImg imgDtotoModell(PlayGroundImgDTO dto,Long playGroundId){
//        return PlayGroundImg.builder()
//                .id(dto.getID())
//                .URL(dto.getURL())
//                .playGround(playGroundRepository.findById(playGroundId).get())
//                .build();
//    }

}
