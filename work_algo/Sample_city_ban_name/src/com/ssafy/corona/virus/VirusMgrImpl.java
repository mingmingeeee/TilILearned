package com.ssafy.corona.virus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class VirusMgrImpl implements VirusMgr {
	private Virus[] virus;
	private int index; // 사용
	
	// 2. 매니저 객체 저장할 변수 생성
	private static VirusMgr instance;
	
	// 싱글턴
	// 1. 외부에서 생성자 호출 못하도록 막기 
	private VirusMgrImpl() {
		// 11-2. 배열 자체에 문제 없음 -> 배열 담고 있는 곳 보쟈
		virus = new Virus[100];
	}

	// 3. 외부에서 접근할 수 있는 getter 생성
	public static VirusMgr getInstance() {
		// 객체 하나만 생성 하도록 코드 작성 
		if(instance==null)
			instance = new VirusMgrImpl();
		return instance;
	}
	
	@Override
	public void add(Virus v) throws DuplicatedException {
		// 중복된 거 있으면 실행ㄴㄴ, search한 거 없으면 (새로 추가면) 추가하도록 
		try {
			Virus getVirus = search(v.getName());
			// 11-5. 중복된 바이러스 처리 
			if(getVirus != null) {
				// msg 넘겨주기
				throw new DuplicatedException(v.getName() + ": 등록된 바이러스입니다.");
			}
			// 11-3. debug -> v도 확인 -> 객체 문제 없음 그렇다묜..
			// 추가 되기 전에 검색하고 있었슴...
			// 다음 줄은 f6
			// f7은 함수 내부로 들어가는
		} catch (NotFoundException e) {
			virus[index++] = v;
		}
	}

	@Override
	public Virus[] search() {
		// 12. Virus[100]이고 등록된 거 까지만 배열 복사해서 return
		// return Arrays.copyOf(virus, index);
		return Arrays.copyOfRange(virus, 0, index);
	}

	@Override
	public Virus search(String name) throws NotFoundException {
		// 11.4. NotFoundException 처리 되도록 해야함
		for (int i = 0; i < index; i++) {
			if (virus[i].getName().equals(name))
				return virus[i];
			// 11-1. -> 어떤 거 발생했는 지 모를 때 BreakPoint 걸고 debugging
			// virus[i] -> i번째 값이 널 값인지 보기 -> tab에서 바꿔보기
			// -> Experssions -> Add new expression -> "virus[1]" 쳐보기
			// -> virusl[1]이 null 값이니 virus놈 찾아보기
		}
		
		throw new NotFoundException(name + ": 미등록 바이러스입니다.");
	}
	
	// 파일 입출력
	/**
	 * 1. 저장하는 파일 이름 먼저
	 * 2. save: 밖으로 나가는 거니까 Output (밖으로 저장) -> 객체 Serializable 상속 
	 * 3. 인터페이스 선언부에 추가 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	// node stream부터 작성 -> 보조 스트림 
	public void save() throws FileNotFoundException, IOException {
		// virus 배열을 파일로 저장
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("virus.dat"));
		oos.writeObject(virus);
		oos.close();
	}
	
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException  {
		// virus.data 파일을 Java 객체화 
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("virus.dat"));
		// 타입 생각하기 -> 받아오는 건 Object, 우리가 저장하려는 건 Virus 
		// 형 변환 !! 
		Object obj = ois.readObject();
		if(obj instanceof Virus[]) { // Virus 객체 맞는 지 확인하고 형변환
			virus = (Virus[]) obj;
		}
		// Virus[] result = (Virus[]) ois.readObject();
		ois.close();
	}
}
