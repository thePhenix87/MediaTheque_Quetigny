package org.primefaces.showcase.view.file;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
public class TelechargerImage{
 
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Le téléchargement", event.getFile().getFileName() + " est terminé.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
