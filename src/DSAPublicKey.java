import java.math.BigInteger;

public class DSAPublicKey {
    private BigInteger y;

    public DSAPublicKey(BigInteger y) {
        this.y = y;
    }

    public BigInteger getY() {
        return y;
    }
}
