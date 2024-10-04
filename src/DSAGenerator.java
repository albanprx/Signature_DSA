import java.security.SecureRandom;
import java.math.BigInteger;

public class DSAGenerator {
    public static KeyPair generateDSAKeyPair() {
        SecureRandom random = new SecureRandom();
        BigInteger x = new BigInteger(160, random).mod(DSAParams.L); // Clé privé
        BigInteger y = DSAParams.G.modPow(x, DSAParams.P); // Clé public
        return new KeyPair(new DSAPublicKey(y), new DSAPrivateKey(x));
    }
}
