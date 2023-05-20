# Generate Secret keys

## Create AES 256 key (Recommendable)

```
$ keytool -genseckey -alias "CamelKey" -keyalg AES -keysize 256 -keystore camel.pfx -storetype PKCS12 -storepass "C4m3l$"
```

## Create SHA 512 Key

Note: HMAC-SHA512 could fail in JDK 8

```
$ keytool -genseckey -alias "camel512" -keyalg HMACSHA512 -keysize 512 -keystore camel.pfx -storetype PKCS12 -storepass "C4m3l$"
```

File camel.pfx should be stored in {CAMEL}/src/main/resources/