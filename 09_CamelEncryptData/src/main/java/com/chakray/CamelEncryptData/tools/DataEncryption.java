package com.chakray.CamelEncryptData.tools;


import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataEncryption {
    Logger log = LoggerFactory.getLogger(DataEncryption.class);

    @Autowired
    KeystoreProperties keystoreProperties;

    public CryptoDataFormat createEncryptor()
            throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException,
            UnrecoverableKeyException {

        String keyStoreType = keystoreProperties.getType();
        log.info("Key Type: {}", keyStoreType);

        String keyStoreFile = keystoreProperties.getFile();
        log.info("Key File: {}", keyStoreFile);

        String keyStoreAlias = keystoreProperties.getAlias();
        log.info("Key Alias: {}", keyStoreAlias);

        String keyStorePassword = keystoreProperties.getPassword();
        log.info("Key Password: {}", keyStorePassword);

        String keyStoreAlg = keystoreProperties.getAlg();
        log.info("Key Alg: {}", keyStoreAlg);

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        ClassLoader classLoader = getClass().getClassLoader();
        keyStore.load(classLoader.getResourceAsStream(
                keyStoreFile),
                keyStorePassword.toCharArray()
        );
        Key sharedKey = keyStore.getKey(
                keyStoreAlias,
                keyStorePassword.toCharArray()
        );

        return new CryptoDataFormat(keyStoreAlg, sharedKey);
    }
}
