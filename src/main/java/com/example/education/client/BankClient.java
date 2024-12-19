package com.example.education.client;


import com.example.consumingwebservice.wsdl.*;
import com.example.education.entity.UserEntity;
import lombok.SneakyThrows;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class BankClient extends WebServiceGatewaySupport {

    private static final String KEYSTORE_PASSWORD = "rsaprivate";
    private static final String KEYSTORE_PATH = "/keystore.jks";

    public GetBankResponse getBank(GetBankRequest request, UserEntity buyerId,
                                   String sellerName, BigDecimal sum){
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

    @SneakyThrows
    private byte[] encryptSeller(BigDecimal sum, String sellerName){
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream is = getClass().getResourceAsStream(KEYSTORE_PATH)) {
            keyStore.load(is, KEYSTORE_PASSWORD.toCharArray());
        }

        PrivateKey privateKey = (PrivateKey) keyStore.getKey("rsaprivate", KEYSTORE_PASSWORD.toCharArray());
        if (privateKey == null) {
            throw new KeyStoreException("Приватный ключ с alias '" + "rsaprivate" + "' не найден.");
        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        String message = sum +"/"+sellerName;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(messageBytes);
    }
    @SneakyThrows
    private byte[] encryptBuyer(BigDecimal sum, String buyerName) {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream is = getClass().getResourceAsStream(KEYSTORE_PATH)) {
            keyStore.load(is, KEYSTORE_PASSWORD.toCharArray());
        }

        PrivateKey privateKey = (PrivateKey) keyStore.getKey("rsaprivate", KEYSTORE_PASSWORD.toCharArray());
        if (privateKey == null) {
            throw new KeyStoreException("Приватный ключ с alias '" + "rsaprivate" + "' не найден.");
        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        String message = sum +"/"+buyerName;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(messageBytes);
    }
}
