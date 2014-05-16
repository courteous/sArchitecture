package org.syncServer.Controller.REST;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {


    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public String provideUploadInfo() {
        return "fileUpload";
    }
    

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("myName") String myName, 
            @RequestParam("myFile") MultipartFile myFile){
        if (!myFile.isEmpty()) {
            try {
                byte[] bytes = myFile.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(myName + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "Successfully uploaded " + myName + " into " + myName + "-uploaded !";
            } catch (Exception e) {
                return "Failed to upload " + myName + " => " + e.getMessage();
            }
        } else {
            return "Failed to upload " + myName + " empty file.";
        }
    }
}
