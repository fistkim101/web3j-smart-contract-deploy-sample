package com.example.web3jsmartcontractdeploysample;

import com.example.contracts.Auction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

@SpringBootApplication
public class Web3jSmartContractDeploySampleApplication {
    private final static String PRIVATE_KEY = "0xe2b2e154a929b8eba8992583dc70cbba9c89b255d716bc09c80a34cab3e96e05";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Web3jSmartContractDeploySampleApplication.class, args);

        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        Credentials credentials = getCredentialsFromPrivateKey();

        // deploy smart contract
        String contractAddress = deployContract(web3j, credentials);

        System.out.println("========================");
        System.out.println("contractAddress : " + contractAddress);
        System.out.println("========================");

        // load target smart contract (need smart contract address)
        Auction targetContract = loadContract(contractAddress, web3j, credentials);

        // call function of smart contract(setData)
        targetContract.setAuctionData("encodedAuctionData").send();

        // call function of smart contract(getData)
        String receivedEncodedAuctionData = targetContract.getAuctionData().send();
        System.out.println("========================");
        System.out.println("receivedEncodedAuctionData : " + receivedEncodedAuctionData);
        System.out.println("========================");

    }

    private static Credentials getCredentialsFromPrivateKey() {
        return Credentials.create(PRIVATE_KEY);
    }

    private static String deployContract(Web3j web3j, Credentials credentials) throws Exception {
        return Auction.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }

    private static Auction loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
        return Auction.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

}
