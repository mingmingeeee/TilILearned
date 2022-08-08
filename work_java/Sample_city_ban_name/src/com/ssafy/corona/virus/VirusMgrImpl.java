package com.ssafy.corona.virus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class VirusMgrImpl implements VirusMgr {
	private Virus[] virus;
	private int index;

	private static VirusMgr instance;

	private VirusMgrImpl() {
		virus = new Virus[100];
	}

	public static VirusMgr getVirusMgrImpl() {
		if (instance == null)
			instance = new VirusMgrImpl();

		return instance;
	}

	@Override
	public void add(Virus v) throws DuplicatedException {
		try {
			search(v.getName());
			throw new DuplicatedException(v.getName() + ": 이미 등록된 바이러스입니다.");
		} catch (NotFoundException e) {
			virus[index++] = v;
		}
	}

	@Override
	public Virus[] search() {
		return Arrays.copyOfRange(virus, 0, index);
	}

	@Override
	public Virus search(String name) throws NotFoundException {
		for (int i = 0; i < index; i++) {
			if (virus[i].getName().equals(name))
				return virus[i];
		}
		throw new NotFoundException(name + ": 미등록 바이러스입니다.");
	}

	// 파일 입출력 //
	public void load() { // JAVA로 들여오는 -> input

		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("virus.dat"));) {
			Object obj = ois.readObject();
			if(obj instanceof Virus) {
				Virus[] result = (Virus[]) obj;
				virus = result;
			}
//				virus = (Virus[]) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() { // JAVA에서 나가는 -> output
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("virus.dat"))) {
			
			oos.writeObject(virus);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
