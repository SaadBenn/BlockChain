import java.security.*;
import java.util.*;

public class Transaction {

    // this is also the hash of the transaction.
    public String transactionId;

    // senders address / public key.
    public PublicKey sender;

    // Recipients address / public key.
    public PublicKey recipient;

    public float value;

    // this is to prevent anybody else from spending funds in our wallet.
    public byte[] signature;

    public ArrayList<TransactionInput> inputs;
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    // a rough count of how many transactions have been generated.
    private static int count = 0;

    // Constructor:
    public Transaction(PublicKey sender, PublicKey receiver, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.recipient = receiver;
        this.value = value;
        this.inputs = inputs;
    }

    // This Calculates the transaction hash aka the transaction id
    private String calulateHash() {

        //increase the count to avoid 2 identical transactions having the same hash
        count++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value) + count);
    }

    // Signs all the data we don't wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }

    // Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }

    //Returns true if new transaction could be created.
    public boolean processTransaction() {

        if (!verifiySignature()) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        //gather transaction inputs, making sure they are unspent):
        for (TransactionInput input : inputs) {
            input.UTXO = Blockchain.UTXOs.get(input.transactionOutputId);
        }

        //check if transaction is valid:
        if (getInputsValue() < Blockchain.minimumTransaction) {
            System.out.println("#Transaction Inputs to small: " + getInputsValue());
            return false;
        }

        //generate transaction outputs:
        //get value of inputs then the left over change:
        float leftOver = getInputsValue() - value;
        transactionId = calulateHash();

        // send value to recipient
        outputs.add(new TransactionOutput( this.recipient, value, transactionId));

        //send the left over 'change' back to sender
        outputs.add(new TransactionOutput( this.sender, leftOver, transactionId));

        //add outputs to Unspent list
        for (TransactionOutput o : outputs) {
            Blockchain.UTXOs.put(o.id , o);
        }

        //remove transaction inputs from UTXO lists as spent:
        for (TransactionInput input : inputs) {

            //if Transaction can't be found skip it
            if (input.UTXO == null) {
                continue;
            }
            Blockchain.UTXOs.remove(input.UTXO.id);
        }
        return true;
    }

    //returns sum of inputs(UTXOs) values
    public float getInputsValue() {
        float total = 0;

        for (TransactionInput input : inputs) {
            if (input.UTXO == null) {

                //if Transaction can't be found skip it
                continue;
            }
            total += input.UTXO.value;
        }
        return total;
    }

    //returns sum of outputs:
    public float getOutputsValue() {
        float total = 0;

        for (TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

}