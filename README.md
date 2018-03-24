# BlockChain

After reading a bunch of articles on blockchain and cryptocurrency, I am setting out on a programming journey by building my own basic blockchain which will further solidify my understanding of mining and proof of work and blockchain technology, in general and apply the knowledge from the articles into action.

The aim of this side project, is to help myself build a better picture of how one could develop blockchain technology. This entire project is written in Java.

Below is a blurb on blockchain technology and how it works: A blockchain is just a chain/list of blocks. Each block in the blockchain will have its own digital signature, contain digital signature of the previous block, and have some data. Each block doesn’t just contain the hash of the block before it, but its own hash is in part, calculated from the previous hash. If the previous block’s data is changed then the previous block’s hash will change ( since it is calculated in part, by the data) in turn affecting all the hashes of the blocks there after. Calculating and comparing the hashes allow us to see if a blockchain is invalid.


To run the program, first 
  `build the project`

then run the `Blockchain ` file using the below command:
  `java Blockchain`


