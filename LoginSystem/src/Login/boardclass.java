package Login;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Login.joinclass.RoundtextField;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class boardclass {
	static String userIDD;
	JFrame frame;
	/**
	 * 어플리케이션 실행 메소드
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					boardclass window = new boardclass(userIDD);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 어플리케이션 생성자
	 */
	public boardclass(String userIDD) {
        this.userIDD = userIDD;
        initialize();
    }

	/**
	 * 프레임 내용 초기화 메소드
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 800); // 프레임 위치 및 크기 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 동작 설정
		frame.getContentPane().setLayout(null); // 레이아웃 설정 (null은 절대 위치 배치)

		// 테두리 스타일 설정
		Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
		Border borderBlue = BorderFactory.createLineBorder(new Color(155, 155, 155), 2);
		Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 0, 625, 755);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255)); // 배경색 설정

		
        //에브리소통 그림2
        ImageIcon everyImageIcon = new ImageIcon("src/img/everygood.png");
        Image everyImage = everyImageIcon.getImage();
        Image every1Image = everyImage.getScaledInstance(120, 30, Image.SCALE_SMOOTH);
        ImageIcon every1ImageIcon = new ImageIcon(every1Image);
        JButton  image1Button = new JButton (every1ImageIcon);	         
        image1Button.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        image1Button.setBounds(30, 40, 120, 30);
        image1Button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        image1Button.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				boardclass b = new boardclass(userIDD); 
				b.showFrame();
				frame.dispose();
			}
		});panel.add(image1Button);
		

		//게시판 버튼
		JButton btnClickMe = new JButton("게시판"); // 버튼 생성 및 텍스트 설정
		btnClickMe.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				boardclass b = new boardclass(userIDD); // abc 클래스의 생성자 호출
				b.showFrame();
				frame.dispose();
			}
		});
		btnClickMe.setBounds(210, 70, 100, 45); // 버튼 위치 및 크기 설정
		btnClickMe.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		btnClickMe.setForeground(new Color(255, 0, 0)); // 텍스트 색상(흰색)
		panel.add(btnClickMe); // 패널에 버튼 추가
		Font buttonFont = new Font(btnClickMe.getFont().getName(), Font.PLAIN, 18);
		btnClickMe.setFont(buttonFont);
		btnClickMe.setBorder(borderWhite); // 테두리 설정

	
		
        //시간표 버튼
        JButton Schedule = new JButton("시간표"); // 버튼 생성 및 텍스트 설정
        Schedule.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				scheduleclass a = new scheduleclass(userIDD); // abc 클래스의 생성자 호출 수정
				a.showFrame();
				frame.dispose();
			}
		});
        Schedule.setBounds(320, 70, 100, 45); // 버튼 위치 및 크기 설정
        Schedule.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        Schedule.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
        panel.add(Schedule); // 패널에 버튼 추가
		Font ScheduleFont = new Font(Schedule.getFont().getName(), Font.PLAIN, 18);
		Schedule.setFont(ScheduleFont);
		Schedule.setBorder(borderWhite); // 테두리 설정

		
		
		//회원 버튼
        JButton member = new JButton("내정보"); // 버튼 생성 및 텍스트 설정
        member.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				mypageclass my = new mypageclass(userIDD); // abc 클래스의 생성자 호출 수정
				my.showFrame();
				frame.dispose();
			}
		});
        member.setBounds(530, 30, 70, 30); // 버튼 위치 및 크기 설정
        member.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        member.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
        panel.add(member); // 패널에 버튼 추가
		Font memberbuttonFont = new Font(member.getFont().getName(), Font.BOLD, 12);
		member.setFont(memberbuttonFont);
		member.setBorder(borderWhite); // 테두리 설정

		
		//검색창 박스
		joinclass outerInstance = new joinclass();
		RoundtextField textField = outerInstance.new RoundtextField(); // 텍스트 필드 생성
		textField.setBounds(130, 140, 360, 43); // 텍스트 필드 위치 및 크기 설정
		textField.setBackground(new Color(200, 200, 200)); // 배경색 설정
		textField.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
		panel.add(textField); // 패널에 텍스트 필드 추가
		textField.setColumns(10); // 텍스트 필드 컬럼 수 설정
		textField.setBorder(borderBlue); // 테두리 설정
		
		
		
		//검색 버튼
		JButton lblId = new JButton("검색"); // 레이블 생성 및 텍스트 설정
		lblId.setBounds(490, 140, 30, 43); // 레이블 위치 및 크기 설정
		lblId.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		lblId.setForeground(new Color(155, 155, 155)); // 텍스트 색상(흰색)
		panel.add(lblId); // 패널에 레이블 추가
		lblId.setBorder(borderWhite); // 테두리 설정

		JTextArea textArea = new JTextArea(); // 텍스트 에어리어 생성
		//textArea.setBounds(131, 390, 463, 193); // 텍스트 에어리어 위치 및 크기 설정
		panel.add(textArea); // 패널에 텍스트 에어리어 추가
		
			
		//자유게시판 박스
		JButton board1 = new JButton("자유게시판"); // 버튼 생성 및 텍스트 설정
		board1.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				freeboardclass c = new freeboardclass(userIDD, "001"); 
				c.showFrame();
				frame.dispose();
			}
		});
		board1.setBounds(60, 210, 250, 30); // 버튼 위치 및 크기 설정
		board1.setBackground(new Color(230, 230, 230));// 배경색 설정 (빨간색)
		board1.setForeground(new Color(80, 80, 80)); // 텍스트 색상(흰색)
		board1.setBorder(borderBlack); // 테두리 설정
		panel.add(board1); // 패널에 버튼 추가
		board1.setBorder(borderWhite); // 테두리 설정
		
		
		
		 // 자유게시판 아래에 추가 패널 생성
		JPanel Panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Panel_1.setBounds(60, 239, 250, 181);
        Panel_1.setBackground(new Color(230, 230, 230));
        Panel_1.setBorder(borderBlack);
        panel.add(Panel_1);
        Panel_1.setBorder(borderWhite); // 테두리 설정
			
        
        
     
        
        
        
        
		JButton board2 = new JButton("비밀게시판"); // 버튼 생성 및 텍스트 설정
		board2.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				freeboardclass c = new freeboardclass(userIDD, "002");
				c.showFrame();
				frame.dispose();
			}
		});
		board2.setBounds(320, 210, 250, 30); // 버튼 위치 및 크기 설정
		board2.setBackground(new Color(230, 230, 230));// 배경색 설정 (빨간색)
		board2.setForeground(new Color(80, 80, 80)); // 텍스트 색상(흰색)
		board2.setBorder(borderBlack); // 테두리 설정
		panel.add(board2); // 패널에 버튼 추가
		board2.setBorder(borderWhite); // 테두리 설정
		
		
		 // 자유게시판 아래에 추가 패널 생성
		JPanel Panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Panel_2.setBounds(320, 239, 250, 181);
        Panel_2.setBackground(new Color(230, 230, 230));
        Panel_2.setBorder(borderBlack);
        panel.add(Panel_2);
        Panel_2.setBorder(borderWhite); // 테두리 설정
			
        
        
        

        
        
        
		//신입생게시판 박스
		JButton board3 = new JButton("신입생게시판"); // 버튼 생성 및 텍스트 설정
		board3.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				freeboardclass c = new freeboardclass(userIDD, "003"); 
				c.showFrame();
				frame.dispose();
			}
		});
		board3.setBounds(60, 435, 250, 30); // 버튼 위치 및 크기 설정
		board3.setBackground(new Color(230, 230, 230));// 배경색 설정 (빨간색)
		board3.setForeground(new Color(80, 80, 80)); // 텍스트 색상(흰색)
		board3.setBorder(borderBlack); // 테두리 설정
		panel.add(board3); // 패널에 버튼 추가
		board3.setBorder(borderWhite); // 테두리 설정
		
		
		
		 // 자유게시판 아래에 추가 패널 생성
		JPanel Panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Panel_3.setBounds(60, 464, 250, 181);
        Panel_3.setBackground(new Color(230, 230, 230));
        Panel_3.setBorder(borderBlack);
        panel.add(Panel_3);
        Panel_3.setBorder(borderWhite); // 테두리 설정
			
        
        
        
        
        
        
        
        // 졸업생게시판
		JButton board4 = new JButton("졸업생게시판"); // 버튼 생성 및 텍스트 설정
		board4.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				freeboardclass c = new freeboardclass(userIDD, "004");
				c.showFrame();
				frame.dispose();
			}
		});
		board4.setBounds(320, 435, 250, 30); // 버튼 위치 및 크기 설정
		board4.setBackground(new Color(230, 230, 230));// 배경색 설정 (빨간색)
		board4.setForeground(new Color(80, 80, 80)); // 텍스트 색상(흰색)
		board4.setBorder(borderBlack); // 테두리 설정
		panel.add(board4); // 패널에 버튼 추가
		board4.setBorder(borderWhite); // 테두리 설정
		
		
		 // 자유게시판 아래에 추가 패널 생성
		JPanel Panel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        Panel_4.setBounds(320, 464, 250, 181);
        Panel_4.setBackground(new Color(230, 230, 230));
        Panel_4.setBorder(borderBlack);
        panel.add(Panel_4);
        Panel_4.setBorder(borderWhite); // 테두리 설정
			
		
		
        
        
		
	}
	   public void showFrame() {
	        frame.setVisible(true);
	    }
}
