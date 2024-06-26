package com.kitri.web_project.service;

import com.kitri.web_project.mappers.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PetService {
    @Autowired
    PetMapper petMapper;


    private static final String frontendUrl = "http://localhost:3000";
//  private static final String frontendUrl = System.getenv("FRONTEND_URL");

    private static final String uploadRootPath = "D:/imageStore";
//        private static final String uploadRootPath = "/app/images";
    public void deletePet(long petId){
        String imgPath = String.valueOf(petMapper.getPetImages(petId));
        petMapper.deletePet(petId);
        String fullPath = uploadRootPath + imgPath;
        File file = new File(fullPath);
        if (file.exists()) {
            try {
                if (file.delete()) {
                    System.out.println("Success: Image deleted");
                } else {
                    System.out.println("Failed: Image could not be deleted");
                }
            } catch (SecurityException e) {
                System.out.println("Failed: Security Exception occurred while deleting image");
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed: Image not found at path " + fullPath);
        }
    }

}
