package com.postgreDemo.Util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Autowired
    private Decryptor decryptor;

    @Value("${spring.datasource.url.encrypted}")
    private String encryptedUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dataBaseDriver;

    @Bean
    public DataSource dataSource() {
        try {
            // 解密連線字串
            String decryptedUrl = decryptor.decrypt(encryptedUrl);

            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(decryptedUrl);
            dataSource.setDriverClassName(dataBaseDriver);
            return dataSource;

        } catch (Exception e) {
            // 或者保留拋出，但提供詳細資訊
            throw new IllegalStateException("無法建立資料庫連線，請檢查加密參數與連線字串是否正確！", e);
        }
    }

}
