package com.postgreDemo.Util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import java.util.Base64;

@Configuration
public class DataBaseConfig {

    // 與 Python 程式碼相同的金鑰和 IV
    private static final String KEY = "32個字"; // 32 bytes
    private static final String IV = "16個字"; // 16 bytes

    @Value("${spring.datasource.url.encrypted}")
    private String encryptedUrl;

    @Bean
    public DataSource dataSource() throws Exception {
        // 解密連線字串
        String decryptedUrl = decrypt(encryptedUrl);

        // 配置 HikariCP 資料源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(decryptedUrl);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    private String decrypt(String encoded) throws Exception {
        // Base64 解碼
        byte[] ciphertext = Base64.getDecoder().decode(encoded);

        // 初始化 AES 解密器
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 解密並移除填充
        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted);
    }

}
