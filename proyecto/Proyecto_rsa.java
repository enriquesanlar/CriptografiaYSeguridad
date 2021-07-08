package proyecto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

class Proyecto_rsa {
    private Random r;
    BigInteger p, q, n, phi, e, d;
    public Proyecto_rsa(int bitLength) {
        r = new Random();
        p = BigInteger.probablePrime(bitLength, r);
        System.out.println("Genera p = " + p);
        q = BigInteger.probablePrime(bitLength, r);
        System.out.println("Genera q = " + q);
        n = p.multiply(q);
        System.out.println("Calcula n = " + n);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Calcula phi =" + phi);
        e = BigInteger.probablePrime(bitLength/2, r);
        System.out.println("Calcula e = " + e);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
        System.out.println("Calcula d = " + d);
    }

    public byte[] encrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(e, n).toByteArray();
    }

    public byte[] decrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(d, n).toByteArray();
    }

    public static void main(String[] args) {
        Proyecto_rsa p = new Proyecto_rsa(2048);
        String plainMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        System.out.println("Encriptando mensaje...");
        byte[] encriptado = p.encrypt(plainMessage.getBytes());
        System.out.println("Mensaje en texto plano: " + plainMessage);
        System.out.println("Mensaje encriptado: " + Arrays.toString(encriptado));
        System.out.println("Desencriptando mensaje...");
        byte[] desencriptado = p.decrypt(encriptado);
        System.out.println("Mensaje desencriptado: " + new String(desencriptado));
    }
}