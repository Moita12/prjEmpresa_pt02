package com.Moises.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Moises.PrjEmpresa.entities.Funcionario;

@Service
public class FuncionarioService {
	private Funcionario FuncionarioRepository;
	
	@Autowired
	public FuncionarioService(Funcionario FuncionarioRepository) {
		this.FuncionarioRepository = FuncionarioRepository;
	}
	public Funcionario getFuncionarioById(Long FunCodigo) {
		return FuncionarioRepository.findbyId(FunCodigo).orElse(null);
	}
    public List<Funcionario> getAllFuncionario(){
        return FuncionarioRepository.findAll();
    }
	    public Funcionario saveFuncionario(Funcionario funcionario) {
	        return FuncionarioRepository.save(funcionario);
	    }
	    
	    public List<Funcionario> getFuncionariosByFunnomeAproximado(String FunNome) {
	        return FuncionarioRepository.findByNomeContaining(FunNome);
	        
	    }
	    
	    public boolean deleteFuncionario(Long id) {
			Optional<Funcionario> funcionarioExistente = FuncionarioRepository.findbyId(id);
			if (funcionarioExistente.isPresent()) {
				FuncionarioRepository.deleteById(id);
				return true;
			} else {
				return false;
			}
		}
	    
	    public Funcionario updateFuncionario(Long FunCodigo, Funcionario novoFuncionario) {
	        Optional<Funcionario> funcionarioOptional = FuncionarioRepository.findbyId(FunCodigo);
	        if (funcionarioOptional.isPresent()) {
	        	Funcionario funcionarioExistente = funcionarioOptional.get();
	           	funcionarioExistente.setFunNome(novoFuncionario.getFunNome());
	           	funcionarioExistente.setFunSalario(novoFuncionario.getFunSalario());
	           	funcionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());     
	            return FuncionarioRepository.save(funcionarioExistente); 
	        } else {
	            return null; 
	        }
	    }
	    
}
