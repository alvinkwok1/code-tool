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
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCheG9On2w7Z9pTpnUdf2yMnhBfzosMSnoxLE3ES6GGgrXlWrtd4qdp+LOXONcf0icZN3X0G/JY8cX5ps7m5HdrzN8tyXW4VNzCS49gXRSen4J4up8j/s8qnkWBHaWPvRexTphC6voBvG6aLtQEA6SDJrXWf+341fCHSdlD2D+imGER+BXFXeyRFXMppM5oIiZZL2TtrijY+U6EUNYVZRMe+ZYJpFYkl4v3scsri22mamocJ+MSqtDG81HX9D/cG5wbay+VYol2tKLjqgHbFZLvBzMrd7M3K+SIOjdtAME+r2YOHamA3trH0pUfme5nm5XgowiakB10kvfVS8rAWn+rAgMBAAECggEAJo4LwdG2Na8UBhJCd6Mj56WcUkwOjoZAC/rArOTEMkqGpTeXcpD6mYadVzfmwf3bgR1piDqeENfWBkjqsV4PF8no5lVqyvNUbP4+w2smT8PVkv84nY8rp0j4Xp6WGC61+AvEtoHF5WE5ZY5V6Il4ICq/k3Kjf12SG7Op8ZWyiB+J66R8IyTIbVDpwpIycmGYDpFw42a1iIRY2P9r23uHZpkGFUsrjxjxdvqGptuTqQ64KQ9Ryp3LxVhVjOMJVXIOtUBb//r/ixn3x81wVK4w8aIMBLbdT9yyR67JkrawepW+4dXudcAMk7V26kYrZ3Uf95m1BAiCWQwoWVFHxgLvmQKBgQC7CoeLgWnS5a1joWH1NAOw5DeBEEvw22MEpj8lb8chVp6QlrjAZX5z9g92v6vE77GEzpWxAP9SWGYiyx/DqGmhJvhBZ4OBYwh82a5RboBD15Yd07cc1VuNfwf4EH3sQxu0CCCTaMfoTgatdZbV6SWfny1lRG1jBkhkzFwLbGxhGQKBgQDdAHjYma2U+hTY7MQJzlrNLF+gMERW35gVBV4/BjqvjK+WlZLQJ0AFGrissL40Ks98BpaGa0sdRWXRDbheMOABQ7m4uCQPQ+InI4qHbwlq8Cm4oTuA2XMv8F6q0WtP60mYl2Tbd5yr9FHL36+WEyaevuBAFIBYyfHZ0ooWrqfrYwKBgQCNqbLxuWnSPvztOdz8n5GwF5nmWdZ6rV84tUWccCWtHXLpZg62QejdoV8i/Jb3iOe2A9Wig3PeBXAOKDR9t3+Re/eJagJTLBPQo/qAWKYzPATJwIGvB/XYnH8Hpk4JdOZ1m7Y4j6LhUr+B/07n4zcIx8HVMld7KdYI6jNIv6zyeQKBgGIgcofh/GO4gocq4YRlHoYZHMB4je9wygsTXhxnvKjzs4cOFKjdtJxPmA39UAbDt2qfVjiK7Ugq5yFikSkpVFzbYFw3TrOEdabhLgIieDEGP9AdrtjwNCfl93KrF5Xdp8jb98lGm8bx7Vuzjzf4et5jbBinwbEqwXrw86VMmXx3AoGACkbt9n8FqYFHfmP/k9d1q5VZQPIZlye5XSjAjv339sYB3oSXylfloDkeNCw7OTX6vohSKjuPdPa1khHoRNVQYgKJ/+2XoIWswnZ+qZb4dwI6Xfn8hTdU+l8xbvnSlXsqIIX4UxgC7jjfk8NIIUcqBuk/6U8bxmTE6l+xUPwGS8o=";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoXhvTp9sO2faU6Z1HX9sjJ4QX86LDEp6MSxNxEuhhoK15Vq7XeKnafizlzjXH9InGTd19BvyWPHF+abO5uR3a8zfLcl1uFTcwkuPYF0Unp+CeLqfI/7PKp5FgR2lj70XsU6YQur6Abxumi7UBAOkgya11n/t+NXwh0nZQ9g/ophhEfgVxV3skRVzKaTOaCImWS9k7a4o2PlOhFDWFWUTHvmWCaRWJJeL97HLK4ttpmpqHCfjEqrQxvNR1/Q/3BucG2svlWKJdrSi46oB2xWS7wczK3ezNyvkiDo3bQDBPq9mDh2pgN7ax9KVH5nuZ5uV4KMImpAddJL31UvKwFp/qwIDAQAB";
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
