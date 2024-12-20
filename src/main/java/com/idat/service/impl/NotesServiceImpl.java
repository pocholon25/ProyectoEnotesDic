package com.idat.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.idat.dto.NotesDto;
import com.idat.entity.Notes;
import com.idat.repository.NotesRepository;
import com.idat.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveNotes(NotesDto notesDto) {
		Notes notes = mapper.map(notesDto, Notes.class);
		Notes saveNotes = notesRepository.save(notes);
		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}

	@Override
	public List<NotesDto> getallNotes() {
		return notesRepository.findAll().stream().map(note->mapper.map(note, NotesDto.class)).toList();
	}

	
	
}
