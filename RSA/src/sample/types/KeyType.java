package sample.types;

import java.math.BigInteger;

public class KeyType {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger N;

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getN() {
        return N;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public void setN(BigInteger n) {
        N = n;
    }
}
