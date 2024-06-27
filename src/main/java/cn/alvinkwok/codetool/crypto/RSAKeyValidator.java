package cn.alvinkwok.codetool.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Description
 * 公私钥匹配验证器
 *
 * @author alvinkwok
 * @since 2024/2/20
 */
public class RSAKeyValidator {

    public static void main(String[] args) {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJxJMzZ7UfjjNf6Fy8YzcFXiHoQrWBFHfY2dPkmOJhICda7Z/2Yhxsn8CBVx9wIsWfvDpXceDRsTii2KGf6UbWw6qXJAUSQBOIQl9SytuuYc9ZE/6jqsIF8jrbvSnhI2nmNxETHdTbtzLGfTi3ACu9zxHqJ6oyIdXOJdeS3FBW7ZAgMBAAECgYBZDAgnCbraAbO5EsjZZGOkO5NBoWSra71o2+gqhtjAODCPcNxpd+NJINYGSgoAbf7TSVVU+oVjIQ4BBofrGu0A8XYBwGDydgv8X9Qsr/r0KqAM8MDCU7zPfxcFXii2rOPAFXdJo6ZWe948lWM66kbL13cUXG1pxqd8O+Cskqbr0QJBAN4n/reGpXW5QwcsDqogFyOow1zcBExXoPlP5ddn+KffKzkw8frmikXdCYQSHhtHHdNJiJb9mmcjT7IGdsara1UCQQC0GEorycTJ76F1VCJhT7WzGkfMBbXy+dCIXmwdIgjDhr4DaA+ZkWNjEVkr1QwvR1jZBZ7PxfvY9gtx2Msq8d11AkEAlbks0XHcx8exZf8KbxwrnCvcZxQdjXotknmHtTJsgm9FCUl0fJ2tqUI8bJdtVQMMFd9kQCbxTfuddaUnnePf9QJAJf2tv9j7c2HinH6YFhEbkmGr3aUiwdPjIrFQe0DPund1JADSsaGS5rYlKnLx9BNH8dgWrVvlido1hdmm2jNVdQJAQzRQDzGaGkKNrOws/gq/PfUJzH0r3OYoNwgaURarNLTQA3EsxuBXzO1/t8i289mimtyV60CoOnQfPfcTZzPO+w==";
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcSTM2e1H44zX+hcvGM3BV4h6EK1gRR32NnT5JjiYSAnWu2f9mIcbJ/AgVcfcCLFn7w6V3Hg0bE4otihn+lG1sOqlyQFEkATiEJfUsrbrmHPWRP+o6rCBfI6270p4SNp5jcREx3U27cyxn04twArvc8R6ieqMiHVziXXktxQVu2QIDAQAB";
        boolean result = validate(pubKey, privateKey);
        System.out.println("验证结果" + result);
    }

    public static boolean validate(String publicKeyBase64, String privateKeyBase64) {
        try {
            // Convert base64 string back to PublicKey and PrivateKey
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Encrypt and decrypt test data
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            String testData = "Test Data";
            byte[] encryptedData = cipher.doFinal(testData.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedData = cipher.doFinal(encryptedData);

            // Compare original data and decrypted data
            return new String(decryptedData).equals(testData);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
