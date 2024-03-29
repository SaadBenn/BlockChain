import java.util.*;

public class Block {

    public String merkleRoot;

    //our data will be a simple message.
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public String hash;
    public String prevHash;

    // our data will be a simple message.
    private String data;

    // as number of milliseconds since 1/1/1970.
    private long timeStamp;
    private int nonce;

    //Block Constructor.
    public Block(String prevHash ) {
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();

        // Making sure we do this after we set the other values.
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(prevHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot); // apply hash
        return calculatedHash;
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);

        //Create a string with difficulty * "0"
        String target = StringUtil.getDifficultyString(difficulty);

        while (!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //Add transactions to this block
    public boolean addTransaction(Transaction transaction) {

        //process transaction and check if valid, unless block is genesis block then ignore.
        if (transaction == null) {
            return false;
        }

        if ((prevHash != "0")) {
            if(!(transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
