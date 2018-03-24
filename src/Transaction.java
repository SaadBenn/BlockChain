import java.security.*;
import java.util.*;

public class Transaction {

    // this is also the hash of the transaction.
    public String transactionId;

    // senders address / public key.
    public PublicKey sender;

    // Recipients address / public key.
    public PublicKey reciepient;

    public float value;

    // this is to prevent anybody else from spending funds in our wallet.
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    // a rough count of how many transactions have been generated.
    private static int count = 0;

    // Constructor:
    public Transaction(PublicKey sender, PublicKey receiver, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.reciepient = receiver;
        this.value = value;
        this.inputs = inputs;
    }

    // This Calculates the transaction hash aka the transaction id
    private String calulateHash() {

        //increase the count to avoid 2 identical transactions having the same hash
        count++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value) + count);
    }

    // Signs all the data we dont wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }

    // Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
}