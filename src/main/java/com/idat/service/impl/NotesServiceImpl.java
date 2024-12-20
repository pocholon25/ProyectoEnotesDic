package com.idat.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.idat.dto.NotesDto;
import com.idat.dto.NotesDto.CategoryDto;
import com.idat.entity.Notes;
import com.idat.exception.ResourceNotFounfException;
import com.idat.repository.CategoryRepository;
import com.idat.repository.NotesRepository;
import com.idat.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Boolean saveNotes(NotesDto notesDto) throws Exception {
		//check valid category
		checkCategoryExist(notesDto.getCategory());
		
		Notes notes = mapper.map(notesDto, Notes.class);
		Notes saveNotes = notesRepository.save(notes);
		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}

	private void checkCategoryExist(CategoryDto category) throws Exception {
		categoryRepository.findById(category.getId()).orElseThrow(()->new ResourceNotFounfException("category id invalid"));
		
	}

	@Override
	public List<NotesDto> getallNotes() {
		return notesRepository.findAll().stream().map(note->mapper.map(note, NotesDto.class)).toList();
	}

	
	
}
