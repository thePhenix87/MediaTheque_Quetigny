/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Tarik
 */
@Named
@RequestScoped
public class FileUploadController {

    UploadedFile file;
    String destination;
    String newFileName;
    @Inject 
    LivreController livreController;
    public FileUploadController() {
        destination = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/resources/img/") + File.separator;
    }

    public void doUploadFile(FileUploadEvent event) throws IOException {
        file = event.getFile();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        newFileName = sdf.format(new Date()) + file.getFileName().substring(
                event.getFile().getFileName().lastIndexOf('.'));;
        try {
            FileInputStream in = (FileInputStream) file.getInputstream();
            FileOutputStream out = new FileOutputStream(destination + newFileName);
            byte[] buffer = new byte[(int) file.getSize()];
            int conteur = 0;
            while ((conteur = in.read(buffer)) != -1) {
                out.write(buffer, 0, conteur);
            }
            in.close();
            out.close();
            livreController.getLivre().setImage(newFileName);
            
            System.out.println(destination +  file.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }

       

    }

//----------------------- SETERS AND GETTERS ---------------------------    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

}
