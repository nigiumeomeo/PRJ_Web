package utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageUploadUtil {

    public static String uploadImage(Part imgFilePart, ServletContext context) throws IOException {
        if (imgFilePart != null && imgFilePart.getSize() > 0) {
            String fileName = Paths.get(imgFilePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = context.getRealPath("/") + "/views/client/asset/img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            imgFilePart.write(uploadPath + "/" + fileName);
            return "/views/client/asset/img/" + fileName;
        }
        return null;
    }
}
