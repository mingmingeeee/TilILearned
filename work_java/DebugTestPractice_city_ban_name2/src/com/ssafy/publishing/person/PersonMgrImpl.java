package com.ssafy.publishing.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonMgrImpl implements PersonMgr {
	private List<Person> people;
	
	private static PersonMgr instance;
	
	private PersonMgrImpl() {
		people=new ArrayList<Person>();
	}
	public static PersonMgr getInstance() {
		if(instance==null)
			instance = new PersonMgrImpl();
		return instance;
	}
	
	@Override
	public void add(Person p) {
		people.add(p);
	}
	@Override
	public List<Person> search() {
		return people;
	}	
	@Override
	public Person search(String name) {
		for(Person p : people) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	@Override
	public void delete(String name) {
		for(int i=people.size()-1; i>=0; i--) {
			if(people.get(i).getName().equals(name)) {
				people.remove(i);
			}
		}
	}	
	@Override
	public void load() {
		File f=new File("people.dat");
		if(f.exists()) {
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f))){
				Object obj = ois.readObject();
				if(obj instanceof List) {
					people = (List<Person>) obj;
				}
					
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("[SYSTEM] 파일 읽기 실패");
			}
		}
	}
	@Override
	public void save() {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("people.dat"))){
			oos.writeObject(people);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[SYSTEM] 파일 쓰기 실패");
		} 
		people=null;
	}
}
