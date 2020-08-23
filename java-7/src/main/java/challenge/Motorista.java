package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        if (habilitacao == null || nome == null)
            throw new NullPointerException("O motorista não possui habilitação e/ou nome");
        this.idade = idade;
        this.habilitacao = habilitacao;
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        public static final int IDADE_MINIMA = 18;
        public static final int PONTUACAO_MAXIMA = 20;
        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            if (idade < 0)
                throw new IllegalArgumentException("O motorista não pode possuir a idade negativa");
            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            this.pontos = isPontosValidos(pontos);
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }

        public Motorista build() {
            isNomeValido(nome);
            isHabilitacaoValida(habilitacao);
            return new Motorista(nome, isIdadeValida(idade), isPontosValidos(pontos), habilitacao);
        }

        public int isIdadeValida(int idade) {
            if (idade < 0)
                throw new IllegalArgumentException("O motorista não pode possuir a idade negativa");
            else if (idade < IDADE_MINIMA)
                throw new EstacionamentoException("O motorista não pode ser menor de idade");
            return idade;
        }

        public String isHabilitacaoValida(String habilitacao) {
            if (habilitacao == null || habilitacao.isEmpty())
                throw new NullPointerException("O motorista precisa ter uma habilitação.");
            return habilitacao;
        }

        public String isNomeValido(String nome) {
            if (nome == null || nome.isEmpty())
                throw new NullPointerException("O motorista precisa ter um nome.");
            return nome;
        }

        public int isPontosValidos(int pontos) {
            if (pontos < 0)
                throw new IllegalArgumentException("A pontuação não pode ser negativa.");
            else if (pontos > PONTUACAO_MAXIMA)
                throw new EstacionamentoException("A pontuação não deve ser maior que a pontuação máxima.");
            return pontos;
        }
    }
}
