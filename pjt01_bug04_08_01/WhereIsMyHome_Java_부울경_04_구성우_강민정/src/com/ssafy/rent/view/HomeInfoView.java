
package com.ssafy.rent.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ssafy.rent.model.dto.EnvInfo;
import com.ssafy.rent.model.dto.HomeDeal;
import com.ssafy.rent.model.dto.HomePageBean;
import com.ssafy.rent.model.dto.StoreInfo;
import com.ssafy.rent.model.service.HomeService;
import com.ssafy.rent.model.service.HomeServiceImpl;


public class HomeInfoView{
	
	/**model들 */
	private HomeService 		homeService;
	
	/** main 화면 */
	private JFrame frame;
	
	/** 주택 목록 화면*/
	// JFrame owner;
	

	/**창 닫기 버튼*/
	//private JButton  closeBt;
	
	/**주택 이미지 표시 Panel*/
	private JLabel	 			imgL;
	private JLabel[] 			homeInfoL;
	
	/**조회 조건*/
	private JCheckBox[]		  	chooseC;
	private JComboBox<String> 	findC; 
	private JTextField		  	wordTf;
	private JButton			  	searchBt;
	
	/**조회 내용 표시할 table*/
	private DefaultTableModel 	homeModel;
	private JTable			  	homeTable;
	private JScrollPane		  	homePan;
	private String[]		  	title = { "번호", "동", "아파트이름", "거래금액", "거래종류" };
	
	
	/** 상가 조회 내용 표시할 table */
	private DefaultTableModel 	storeModel;
	private JTable				storeTable;
	private JScrollPane		  	storePan;
	private String[]		  	storeTitle = { "번호", "상호명", "분류", "동", "지번주소" };
	
	
	/** 환경 정보 내용 표시할 table */
	private DefaultTableModel 	envModel;
	private JTable				envTable;
	private JScrollPane		  	envPan;
	private String[]		  	envTitle = { "업체명", "지도점검일자", "점검사항", "지도점검구분", "동" };
	
	
	/**검색  조건*/
	private String	key;
	
	/**검색할 단어*/
	private String  word;
	private boolean[] searchType = { true, true, true, true };
	private String[] choice = { "all", "dong", "name" };
	
	/**화면에 표시하고 있는 주택*/
	private HomeDeal curHome;

	
	private void showHomeInfo(int code) {
		
		curHome = homeService.search(code);
		System.out.println(curHome);
		
		//foodInfoL[0].setText(""+curfood.getCode());
		homeInfoL[0].setText("");
		homeInfoL[1].setText("");
		homeInfoL[2].setText(curHome.getAptName());
		homeInfoL[3].setText(""+curHome.getDealAmount());
		String rent = curHome.getRentMoney();
		if(rent == null) {
			homeInfoL[4].setText("없음");
		}else {
			homeInfoL[4].setText(curHome.getRentMoney());
		}
		homeInfoL[5].setText(""+curHome.getBuildYear());
		homeInfoL[6].setText(curHome.getArea()+"");
		homeInfoL[7].setText(String.format("%d년 %d월 %d일"
											,curHome.getDealYear()
											,curHome.getDealMonth()
											,curHome.getDealDay()
											));
		homeInfoL[8].setText(curHome.getDong());
		homeInfoL[9].setText(curHome.getJibun());
		
		//System.out.println("###############" + curHome.getImg());
		
		ImageIcon icon = null;
		if( curHome.getImg() != null && curHome.getImg().trim().length() != 0) {
			icon = new ImageIcon("img/" + curHome.getImg());
			System.out.println("#####" + icon.toString() + "####");
		}else {
			icon = new ImageIcon("img/다세대주택.jpg");
		}

		imgL.setIcon(icon);
		  
		  

//		Image img = null;
//		try {
//			img = ImageIO.read(new File("img/"+curHome.getImg()));
//         } catch (IOException ex) {
//        	 try {
//        		 img = ImageIO.read(new File("img/다세대주택.jpg"));
//			} catch (Exception e) {
//			}
//         }
//		img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//		imgL.setIcon(new ImageIcon(img));
	}
	
	public HomeInfoView(){
		/*Service들 생성 */
		homeService = new HomeServiceImpl();
		
		/*메인 화면 설정 */
		frame = new JFrame("WhereIsMyHome -- 아파트 거래 정보");
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e){
//				frame.dispose();
//			}
//		});
		
		setMain();
		
		frame.setSize(1200, 800);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showHomeInfo(1);
		//showHomes();
	}

	/**메인 화면인 주택 목록을 위한 화면 셋팅하는 메서드  */
	public void setMain(){
		
		/*왼쪽 화면을 위한 설정 */
		JPanel left = new JPanel(new GridLayout(2,1));
		
		JPanel leftCenter = new JPanel(new GridLayout());
		
		JPanel leftTop = new JPanel(new GridLayout(2,1)); // 상가
		JPanel leftBottom = new JPanel(new GridLayout(1, 2));
		//1,2  10,2
		JPanel leftBottomR = new JPanel(new GridLayout(10, 2));
		leftBottomR.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 10 , 10));
		
		
		String[] info= {"","","주택명","거래금액","월세금액","건축연도","전용면적","거래일","법정동","지번"};
		int size = info.length;
		JLabel infoL[] = new JLabel[size];
		homeInfoL = new JLabel[size];
		for (int i = 0; i <size; i++) {
			infoL[i] = new JLabel(info[i]);
			homeInfoL[i]=new JLabel("");
			leftBottomR.add(infoL[i]);
			leftBottomR.add(homeInfoL[i]);
		}
		imgL = new JLabel();
		leftBottom.add(imgL);
		leftBottom.add(leftBottomR,"South");
		
		storeModel = new DefaultTableModel(storeTitle,30);
		storeTable = new JTable(storeModel);
		storePan = new JScrollPane(storeTable);
		storeTable.setColumnSelectionAllowed(true);
		
		
		
		
//		leftTop.add(new JLabel("상가 정보", JLabel.CENTER),"North");
		leftTop.add(storePan,"Center");
		left.add(leftTop,"South");
		
		
		envModel = new DefaultTableModel(envTitle,30);
		envTable = new JTable(envModel);
		envPan = new JScrollPane(envTable);
		envTable.setColumnSelectionAllowed(true);
		
		leftCenter.add(envPan,"Center");
		leftTop.add(leftCenter,"North");
		
		left.add(leftBottom,"South");
		
		
		/*오른쪽 화면을 위한 설정 */
		JPanel right = new JPanel(new BorderLayout());
		JPanel rightTop = new JPanel(new GridLayout(4, 2));
		JPanel rightTop1 = new JPanel(new GridLayout(1, 4));
		String[] chooseMeg= { "아파트 매매", "아파트 전월세", "주택 매매", "주택 전월세"};
		chooseC = new JCheckBox[chooseMeg.length];
		for (int i = 0, len= chooseMeg.length; i < len; i++) {
			chooseC[i] = new JCheckBox(chooseMeg[i], true);
			rightTop1.add(chooseC[i]);
		}
		
		
		JPanel rightTop2 = new JPanel(new GridLayout(1, 3));
		String[] item = {"---all---","동","아파트 이름"}; 
		findC = new JComboBox<String>(item);
		wordTf = new JTextField();
		searchBt = new JButton("검색");
		
		rightTop2.add(findC);
		rightTop2.add(wordTf);
		rightTop2.add(searchBt);
		
		rightTop.add(new Label(""));
		rightTop.add(rightTop1);
		rightTop.add(rightTop2);		
		rightTop.add(new Label(""));
		
		JPanel rightCenter = new JPanel(new BorderLayout());
		homeModel = new DefaultTableModel(title,20);
		homeTable = new JTable(homeModel);
		homePan = new JScrollPane(homeTable);
		homeTable.setColumnSelectionAllowed(true);
		
		rightCenter.add(new JLabel("거래 내역", JLabel.CENTER),"North");
		rightCenter.add(homePan,"Center");
		
		right.add(rightTop,"North");
		right.add(rightCenter,"Center");
		
		JPanel mainP = new JPanel(new GridLayout(1, 2));
		
		mainP.add(left);
		mainP.add(right);
		
		mainP.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
		frame.add(mainP,"Center");
		
		/*이벤트 연결*/

		homeTable.addMouseListener( new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            int row =  homeTable.getSelectedRow();
	            System.out.println("선택된 row : " + row);
	            System.out.println("선택된 row의 동 이름 :"+homeModel.getValueAt(row, 1));
	            
	            List<StoreInfo> storeList = homeService.storeSearch((homeModel.getValueAt(row, 1).toString()));
	            
	            if(storeList!=null){
	                int i=0;
	                String[][]data = new String[storeList.size()][6];
	                for (StoreInfo store: storeList) {
	                    data[i][0]= ""+store.getNo();
	                    data[i][1]= store.getStoreName();
	                    data[i][2]= store.getClassifyName();
	                    data[i][3]= store.getDong();
	                    data[i++][4] = store.getAddress();
	                }
	                storeModel.setDataVector(data, storeTitle);
	            }
	            
	            List<EnvInfo> envList = homeService.envSearch((homeModel.getValueAt(row, 1).toString()));
	            //업체(시설)명,지도점검일자,점검사항,지도점검구분,소재지주소
	            if(envList!=null){
	                int i=0;
	                String[][]data = new String[envList.size()][6];
	                for (EnvInfo env: envList) {
	                    data[i][0]= ""+env.getName();
	                    data[i][1]= env.getDate();
	                    data[i][2]= env.getContent();
	                    data[i][3]= env.getResult();
	                    data[i++][4] = env.getDong();
	                }
	                envModel.setDataVector(data, envTitle);
	            }
	            
	            int code = Integer.parseInt(((String)homeModel.getValueAt(row, 0)).trim());
	            showHomeInfo(code);
	        }
	    });
		
		// complete code #01
		// 아래의 코드를 참조하여 아래 라인을 uncomment 하고 searchBt.addActionList() 를 Lambda 표현식으로 바꾸세요.
		//searchBt.addActionListener(/* 여기에 Lambda 표현식 작성 */);
		
		// 참조코드 시작 - 위 코드를 완성 후 삭제 또는 comment 처리하세요.
//		ActionListener buttonHandler = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				searchHomes();
//			}
//		};
//		
//		searchBt.addActionListener( buttonHandler );

		// 참조코드 종료
		
		searchBt.addActionListener(e -> searchHomes());
		
		
//		button.addActionListener(e -> System.out.println("람다 리스너를 이용한 버튼클릭"));
		
		showHomes();
	}
	
	
	/**검색 조건에 맞는 주택 정보 검색 */
	private void searchHomes() {
		for(int i = 0, size = chooseC.length; i<size; i++) {
			if(chooseC[i].isSelected()) {
				searchType[i] = true;
			}else {
				searchType[i] = false;
			}
		}
		word = wordTf.getText().trim();
		key = choice[findC.getSelectedIndex()];
		System.out.println("word:"+word+" key:"+key);
		showHomes();		
	}
	/**
	 * 주택 목록을  갱신하기 위한 메서드 
	 */
	public void showHomes(){
		HomePageBean  bean = new HomePageBean();
		bean.setSearchType(searchType);
		if(key !=null) {
			if(key.equals("dong")) {
				bean.setDong(word);
			}else if(key.equals("name")) {
				bean.setAptname(word);
			}
		}
		
		List<HomeDeal> deals = homeService.searchAll(bean);
		if(deals!=null){
			int i=0;
			String[][]data = new String[deals.size()][5];
			for (HomeDeal deal: deals) {
				data[i][0]= ""+deal.getNo();
				data[i][1]= deal.getDong();
				data[i][2]= deal.getAptName();
				data[i][3]= deal.getDealAmount();
				data[i++][4]= deal.getType();
			}
			homeModel.setDataVector(data, title);
		}
	}
//	public static void main(String[] args) {
//		new HomeInfoView();
//	}
	
}

