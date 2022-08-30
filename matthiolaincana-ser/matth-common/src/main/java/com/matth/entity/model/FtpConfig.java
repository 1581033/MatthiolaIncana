package com.matth.entity.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2022/8/1 11:48
 */
@Data
@ConfigurationProperties(prefix = "spring.ftp")
public class FtpConfig implements Serializable {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String destPath;

}
