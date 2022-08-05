package com.ssafy.corona.virus;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface VirusMgr {
	
	void add(Virus v) throws DuplicatedException;
	Virus[] search();
	Virus search(String name) throws NotFoundException;
	public void save() throws FileNotFoundException, IOException;
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException;
}