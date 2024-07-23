/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 *
 * @author fsolis
 */
@Log4j2
@Component
public class Encriptador {

    private final static String SECRETKEY = "@solmit@2016ADM";
    private final static String SHA_256 = "SHA-256";
    private final static String UTF_8 = "utf-8";
    private final static String UTF_8_ = "UTF-8";
    private final static String DESede = "DESede";
    private final static String MD5 = "MD5";
    private final static String VACIO = "";
    private final static int N = 24;

    public static byte[] decodificado(String archivoCodificado) {
        byte[] decodificado = null;
        try {
            decodificado = Base64.decodeBase64(archivoCodificado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return decodificado;
    }

    public static void main(String[] args) throws Exception {
        final Encriptador encriptador = new Encriptador();
        System.out.println(encriptador.desencriptarTexto("IslqoQoRhZcD0uH/xnVOWwzHmxCUIIt/303l7GgNin4="));
    }

    private Cipher decipherEncrypt_SHA;
    private Cipher decipherEncrypt_MD5;
    private Cipher decipherDecript_SHA;
    private Cipher decipherDecript_MD5;

    public Encriptador() {
        try {
            this.decipherEncrypt_SHA = obtenerCipher(MessageDigest.getInstance(SHA_256), Cipher.ENCRYPT_MODE);
            this.decipherEncrypt_MD5 = obtenerCipher(MessageDigest.getInstance(MD5), Cipher.ENCRYPT_MODE);
            this.decipherDecript_SHA = obtenerCipher(MessageDigest.getInstance(SHA_256), Cipher.DECRYPT_MODE);
            this.decipherDecript_MD5 = obtenerCipher(MessageDigest.getInstance(MD5), Cipher.DECRYPT_MODE);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    public String encriptarTexto(String texto) {
        texto = encriptarMD5(texto);
        texto = encriptarSHA256(texto);
        return texto;
    }

    public String encriptarID(String texto) {
        texto = encriptarSHA256(texto);
        return texto;
    }

    public String encriptarBigDecimal(BigDecimal numero) {
        String texto = numero.toPlainString();
        texto = encriptarTexto(texto);
        return texto;
    }

    public BigDecimal desencriptarBigDecimal(String texto) {
        texto = desencriptarTexto(texto);
        return new BigDecimal(texto);
    }

    public String desencriptarTexto(String texto) {
        texto = desencriptarSHA256(texto);
        texto = desencriptarMD5(texto);
        return texto;
    }

    public String desencriptarID(String texto) {
        texto = desencriptarSHA256(texto);
        return texto;
    }

    public int desencriptarIDString(String texto) {
        texto = desencriptarSHA256(texto);
        return new Integer(texto);
    }

    public String codificado(File file) throws Exception {
        String codificado = null;
        try {
            byte[] fileArray = new byte[(int) file.length()];
            InputStream inputStream = new FileInputStream(file);
            inputStream.read(fileArray);
            codificado = Base64.encodeBase64String(fileArray);
            inputStream.close();

        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
        }
        return codificado;
    }

    public byte[] byteFile(File file) throws Exception {
        return new byte[(int) file.length()];
    }

    public String codeBase64(String texto) {
        byte[] authBytes = texto.getBytes(StandardCharsets.UTF_8);
        String encoded = java.util.Base64.getEncoder().encodeToString(authBytes);
        return encoded;
    }

    public String decodeBase64(String texto) {
        try {
            byte[] decoded = java.util.Base64.getDecoder().decode(texto);
            String salida = new String(decoded, UTF_8_);
            return salida;
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
            return VACIO;
        }
    }

    public byte[] decodeBase64byte(String texto) {
        try {
            byte[] decoded = java.util.Base64.getDecoder().decode(texto);
            return decoded;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    private Cipher obtenerCipher(MessageDigest messageDigest, int mode) throws Exception {
        try {
            byte[] digestOfPassword = messageDigest.digest(SECRETKEY.getBytes(UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, N);
            SecretKey key = new SecretKeySpec(keyBytes, DESede);
            Cipher obtenerCipherDecript = Cipher.getInstance(DESede);
            obtenerCipherDecript.init(mode, key);
            return obtenerCipherDecript;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private String encriptarSHA256(String texto) {
        try {
            byte[] plainTextBytes = texto.getBytes(UTF_8);
            byte[] buf;
            synchronized (decipherEncrypt_SHA) {
                buf = decipherEncrypt_SHA.doFinal(plainTextBytes);
            }
            String base64EncryptedString = new String(Base64.encodeBase64(buf));
            return base64EncryptedString;
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            log.error(ex.getMessage());
            return encriptarSHA256(texto);
        }
    }

    private String encriptarMD5(String texto) {
        try {
            byte[] plainTextBytes = texto.getBytes(UTF_8);
            byte[] buf;
            synchronized (decipherEncrypt_MD5) {
                buf = decipherEncrypt_MD5.doFinal(plainTextBytes);
            }
            String base64EncryptedString = new String(Base64.encodeBase64(buf));
            return base64EncryptedString;
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            log.error(ex.getMessage());
            return VACIO;
        }
    }

    private String desencriptarSHA256(String textoEncriptado) {
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(UTF_8));
            byte[] plainText;
            synchronized (decipherDecript_SHA) {
                plainText = decipherDecript_SHA.doFinal(message);
            }
            String base64EncryptedString = new String(plainText, UTF_8_);
            return base64EncryptedString;
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            log.error(ex.getMessage());
            return VACIO;
        }
    }

    private String desencriptarMD5(String textoEncriptado) {
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes(UTF_8));
            byte[] plainText;
            synchronized (decipherDecript_MD5) {
                plainText = decipherDecript_MD5.doFinal(message);
            }
            String base64EncryptedString = new String(plainText, UTF_8_);
            return base64EncryptedString;
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            log.error(ex.getMessage());
            return VACIO;
        }
    }
//

    public String desencriptarKeyPrivate(String encrypted, PrivateKey key) {
        String decrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
            decrypted = new String(cipherText, "UTF8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            return decrypted;
        }
        return decrypted;
    }
}
