// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

contract Auction {

    string auctionData;

    function setAuctionData(string memory _auctionData) public {
        auctionData = _auctionData;
    }

    function getAuctionData() public view returns (string memory) {
        return auctionData;
    }

}
