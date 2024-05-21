package com.matth.config;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.matth.entity.model.FtpConfig;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Matthiola incana
 * @create 2022/8/1 9:59
 */
@Data
@Component
@EnableConfigurationProperties(FtpConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FtpServerConfig {

    private final FtpConfig ftpConfig;

    public String upload(MultipartFile file) throws IOException {
        return this.upload(file.getName(),file.getInputStream());
    }

    public String upload(String fileName,InputStream inputStream) throws IOException {
        Ftp ftp = new Ftp(ftpConfig.getHost(),ftpConfig.getPort(),ftpConfig.getUsername(),ftpConfig.getPassword());
        ftp.setMode(FtpMode.Passive);
        ftp.upload(ftpConfig.getDestPath(),fileName,inputStream);
        ftp.close();
        return String.format("http://%s:%d%s%s",ftpConfig.getHost(),ftpConfig.getPort(),ftpConfig.getDestPath(),fileName);
    }

    public OutputStream download(String fileName, OutputStream outputStream) throws IOException {
        Ftp ftp = new Ftp(ftpConfig.getHost(),ftpConfig.getPort(),ftpConfig.getUsername(),ftpConfig.getPassword());
        ftp.download(ftpConfig.getDestPath(),fileName,outputStream, StandardCharsets.UTF_8);
        ftp.close();
        return outputStream;
    }

}
