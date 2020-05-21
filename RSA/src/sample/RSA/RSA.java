package sample.RSA;

import org.jetbrains.annotations.NotNull;
import sample.types.KeyType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
public class  RSA implements RSAInt {
    int minPubKey = 5;

    private final BigInteger N;
    private  final BigInteger privateKey;
    private  final BigInteger publicKey;

    public RSA() {
       KeyType rez =  createKey();
       this.N = rez.getN();
       this.privateKey = rez.getPrivateKey();
       this.publicKey = rez.getPublicKey();
    }

    /**
     * create key pair. It's a public key + private key. In process, we generated N.
     * we use variables of type BigInteger.
     */
    @NotNull
    private KeyType createKey() {

        BigInteger bigP = BigInteger.probablePrime(52, new Random(System.currentTimeMillis()));
        BigInteger bigQ = BigInteger.probablePrime(52,new Random(System.currentTimeMillis() * 10));

        KeyType rez = new KeyType();
        rez.setN(bigP.multiply(bigQ));

        BigInteger bigPMinOne = bigP.subtract(new BigInteger("1"));
        BigInteger bigQMinOne = bigQ.subtract(new BigInteger("1"));
        BigInteger bigB_p_1_q_1 = bigPMinOne.multiply(bigQMinOne);

        while (true) {
            BigInteger BigB_GCD = bigB_p_1_q_1.gcd(new BigInteger("" + minPubKey));
            if (BigB_GCD.equals(BigInteger.ONE)) {
                break;
            }
            minPubKey++;
        }

        BigInteger bigB_pubKey = new BigInteger("" + minPubKey);
        rez.setPublicKey(bigB_pubKey);
        BigInteger bigB_prvKey = bigB_pubKey.modInverse(bigB_p_1_q_1);
        rez.setPrivateKey(bigB_prvKey);

        return rez;
    }

    public ArrayList<BigInteger> encrypt (final String message ) {
        ArrayList<BigInteger> data = convertToInt(message);
        int size = data.size();
        ArrayList<BigInteger> rez = new ArrayList<>(size);
        for (BigInteger datum : data) {
            rez.add(datum.modPow(publicKey, N));
        }
        return rez;
    }

    public String decode(final @NotNull ArrayList<BigInteger> inputStr) {
        int size = inputStr.size();
        String rez = new String("");
        for (int i = 0; i < size; i ++) {
            rez += convertToStr(inputStr.get(i).modPow(privateKey, N));
        }
        return rez;
    }

    public ArrayList<BigInteger> convertToInt (final @NotNull String inputStr) {
        String rezStr = "";
        int size = inputStr.length();
        int arraySize = 0;
        int counter = 0;
        int counters = 1;
        if (size % 3 != 0) arraySize++;
        arraySize += size/3;
        ArrayList<BigInteger> rezArray = new ArrayList();

        for (int j = 0; j < arraySize; j ++){
            rezStr = "";
            for (int i = 0; i < 3; i++) {
                if (counters++ > size) break;
                 char t = inputStr.charAt(counter);
                counter++;
                if ((int) t < 1000) {
                    rezStr += "00";
                }
                rezStr += (int) t;
            }
            rezArray.add(new BigInteger(rezStr));
        }
        return rezArray;
    }

    public String convertToStr (final @NotNull BigInteger inputColl) {
        StringBuffer str = new StringBuffer(inputColl.toString());
        StringBuilder rezString = new StringBuilder(new String(""));
        if (str.length() % 4 != 0) str.insert(0,"00");
        String inputStr = str.toString();
        String[] words = inputStr.split("(?<=\\G.{4})");
        for (int i = 0; i < words.length ; i ++) {
            int foo = Integer.parseInt(words[i]);
            char c = (char) foo;
            rezString.append(c);
        }
        return String.valueOf(rezString);
    }
}