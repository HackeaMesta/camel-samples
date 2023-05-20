package com.chakray.CamelEncryptData.routes;

import com.chakray.CamelEncryptData.tools.DataEncryption;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleRouter extends RouteBuilder {
    @Autowired
    DataEncryption dataEncryption;

    @Override
    public void configure() throws Exception {
        from("timer:encrypt-generator-timer?period=5000")
                .log("encrypt-generator-log")
                .transform().constant("Message to encrypted Queue")
                .log("${body}")
                .marshal(dataEncryption.createEncryptor())
                .log("Encrypted Message: ${body}")
                .to("activemq:encrypt-queue");

        from("activemq:encrypt-queue")
                .log("Received encrypted message: ${body}")
                .unmarshal(dataEncryption.createEncryptor())
                .log("Decrypted message: ${body}")
                .to("log:decrypted-messages");
    }
}
