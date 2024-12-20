package com.idat.service;

import java.util.List;

import com.idat.dto.NotesDto;

public interface NotesService {

	public Boolean saveNotes(NotesDto notesDto);
	
	public List<NotesDto> getallNotes();
	
}
