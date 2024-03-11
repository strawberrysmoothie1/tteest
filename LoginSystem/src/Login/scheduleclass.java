package Login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import javax.swing.JFrame;

public class scheduleclass {
	static String userIDD;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					scheduleclass window = new scheduleclass(userIDD);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class RoundedBorder implements Border {
	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius + 1);
	    }

	    public boolean isBorderOpaque() {
	        return true;
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	    }
	}

	/**
	 * Create the application.
	 */
	public scheduleclass(String userIDD) {
        this.userIDD = userIDD;
        initialize();
    }
	/**
	 * Initialize the contents of the frame.
	 */
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
		Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
		
		
		
		
		// 테두리 스타일 설정
		Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);

		JPanel panel = new JPanel();
		panel.setBounds(5, 0, 625, 755);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255)); // 배경색 설정

//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(new Color(255, 255, 255));
//		panel_1.setBounds(0, 0, 625, 755);
//		panel.add(panel_1);
//		panel_1.setLayout(null);
//		panel_1.setBorder(border); // 테두리 설정
			

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
		btnClickMe.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
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
        Schedule.setForeground(new Color(255, 0, 0)); // 텍스트 색상(흰색)
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
	    member.setBounds(520, 30, 70, 30); // 버튼 위치 및 크기 설정
	    member.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        member.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
        panel.add(member); // 패널에 버튼 추가
		Font memberbuttonFont = new Font(member.getFont().getName(), Font.BOLD, 12);
		member.setFont(memberbuttonFont);  
		member.setBorder(borderWhite); // 테두리 설정
		}
	
	
	
	
	
	public void showFrame() {
        frame.setVisible(true);
    }
}

