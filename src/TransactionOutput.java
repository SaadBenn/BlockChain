import java.security.PublicKey;

public class TransactionOutput {
    public String id;

    // also known as the new owner of these coins.
    public PublicKey recipient;

    // the amount of coins they own
    public float value;

    // the id of the transaction this output was created in
    public String parentTransactionId;

    // Constructor
    public TransactionOutput(PublicKey recipient, float value, String parentTransactionId) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(recipient)+Float.toString(value)+parentTransactionId);
    }

    // Check if coin belongs to you
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == recipient);
    }

}