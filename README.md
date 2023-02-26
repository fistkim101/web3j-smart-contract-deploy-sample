
## 1. abi 와 binary 을 컴파일 
```bash
solc ./src/main/resources/contract/Auction.sol --bin --abi --optimize -o ./src/main/resources/out
```

## 2. web3j 를 통해서 1에서 만든 bin, abi 를 이용하여 .java 파일 생성
```bash
web3j generate solidity -b ./src/main/resources/out/Auction.bin --abiFile=./src/main/resources/out/Auction.abi -o ./src/main/java -p com.example.contracts
```