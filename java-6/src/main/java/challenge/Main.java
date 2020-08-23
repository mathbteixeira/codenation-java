package challenge;

public class Main {
    private static Criptografia criptografia = new CriptografiaCesariana();

    public static void main(String[] args) {
        criptografia.criptografar("abcDEfghij12345");
    }
}
