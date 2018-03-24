import java.util.Date;

public class Block {

    public String hash;
    public String prevHash;
    private String data; // our data will be a simple message.
    private long timeStamp; // as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data, String prevHash ) {
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); // Making sure we do this after we set the other values.
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(prevHash + Long.toString(timeStamp) + data); // apply hash
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"

        while(true) {
            if (!!hash.substring(0, difficulty).equals(target)) {
                break;
            }
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
