package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    public static final int VAGAS = 10;
    public List<Carro> carros = new ArrayList<>();;
    public List<Carro> carrosEstacionados = new ArrayList<>();;

    public void estacionar(Carro carro) {
        if (carrosEstacionados.size() == VAGAS) {
            removerPrimeiroEstacionado(carro);
        }
        else if (!carroEstacionado(carro)) {
            carrosEstacionados.add(carro);
        }
    }

    public void removerPrimeiroEstacionado(Carro carro) {
        carrosEstacionados.remove(carrosEstacionados.stream().filter(c -> c.getMotorista().getIdade() < 55).findFirst().orElseThrow(() -> new EstacionamentoException("Não é possível estacionar no momento, pois todos os motoristas tem 55 anos ou mais.")));
        carrosEstacionados.add(carro);
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.contains(carro);
    }
}
