import java.security.MessageDigest;
public class StringUtil {

    //Applies Sha256 to a string and returns the result.
    public static String applySha256(String data){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Applies sha256 to our data,
            byte[] hash = digest.digest(data.getBytes("UTF-8")); // get the bytes
            StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal

            for(byte element : hash) {
                String hex = Integer.toHexString(0xff & element); //change each element into hexadecimal

                if(hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
