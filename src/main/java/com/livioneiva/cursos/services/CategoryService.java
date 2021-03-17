package com.livioneiva.cursos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.livioneiva.cursos.entities.Category;
import com.livioneiva.cursos.repositories.CategoryRepository;

@RestController
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(@PathVariable Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.get();
	}
}
