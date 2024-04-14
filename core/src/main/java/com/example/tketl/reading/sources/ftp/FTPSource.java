package com.example.tketl.reading.sources.ftp;

import com.example.tketl.api.DTO.source.ConfigFTPDTO;
import com.example.tketl.processing.OutputFromStep;
import com.example.tketl.reading.sources.Source;
import org.apache.commons.net.ftp.FTPClient;

import java.util.List;

public class FTPSource extends Source {
    public List<OutputFromStep> read(ConfigFTPDTO configFTPDTO){
        FTPClient ftp = new FTPClient();
return null;
    }
}
