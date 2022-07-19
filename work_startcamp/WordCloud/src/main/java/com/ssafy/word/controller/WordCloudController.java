package com.ssafy.word.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.word.model.WordDto;
import com.ssafy.word.model.service.WordService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/word")
public class WordCloudController {

	private static final Logger logger = LoggerFactory.getLogger(WordCloudController.class);
	
	@Autowired
	private WordService wordService;
	
	@GetMapping
	public ResponseEntity<List<WordDto>> listWord() {
		logger.debug("listWord - 호출");
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<List<WordDto>> registWord(@RequestParam(value = "concerns[]") List<String> concerns) {
		logger.debug("registWord - 호출");
		wordService.registWord(concerns);
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	} 
	
	@PostMapping("{word}")
	public ResponseEntity<List<WordDto>> updateWordCount(@PathVariable("word") String word) {
		logger.debug("updateWordCount - 호출");
		wordService.updateCount(word);
		return new ResponseEntity<>(wordService.listWord(), HttpStatus.OK);
	}
	
}
