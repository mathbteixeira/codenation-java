package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Jogador> jogadores = new ArrayList<>();
	private List<Time> times = new ArrayList<>();

	@Override
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		List<Long> listaIdTimes = buscarTimes();
		if (listaIdTimes.contains(id)) {
			throw new IdentificadorUtilizadoException();
		}
		else {
			Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
			times.add(time);
		}
	}

	@Override
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (isJogadorValido(id, idTime)) {
			Time time = getTimeById(idTime);

			Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
			time.addJogador(jogador);

			jogadores.add(jogador);
		}
	}

	private boolean isJogadorValido(Long id, Long idTime) {
		List<Long> listaIdTimes = buscarTimes();
		if (listaIdTimes.contains(idTime)) {
			List<Long> listaIdJogadores = buscarJogadoresDoTime(idTime);
			if (listaIdJogadores.contains(id)) {
				throw new IdentificadorUtilizadoException();
			}
			else {
				return true;
			}
		}
		else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Override
	public void definirCapitao(Long idJogador) {
		Jogador jogador = getJogadorById(idJogador);
		Time time = getTimeById(jogador.getIdTime());
		time.setIdCapitao(idJogador);
	}

	private Time getTimeById(Long idTime) {
		return times.stream().filter(t -> t.getId() == idTime).findFirst().orElseThrow(() -> new TimeNaoEncontradoException());
	}

	private Jogador getJogadorById(Long idJogador) {
		return jogadores.stream().filter(j -> j.getId() == idJogador).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException());
	}

	@Override
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = getTimeById(idTime);
		Long idCap = time.getIdCapitao();
		if (idCap == null) {
			throw new CapitaoNaoInformadoException();
		}
		else {
			return idCap;
		}
	}

	@Override
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = getJogadorById(idJogador);
		return jogador.getNome();
	}

	@Override
	public String buscarNomeTime(Long idTime) {
		Time time = getTimeById(idTime);
		return time.getNome();
	}

	@Override
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = getTimeById(idTime);
		return time.getJogadores().stream()
				.map(jogador -> jogador.getId())
				.sorted()
				.collect(Collectors.toList());
	}

	@Override
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = getTimeById(idTime);
		return time.getJogadores().stream()
				.reduce((prev, actual) -> {
					actual = prev.getNivelHabilidade() > actual.getNivelHabilidade() ? prev : actual;
					return actual;
				})
				.get()
				.getId();
	}

	@Override
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = getTimeById(idTime);
		return time.getJogadores().stream()
				.reduce((prev, actual) -> {
					actual = prev.getDataNascimento().isBefore(actual.getDataNascimento()) ||
					prev.getDataNascimento().isEqual(actual.getDataNascimento()) &&
							prev.getId() < actual.getId() ? prev : actual;
					return actual;
				})
				.get()
				.getId();
	}

	@Override
	public List<Long> buscarTimes() {
		return times.stream()
				.map(time -> time.getId())
				.collect(Collectors.toList());
	}

	@Override
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = getTimeById(idTime);
		return time.getJogadores().stream()
				.reduce((prev, actual) -> {
					actual = prev.getSalario().compareTo(actual.getSalario()) == 1 ||
							prev.getSalario().compareTo(actual.getSalario()) == 0 &&
									prev.getId() < actual.getId() ? prev : actual;
					return actual;
				})
				.get()
				.getId();
	}

	@Override
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = getJogadorById(idJogador);
		return jogador.getSalario();
	}

	@Override
	public List<Long> buscarTopJogadores(Integer top) {
		Comparator<Jogador> comparaId = Comparator.comparing(Jogador::getId).reversed();
		Comparator<Jogador> comparaHabilidade = Comparator.comparing(Jogador::getNivelHabilidade)
				.thenComparing(comparaId);
		List<Jogador> jogadoresPorHabilidade = jogadores;
		jogadoresPorHabilidade.sort(comparaHabilidade.reversed());

		return jogadoresPorHabilidade.stream()
				.map(j -> j.getId())
				.limit(top)
				.collect(Collectors.toList());
	}

}
