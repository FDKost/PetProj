package com.example.education.client;


import com.example.consumingwebservice.wsdl.*;
import com.example.education.entity.UserEntity;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class BankClient extends WebServiceGatewaySupport {

    public GetBankResponse getBank(GetBankRequest request, UserEntity buyerId, String sellerName, BigDecimal sum) throws NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        request.setBuyerId(String.valueOf(buyerId.getId()));
        BuyerBankAccount buyerBankAccount = new BuyerBankAccount();
        Client buyerClient = new Client();
        buyerClient.setName(buyerId.getLogin());
        buyerClient.setId(String.valueOf(buyerId.getId()));
        buyerBankAccount.setClient(buyerClient);
        buyerBankAccount.setSum(sum);
        byte[] buyerSecretBytes = encryptBuyer(sum,buyerClient.getName());
        String buyerSecret = Base64.getEncoder().encodeToString(buyerSecretBytes);
        request.setBuyerSecret(buyerSecret);
        request.setBuyerBankAccount(buyerBankAccount);
        SellerBankAccount sellerBankAccount = new SellerBankAccount();
        Client sellerClient = new Client();
        sellerClient.setName(sellerName);
        sellerBankAccount.setClient(sellerClient);
        byte[] sellerSecretBytes = encryptSeller(sum,sellerName);
        String sellerSecret = Base64.getEncoder().encodeToString(sellerSecretBytes);
        request.setSellerSecret(sellerSecret);
        request.setSellerBankAccount(sellerBankAccount);

        GetBankResponse response = (GetBankResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    private byte[] encryptSeller(BigDecimal sum, String sellerName) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        InputStream inputStream = getClass().getResourceAsStream("/privatekey.pem");
        String privateKeyContent = new String(inputStream.readAllBytes());

        privateKeyContent = convertPrivateKey(privateKeyContent);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privateKey = kf.generatePrivate(keySpecPKCS8);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        String message = sum +"/"+sellerName;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(messageBytes);
    }
    private byte[] encryptBuyer(BigDecimal sum, String buyerName) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        InputStream inputStream = getClass().getResourceAsStream("/privatekey.pem");
        String privateKeyContent = new String(inputStream.readAllBytes());

        privateKeyContent = convertPrivateKey(privateKeyContent);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privateKey = kf.generatePrivate(keySpecPKCS8);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        String message = sum +"/"+buyerName;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(messageBytes);
    }

    private String convertPrivateKey(String privateKeyContent){

        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        privateKeyContent = privateKeyContent.replaceAll("\\s", "");

        return privateKeyContent;
    }
}
