package com.example.education.client;


import com.example.consumingwebservice.wsdl.*;
import com.example.education.entity.UserEntity;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigDecimal;

public class BankClient extends WebServiceGatewaySupport {

    public GetBankResponse getBank(GetBankRequest request, UserEntity buyerId, String sellerName, BigDecimal sum) {
        request.setBuyerId(String.valueOf(buyerId.getId()));
        BuyerBankAccount buyerBankAccount = new BuyerBankAccount();
        Client buyerClient = new Client();
        buyerClient.setName(buyerId.getLogin());
        buyerClient.setId(String.valueOf(buyerId.getId()));
        buyerBankAccount.setClient(buyerClient);
        buyerBankAccount.setSum(sum);
        request.setBuyerBankAccount(buyerBankAccount);
        SellerBankAccount sellerBankAccount = new SellerBankAccount();
        Client sellerClient = new Client();
        sellerClient.setName(sellerName);
        sellerBankAccount.setClient(sellerClient);
        request.setSellerBankAccount(sellerBankAccount);

        GetBankResponse response = (GetBankResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }
}
