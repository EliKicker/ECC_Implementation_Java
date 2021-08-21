package at.EliKicker.main;

import at.EliKicker.main.encryptionmethods.*;

import java.math.BigInteger;

public class main {

    public static void main(String[] args) {

        //Choose Elliptic Curve and initialize ECC Class
        EcryptionMethod enc_method = new secp256r1();
        ECC ecc1 = new ECC(enc_method);
        ECC ecc2 = new ECC(enc_method);

        //Generate private and public keys for person 1
        BigInteger[] keys_1 = ecc1.genKeys();
        BigInteger[] public_key_1 = new BigInteger[]{keys_1[1], keys_1[2]};

        //Generate private and public keys for person 2
        BigInteger[] keys_2 = ecc2.genKeys();
        BigInteger[] public_key_2 = new BigInteger[]{keys_2[1], keys_2[2]};

        System.out.println("private_key_1: " + keys_1[0].toString(16) + "\npublic_key_1: (" + public_key_1[0].toString(16) + ", " + public_key_1[1].toString(16) + ")");
        System.out.println("\nprivate_key_2: " + keys_2[0].toString(16) + "\npublic_key_2: (" + public_key_2[0].toString(16) + ", " + public_key_2[1].toString(16) + ")");

        //Calculate shared secret for person 1
        BigInteger[] shared_secret_1 = ecc1.point_mult(keys_1[0], public_key_2);

        //Calculate shared secret for person 2
        BigInteger[] shared_secret_2 = ecc2.point_mult(keys_2[0], public_key_1);

        System.out.println("\nshared_secret_1: (" + shared_secret_1[0].toString(16) + ", " + shared_secret_1[1].toString(16) + ")");
        System.out.println("shared_secret_2: (" + shared_secret_2[0].toString(16) + ", " + shared_secret_2[1].toString(16) + ")");
    }
}