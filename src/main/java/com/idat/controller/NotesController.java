package com.idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.dto.NotesDto;
import com.idat.service.NotesService;
import com.idat.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveNotes(@RequestBody NotesDto notesDto){
		Boolean saveNotes = notesService.saveNotes(notesDto);
		if (saveNotes) {
			return CommonUtil.createBuildResponseMessage("Notes saved success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Notes not saved ", HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllNotes(){
		List<NotesDto> notes = notesService.getallNotes();
		if (CollectionUtils.isEmpty(notes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}
	
	
	
	
	
}
