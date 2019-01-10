package br.com.carlos.spring.mvc.infra;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Classe responsável pela gravação de arquivos.
 *
 * @author Carlos H
 */
@Component
public class FileSaver {

    @Autowired
    private HttpServletRequest request;

    public String writeFile(String baseFolder, MultipartFile file) {

        String absolutePath = request.getServletContext().getRealPath("/" + baseFolder);
        String path = absolutePath + "/" + file.getOriginalFilename();

        try {
            file.transferTo(new File(path));
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return baseFolder + "/" + file.getOriginalFilename();

    }

}
