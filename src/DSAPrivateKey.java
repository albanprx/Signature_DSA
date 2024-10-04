import java.math.BigInteger;

public class DSAPrivateKey {
    private BigInteger x;

    public DSAPrivateKey(BigInteger x) {
        this.x = x;
    }

    public BigInteger getX() {
        return x;
    }
}
