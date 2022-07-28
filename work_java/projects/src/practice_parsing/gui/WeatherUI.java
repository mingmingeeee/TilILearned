package practice_parsing.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import practice_parsing.Weather;
import practice_parsing.dom.WeatherDomParser;

import java.io.*;
import java.util.*;

public class WeatherUI extends JFrame {

	private static final long serialVersionUID = 1L;

	// 컴포넌트
	JButton button = null;
	JTable table = null;

	// table의 데이터 관리하는 객체
	DefaultTableModel model = null;

	public static void main(String[] args) {
		WeatherUI ui = new WeatherUI();
		ui.launchUI();
	}

	private void launchUI() {
		button = new JButton("날씨 정보 보기");

		// 테이블 구성
		table = new JTable();
		String[] header = { "시간", "기온", "날씨", "습도" };
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(header);

		// 이벤트 리스너 등록 처리
		addEventListener();

		// 요소 배치
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);

		this.setTitle("오늘의 날씨");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	private void addEventListener() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 기존 자료 삭제
				model.setRowCount(0);
				// 새로운 자료 조회
				WeatherDomParser parser = new WeatherDomParser();
				List<Weather> list = parser.getWeather();
				for (Weather info : list) {
					model.addRow(new Object[] { info.getHour(), info.getTemp(), info.getWfKor(), info.getReh() });
				}
				// model의 데이터가 변경되었음을 알림
				model.fireTableDataChanged();
			}
		});

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				String nm = model.getValueAt(row, 2).toString();
				JOptionPane.showMessageDialog(WeatherUI.this, "선택된 날씨: " + nm);
			}

		});
	}

}
