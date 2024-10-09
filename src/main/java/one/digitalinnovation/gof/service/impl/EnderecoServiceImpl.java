package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Override
    public Iterable<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco buscarPorCep(String cep) throws Exception {
        String cepPadrao = "^\\d{5}-\\d{3}$|^\\d{8}$";
        if (!cep.matches(cepPadrao)) {
            throw new Exception("CEP inválido");
        }
        Optional<Endereco> endereco = enderecoRepository.findById(cep);
        if (!endereco.isPresent()) {
            String cepNaoEncontrado = String.format("CEP %s não encontrado", cep);
             throw new Exception(cepNaoEncontrado);
        }
        return endereco.get();
    }
}
