package com.ssafy.word.model.service;

import java.util.List;

import com.ssafy.word.model.WordDto;

public interface WordService {

	List<WordDto> listWord();
	void registWord(List<String> concerns);
	void updateCount(String word);
	
}
