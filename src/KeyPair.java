public class KeyPair {
    private DSAPublicKey publicKey;
    private DSAPrivateKey privateKey;

    public KeyPair(DSAPublicKey publicKey, DSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public DSAPublicKey getPublic() {
        return publicKey;
    }

    public DSAPrivateKey getPrivate() {
        return privateKey;
    }
}
