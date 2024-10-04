import java.math.BigInteger;
import java.security.SecureRandom;

public class DSASigner {
    public static BigInteger[] signData(String data, DSAPrivateKey privateKey) {
        SecureRandom random = new SecureRandom();
        BigInteger k = new BigInteger(160, random).mod(DSAParams.L);
        BigInteger r = DSAParams.G.modPow(k, DSAParams.P).mod(DSAParams.L);

        // A CHANGER VERS SHA-3 SI LE TEMPS
        BigInteger hash = new BigInteger(data.getBytes()).mod(DSAParams.L);
        BigInteger s = k.modInverse(DSAParams.L).multiply(hash.add(privateKey.getX().multiply(r))).mod(DSAParams.L);
        return new BigInteger[] { r, s };
    }
}
