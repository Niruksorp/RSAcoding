package sample.RSA;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;

public interface RSAInt {
    /**
     * Encrypts a message through C = M^e mod n where:
     * C = encrypted message
     * M = message to be encrypted
     * e = relative prime
     * n = p*q
     * @param message to be encrypted
     * @return encrypted message represented by a Java BigInteger
     */
    ArrayList<BigInteger> encrypt (final String message);

    /**
     * decrypt an encrypted message through M = C^d mod n where:
     * M = decrypted message
     * C = encrypted message
     * d = private key
     * n = p*q
     * @param inputStr encrypted message
     * @return decode message represented by a Java BigInteger type
     */
    String decode (final @NotNull ArrayList<BigInteger> inputStr);

    /**
     * @param inputStr message in string format
     * @return message in integer format. disassemble into parts
     */
    ArrayList<BigInteger> convertToInt (final @NotNull String inputStr);

    /**
     * @param inputColl message in integer format
     * @return convertToStr message in String format
     */
    String convertToStr (final @NotNull BigInteger inputColl);

}
