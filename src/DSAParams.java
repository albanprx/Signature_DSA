import java.math.BigInteger;

public class DSAParams {
    public static final BigInteger L;
    public static final BigInteger P;
    public static final BigInteger G;

    static {
        L = BigInteger.valueOf(2).pow(160).add(BigInteger.valueOf(7));

        BigInteger temp = BigInteger.valueOf(2).pow(864).add(BigInteger.valueOf(218));
        P = BigInteger.ONE.add(L.multiply(temp));

        G = BigInteger.valueOf(2).modPow((P.subtract(BigInteger.ONE)).divide(L), P);
    }
}