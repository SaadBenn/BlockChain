import java.security.Security;
import java.util.*;

import com.google.gson.GsonBuilder;

public class Blockchain {

    //list of all unspent transactions.
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 2;

    // declare Wallets
    public static Wallet wallet1;
    public static Wallet wallet2;

    public static void main(String[] args) {

        // Setup Bouncy castle as a Security Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // Create the new wallets
        wallet1 = new Wallet();
        wallet2 = new Wallet();

        // Test public and private keys
        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(wallet1.privateKey));
        System.out.println(StringUtil.getStringFromKey(wallet1.publicKey));

        // Create a test transaction from WalletA to wallet2
        Transaction transaction = new Transaction(wallet1.publicKey, wallet2.publicKey, 5, null);
        transaction.generateSignature(wallet1.privateKey);

        // Verify the signature works and verify it from the public key
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());
    }

    public static boolean isChainValid() {
        Block curr;
        Block prev;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            curr = blockchain.get(i);
            prev = blockchain.get(i-1);

            // compare registered hash and calculated hash:
            if(!curr.hash.equals(curr.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }

            // compare previous hash and registered previous hash
            if(!prev.hash.equals(curr.prevHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            // check if hash is solved
            if(!curr.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
