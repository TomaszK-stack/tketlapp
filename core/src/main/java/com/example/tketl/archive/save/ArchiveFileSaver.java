package com.example.tketl.archive.save;

import com.example.tketl.archive.save.ArchiveSaveType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ArchiveFileSaver {

    @Value("${archive.destination}")
    private String path;




    public void save(File file, String fileName, ArchiveSaveType saveType) throws IOException {

        BufferedReader bfr = new BufferedReader(new FileReader(file));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(path + "\\"
                + saveType.toString().toLowerCase()+
                "\\" + fileName)));
        String line = "";

        while ((line = bfr.readLine()) != null){
            bfw.write(line);
            bfw.write("\n");
        }
        bfr.close();
    }
    public void save(StringBuilder data, String fileName, ArchiveSaveType saveType) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(path + "\\"
                + saveType.toString().toLowerCase()+
                "\\" + fileName)));
        bfw.write(data.toString());
        bfw.close();
    }

}
