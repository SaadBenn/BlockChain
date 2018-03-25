# BlockChain

After reading a bunch of articles on blockchain and cryptocurrency, I am setting out on a programming journey by building my own basic blockchain which will further solidify my understanding of mining and proof of work and blockchain technology, in general and apply the knowledge from the articles into action.

The aim of this side project, is to help myself build a better picture of how one could develop blockchain technology. This entire project is written in Java.

Below is a blurb on blockchain technology and how it works: A blockchain is just a chain/list of blocks. Each block in the blockchain will have its own digital signature, contain digital signature of the previous block, and have some data. Each block doesn’t just contain the hash of the block before it, but its own hash is in part, calculated from the previous hash. If the previous block’s data is changed then the previous block’s hash will change ( since it is calculated in part, by the data) in turn affecting all the hashes of the blocks there after. Calculating and comparing the hashes allow us to see if a blockchain is invalid.


To run the program, first 
  `build the project`

then run the `Blockchain ` file using the below command:
  `java Blockchain`

Your output should be similar to this:
`Creating and Mining Genesis block... 
Transaction Successfully added to Block
Block Mined!!! : 004fb74c064156a20bde57bfe7ab32c06a8f5e8cf427e656c5533d13e5714edb

Wallet1's balance is: 100.0

Wallet1 is Attempting to send funds (40) to Wallet2...
Transaction Successfully added to Block
Block Mined!!! : 002e5ea0d5ba9358cbb59fca2adb658e7d07f72b1befba862873a667ed3f4bdd

Wallet1's balance is: 60.0
Wallet2's balance is: 40.0

Wallet1 Attempting to send more funds (1000) than it has...
#Not Enough funds to send transaction. Transaction Discarded.
Block Mined!!! : 00e8f4a61c8b6b68809c2df970b09ab111a2e98f78eb55e5f68033be1524ade1

Wallet1's balance is: 60.0
Wallet2's balance is: 40.0

Wallet2 is Attempting to send funds (20) to Wallet1...
Transaction Successfully added to Block

Wallet1's balance is: 80.0
Wallet2's balance is: 20.0
Blockchain is valid`
