package challenge;

public class CriptografiaCesariana implements Criptografia {

    public static final String CRIPTOGRAFAR = "criptografar";
    public static final String DESCRIPTOGRAFAR = "descriptografar";

    @Override
    public String criptografar(String texto) {
        return criptografarOuDescriptografar(texto, CRIPTOGRAFAR);
    }

    @Override
    public String descriptografar(String texto) {
        return criptografarOuDescriptografar(texto, DESCRIPTOGRAFAR);
    }


    private String criptografarOuDescriptografar(String texto, String operacao) {
        if (texto == null)
            throw new NullPointerException("Texto não pode ser nulo.");
        if (texto.isEmpty())
            throw new IllegalArgumentException("Texto não pode ser vazio.");

        String ret = "";
        for (Character c : texto.toLowerCase().toCharArray()) {
            if (String.valueOf(c).matches(("[a-z]*"))) {
                if (operacao.equals(CRIPTOGRAFAR)) {
                    int cifrado = ((int) c + 3) % 122;
                    ret = ret.concat(String.valueOf((char) cifrado));
                }
                else if (operacao.equals(DESCRIPTOGRAFAR)) {
                    int decifrado = ((int) c - 3) % 122;
                    ret = ret.concat(String.valueOf((char) decifrado));
                }

            }
            else
                ret = ret.concat(String.valueOf(c));
        }
        return ret;
    }
}
