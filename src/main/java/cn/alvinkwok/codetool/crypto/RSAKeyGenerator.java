package cn.alvinkwok.codetool.crypto;


import java.security.*;
import java.util.Base64;

/**
 * Description
 * RSA公钥生成工具
 *
 * @author alvinkwok
 * @since 2024/2/20
 */
public class RSAKeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println("Public Key: " + publicKeyBase64);
        System.out.println("Private Key: " + privateKeyBase64);
    }
}
