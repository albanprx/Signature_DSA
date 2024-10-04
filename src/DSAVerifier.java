import java.math.BigInteger;

public class DSAVerifier {
    public static boolean verifySignature(String data, BigInteger[] signature, DSAPublicKey publicKey) {
        BigInteger r = signature[0];
        BigInteger s = signature[1];
        if (r.compareTo(BigInteger.ZERO) <= 0 || r.compareTo(DSAParams.L) >= 0 || s.compareTo(BigInteger.ZERO) <= 0
                || s.compareTo(DSAParams.L) >= 0) {
            return false;
        }

        // A MODIFIER SI POSSIBLE
        BigInteger hash = new BigInteger(data.getBytes()).mod(DSAParams.L);
        BigInteger temp1 = s.modInverse(DSAParams.L);
        BigInteger temp2 = hash.multiply(temp1).mod(DSAParams.L);
        BigInteger temp3 = r.multiply(temp1).mod(DSAParams.L);
        BigInteger temp4 = DSAParams.G.modPow(temp2, DSAParams.P).multiply(publicKey.getY().modPow(temp3, DSAParams.P))
                .mod(DSAParams.P).mod(DSAParams.L);
        return temp4.equals(r);

    }
}
