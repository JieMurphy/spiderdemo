package database;

import com.jie.service.FileService;
import org.junit.Test;

public class UserTest {

    @Test
    public void tika()
    {
        String path = "E:\\新建文件夹 (2)\\VS2012_ULT_chs\\";
        String fileName = "1.html";
        String filePath = path + fileName;
        FileService fileService = new FileService();
        String fileType = fileService.getFileType(filePath);
        String fileContext = fileService.getContext(filePath);
        System.out.println(fileType);
        System.out.println(fileContext);
    }
}
