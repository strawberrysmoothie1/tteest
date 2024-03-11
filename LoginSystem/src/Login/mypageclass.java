package Login;

import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import DBpackage.DBConnection;
import DBpackage.DBConnection.UniversityInfo;
import Login.joinclass.RoundtextField;
import Login.loginclass.RoundPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Desktop.Action;

import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.border.*;


class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 2, this.radius + 2, this.radius + 3, this.radius + 2);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(c.getBackground());
        graphics.fillRoundRect(x, y, width, height, radius, radius);

        graphics.setColor(c.getForeground());
        graphics.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}



class RoundedButton extends JButton {
    public RoundedButton() { super(); decorate(); } 
    public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override 
    protected void paintComponent(Graphics g) {
  	    int width = getWidth();
  	    int height = getHeight();
  	    Graphics2D graphics = (Graphics2D) g;
  	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  	    if (getModel().isArmed()) {
  	        graphics.setColor(getBackground().darker());
  	    } else if (getModel().isRollover()) {
  	        graphics.setColor(getBackground().brighter());
  	    } else {
  	        graphics.setColor(getBackground());
  	    }
  	    graphics.fillRoundRect(0, 0, width, height, 10, 10);
  	    FontMetrics fontMetrics = graphics.getFontMetrics();
  	    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
  	    int textX = (width - stringBounds.width) / 2;
  	    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
  	    graphics.setColor(getForeground());
  	    graphics.setFont(getFont());
  	    graphics.drawString(getText(), textX, textY);
  	    graphics.dispose();
  	    super.paintComponent(g);
  	}
    }


public class mypageclass {

	JFrame frame;
	static String userIDD;
	//이미지
	String imagePath = "LoginSystem/src/img/22.png"; 
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mypageclass window = new mypageclass(userIDD);
					window.frame.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
	}

	/**
	 * 어플리케이션 생성자
	 */
	   public mypageclass(String userIDD) {
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
		Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 0, 625, 755);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255)); // 배경색 설정
	
        // 마이 페이지 라벨
        JLabel mypageLabel = new JLabel("마이 페이지");
        mypageLabel.setBounds(253, 95, 150, 30); // Adjust the position and size as needed
        mypageLabel.setForeground(new Color(100, 100, 100)); // Set label text color
        Font labelFont2 = new Font(mypageLabel.getFont().getName(), Font.BOLD, 22);
        mypageLabel.setFont(labelFont2);
        panel.add(mypageLabel);    
		
		
		
		//게시판 버튼
				JButton btnClickMe = new JButton("게시판"); // 버튼 생성 및 텍스트 설정
				btnClickMe.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
					public void actionPerformed(ActionEvent e) {
						boardclass b = new boardclass(userIDD); // abc 클래스의 생성자 호출
						b.showFrame();
						frame.dispose();
					}
				});
				btnClickMe.setBounds(210, 32, 100, 45); // 버튼 위치 및 크기 설정
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
		        Schedule.setBounds(320, 32, 100, 45); // 버튼 위치 및 크기 설정
		        Schedule.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Schedule.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
				panel.add(Schedule); // 패널에 버튼 추가
				Font ScheduleFont = new Font(Schedule.getFont().getName(), Font.PLAIN, 18);
				Schedule.setFont(ScheduleFont);
				Schedule.setBorder(borderWhite); // 테두리 설정

				
		

				 //패널 생성
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setBounds(73, 140, 480, 100);
				panel.add(panel_1);
				panel_1.setLayout(null);
				panel_1.setBorder(border); // 테두리 설정
		        RoundedBorder roundedBorder = new RoundedBorder(20);
		        panel_1.setBorder(roundedBorder);

		        
		        
				//유저 
		        ImageIcon useroriginalImageIcon = new ImageIcon("src/img/user.png");
		        Image useroriginalImage = useroriginalImageIcon.getImage();
		        Image userscaledImage = useroriginalImage.getScaledInstance(40, 37, Image.SCALE_SMOOTH);
		        ImageIcon userscaledImageIcon = new ImageIcon(userscaledImage);
		        JLabel userimageLabel = new JLabel(userscaledImageIcon);
		        userimageLabel.setBounds(11, 34, 50, 50);
		        panel_1.add(userimageLabel);
		        
				
		        
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
		        	        		        
		        		   
		        // 내 정보 라벨
		        JLabel myinforLabel = new JLabel("내 정보");
		        myinforLabel.setBounds(15, 6, 150, 30); // Adjust the position and size as needed
		        myinforLabel.setForeground(new Color(0, 0, 0)); // Set label text color
		        Font labelFont3 = new Font(myinforLabel.getFont().getName(), Font.BOLD, 16);
		        myinforLabel.setFont(labelFont3);
		        panel_1.add(myinforLabel);    
				
		        
		        //로그아웃 버튼
		        RoundedButton logoutbutton = new RoundedButton("로그아웃");
		        logoutbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	loginclass a = new loginclass(); // abc 클래스의 생성자 호출 수정
						a.showFrame();
						frame.dispose();
		                // 버튼 동작 추가
		            }
		        });
		        logoutbutton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
		        logoutbutton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
		        logoutbutton.setBounds(385, 15, 70, 25);
		        panel_1.add(logoutbutton);
		        
		        //로그인 화면에서 아이디 받고 그 아이디로 유저 정보찾기

                String SearchNickname = DBConnection.FindUserInformation("users", "Nickname","UserID", userIDD);
                String SearchSchoolcode = DBConnection.FindUserInformation("users", "UID", "UserID", userIDD);
                String SearchSchoolname = DBConnection.FindUserInformation("university", "UName",  "UID" , SearchSchoolcode);
                String Searchdepartment = DBConnection.FindUserInformation("university","department","UID" , SearchSchoolcode);
		        
		        JLabel Nickname = new JLabel(SearchNickname + " /" +userIDD);
		        Nickname.setBounds(67, 40, 150, 13); // Adjust the position and size as needed
		        Nickname.setForeground(new Color(0, 0, 0)); // Set label text color
		        Font labelFont4 = new Font(Nickname.getFont().getName(), Font.BOLD, 12);
		        Nickname.setFont(labelFont4);
		        panel_1.add(Nickname);   
		        
		        JLabel Schoolname = new JLabel(SearchSchoolname);
		        Schoolname.setBounds(69, 57, 60, 10); // Adjust the position and size as needed
		        Schoolname.setForeground(new Color(100, 100, 100)); // Set label text color
		        Font labelFont5 = new Font(Schoolname.getFont().getName(), Font.BOLD, 9);
		        Schoolname.setFont(labelFont5);
		        panel_1.add(Schoolname);   
		        
		        JLabel department = new JLabel(Searchdepartment);
		        department.setBounds(69, 68, 100, 10); // Adjust the position and size as needed
		        department.setForeground(new Color(100, 100, 100)); // Set label text color
		        department.setFont(labelFont5);
		        panel_1.add(department);   
		        
		        
				 //패널 생성
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(255, 255, 255));
				panel_2.setBounds(73, 250, 480, 135);
				panel.add(panel_2);
				panel_2.setLayout(null);
				panel_2.setBorder(border); // 테두리 설정
		        RoundedBorder roundedBorder2 = new RoundedBorder(20);
		        panel_2.setBorder(roundedBorder2);
		        
		        
		        // 계정 라벨
		        JLabel AccountLabel = new JLabel("계정");
		        AccountLabel.setBounds(15, 11, 70, 20); // Adjust the position and size as needed
		        AccountLabel.setForeground(new Color(0, 0, 0)); // Set label text color
		        AccountLabel.setFont(labelFont3);
		        panel_2.add(AccountLabel);    

		        
		        //비밀번호 변경
		        JButton PWchangebutton = new JButton("비밀번호 변경");
		        PWchangebutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	PWchangeDialog();
		            	
		            }
		        });
		        PWchangebutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        PWchangebutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        PWchangebutton.setBounds(20, 45, 450, 25);
		        PWchangebutton.setHorizontalAlignment(SwingConstants.LEFT);
		        PWchangebutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Font labelFont6 = new Font(Schoolname.getFont().getName(), Font.BOLD, 12);
		        PWchangebutton.setFont(labelFont6);
		        panel_2.add(PWchangebutton);
		        
		        
		        //학교 변경
		        JButton Schoolchangebutton = new JButton("학교 설정");
		        Schoolchangebutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

		            	SchoolchangeDialog();
		            }
		        });
		        Schoolchangebutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Schoolchangebutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        Schoolchangebutton.setBounds(20, 70, 450, 25);
		        Schoolchangebutton.setHorizontalAlignment(SwingConstants.LEFT);
		        Schoolchangebutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Schoolchangebutton.setFont(labelFont6);
		        panel_2.add(Schoolchangebutton);
		        
		        
		        
		        //학과 변경
		        JButton Departmentchangebutton = new JButton("학과 설정");
		        Departmentchangebutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

		            	DepartmentchangeDialog();
		            }
		        });
		        Departmentchangebutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Departmentchangebutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        Departmentchangebutton.setBounds(20, 95, 450, 25);
		        Departmentchangebutton.setHorizontalAlignment(SwingConstants.LEFT);
		        Departmentchangebutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Departmentchangebutton.setFont(labelFont6);
		        panel_2.add(Departmentchangebutton);
		        
		        

		        
				JPanel panel_3 = new JPanel();
				panel_3.setBackground(new Color(255, 255, 255));
				panel_3.setBounds(73, 395, 480, 160);
				panel.add(panel_3);
				panel_3.setLayout(null);
				panel_3.setBorder(border); // 테두리 설정
		        RoundedBorder roundedBorder3 = new RoundedBorder(20);
		        panel_3.setBorder(roundedBorder3);
		        
		        // 커뮤니티 라벨
		        JLabel CommunityLabel = new JLabel("커뮤니티");
		        CommunityLabel.setBounds(15, 11, 70, 20); // Adjust the position and size as needed
		        CommunityLabel.setForeground(new Color(0, 0, 0)); // Set label text color
		        CommunityLabel.setFont(labelFont3);
		        panel_3.add(CommunityLabel);    
		
		
		        //닉네임 설정
		        JButton Nickchangebutton = new JButton("닉네임 설정");
		        Nickchangebutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	NicknamechangeDialog();
		            	
		            }
		        });
		        Nickchangebutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Nickchangebutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        Nickchangebutton.setBounds(20, 45, 450, 25);
		        Nickchangebutton.setHorizontalAlignment(SwingConstants.LEFT);
		        Nickchangebutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Nickchangebutton.setFont(labelFont6);
		        panel_3.add(Nickchangebutton);
		        
		        
		        //내가 쓴 글
		        JButton WrittenBbutton = new JButton("내가 쓴 글");
		        WrittenBbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

		            	
		            }
		        });
		        WrittenBbutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        WrittenBbutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        WrittenBbutton.setBounds(20, 70, 450, 25);
		        WrittenBbutton.setHorizontalAlignment(SwingConstants.LEFT);
		        WrittenBbutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        WrittenBbutton.setFont(labelFont6);
		        panel_3.add(WrittenBbutton);
		        
		        
		        
		        //댓글 단 글
		        JButton Commentbutton = new JButton("댓글 단 글");
		        Commentbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

		            	
		            }
		        });
		        Commentbutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Commentbutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        Commentbutton.setBounds(20, 95, 450, 25);
		        Commentbutton.setHorizontalAlignment(SwingConstants.LEFT);
		        Commentbutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Commentbutton.setFont(labelFont6);
		        panel_3.add(Commentbutton);
		
		        //이용 제한 내역
		        JButton Limitbutton = new JButton("이용 제한 내역");
		        Limitbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

		            	
		            }
		        });
		        Limitbutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		        Limitbutton.setForeground(new Color(100, 100, 100)); // 텍스트 색상(흰색)
		        Limitbutton.setBounds(20, 120, 450, 25);
		        Limitbutton.setHorizontalAlignment(SwingConstants.LEFT);
		        Limitbutton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		        Limitbutton.setFont(labelFont6);
		        panel_3.add(Limitbutton);
		
		
		
		        
	}
	
	
	   public class RoundtextField extends JTextField {
		      public RoundtextField() { super(); decorate(); } 
		      public RoundtextField(String text) { super(text); decorate(); } 
		      protected void decorate() { setOpaque(false); 
		      setHorizontalAlignment(JTextField.LEFT);}
		      @Override 
		      protected void paintComponent(Graphics g) {
		          int width = getWidth();
		          int height = getHeight();
		          Graphics2D graphics = (Graphics2D) g;
		          graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		          graphics.setColor(getBackground());
		          graphics.fillRoundRect(0, 0, width, height, 10, 10);

		          FontMetrics fontMetrics = graphics.getFontMetrics();
		          Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		          int textX = 8;
		          int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

		          graphics.setColor(getForeground());
		          graphics.setFont(getFont());
		          graphics.drawString(getText(), textX, textY);

		          graphics.dispose();
		          super.paintComponent(g);
		      }
	      }
	
	   private void SchoolchangeDialog() {
			// 테두리 스타일 설정
			Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			Border borderBlue = BorderFactory.createLineBorder(new Color(155, 155, 155), 2);
			Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
			Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			
			 String SearchNickname = DBConnection.FindUserInformation("users", "Nickname","UserID", userIDD);
	         String SearchSchoolcode = DBConnection.FindUserInformation("users", "UID", "UserID", userIDD);
	         String SearchSchoolname = DBConnection.FindUserInformation("university", "UName",  "UID" , SearchSchoolcode);
	         String Searchdepartment = DBConnection.FindUserInformation("university","department","UID" , SearchSchoolcode);
				
				
			
			
			
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(250, 280, 350, 400);
		    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // 다이얼로그 패널 생성
		    JPanel dialogPanel = new JPanel();
		    //dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		    dialogPanel.setBounds(5, 0, 100, 100);
			frame.getContentPane().add(dialogPanel);
			dialogPanel.setLayout(null);
			dialogPanel.setBackground(new Color(255, 255, 255)); // 배경색 설정		    
			dialogFrame.add(dialogPanel);


	        // 변경 라벨
	        JLabel ChangePWLabel = new JLabel("학교 설정");
	        ChangePWLabel.setBounds(135, 11, 150, 30); // Adjust the position and size as needed
	        ChangePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font ChangePWFont = new Font(ChangePWLabel.getFont().getName(), Font.BOLD, 14);
	        ChangePWLabel.setFont(ChangePWFont);
	        dialogPanel.add(ChangePWLabel);  
					
			
			//취소 버튼
	        JButton BackButton = new JButton("취소"); // 버튼 생성 및 텍스트 설정
	        BackButton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
				public void actionPerformed(ActionEvent e) {
					dialogFrame.dispose();
				}
			});
	        BackButton.setBounds(282, 10, 60, 20); // 버튼 위치 및 크기 설정
	        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정
	        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
	        dialogPanel.add(BackButton); // 패널에 버튼 추가
			Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
			BackButton.setFont(memberbuttonFont);
	 		BackButton.setBorder(borderWhite); // 테두리 설정	
			BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			
			
	        
	        // 코드 라벨
	        JLabel universityLabel = new JLabel("학교 코드");
	        universityLabel.setBounds(70, 110, 150, 15); // Adjust the position and size as needed
	        universityLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font labelFont2 = new Font(universityLabel.getFont().getName(), Font.BOLD, 9);
	        universityLabel.setFont(labelFont2);
	        dialogPanel.add(universityLabel);     
	        // 학교 아이디 입력 필드
	        RoundtextField universityField = new RoundtextField();
	        universityField.setBounds(65, 125, 200, 35);
	        universityField.setBackground(new Color(200, 200, 200));
	        universityField.setForeground(new Color(255, 255, 255));
	        dialogPanel.add(universityField);
	        universityField.setColumns(10);
	        universityField.setBorder(borderBlack);
	        universityField.setText("코드 입력");
	        
	        JLabel univerLabel = new JLabel("");
	       
	        univerLabel.setForeground(new Color(0, 0, 255)); // Set label text color
	        univerLabel.setFont(labelFont2);
	        dialogPanel.add(univerLabel);
	        
	        
	        String lastTwoDigits = SearchSchoolcode.substring(SearchSchoolcode.length() - 2);

	        // 학교 검색 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
			RoundedButton searchButton = new RoundedButton("검색");
	        searchButton.setBounds(260, 132, 50, 20);
	        searchButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
	        searchButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
			Font searchFont = new Font(searchButton.getFont().getName(), Font.PLAIN, 10);
			searchButton.setFont(searchFont);
			dialogPanel.add(searchButton); // 패널에 버튼 추가
	        searchButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String schoolcode = universityField.getText()+ lastTwoDigits;
	                // 여기서 데이터베이스 조회하여 중복 여부 확인
	                UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);
	                
	                if (universityInfo != null) {
	                    univerLabel.setText(universityInfo.getUName());
	                    univerLabel.setForeground(new Color(0, 0, 255));
	                    univerLabel.setBounds(140, 163, 150, 15);
	                } else {
	                    univerLabel.setText("학교 정보를 찾을 수 없습니다.");
	                    univerLabel.setForeground(new Color(255, 0, 0));
	                    univerLabel.setBounds(105, 163, 150, 15);
	                }
	            }

				private boolean checkIfIDExists(String enteredID) {
					// TODO Auto-generated method stub
					return false; 
				}
	        });
	        
					
			
	        RoundedButton confirmButton = new RoundedButton("변경하기");
	        confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	loginclass a = new loginclass(); // abc 클래스의 생성자 호출 수정
	            	String schoolcode = universityField.getText()+ lastTwoDigits;
	            	UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);
	            	  if (universityInfo != null) {
	            		  
	            		DBConnection.ChangeUser(userIDD, "UID", schoolcode);
	            		dialogFrame.dispose();	            		
	            	
	            	  }
	            }
	        });
	        confirmButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
	        confirmButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
	        confirmButton.setBounds(135, 300, 65, 25);
	        dialogPanel.add(confirmButton);		
			
		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);

		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);		
	   }

	   private void DepartmentchangeDialog() {
			// 테두리 스타일 설정
			Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			Border borderBlue = BorderFactory.createLineBorder(new Color(155, 155, 155), 2);
			Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
			Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			
			 String SearchNickname = DBConnection.FindUserInformation("users", "Nickname","UserID", userIDD);
	         String SearchSchoolcode = DBConnection.FindUserInformation("users", "UID", "UserID", userIDD);
	         String SearchSchoolname = DBConnection.FindUserInformation("university", "UName",  "UID" , SearchSchoolcode);
	         String Searchdepartment = DBConnection.FindUserInformation("university","department","UID" , SearchSchoolcode);
				
				
			
			
			
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(250, 280, 350, 400);
		    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // 다이얼로그 패널 생성
		    JPanel dialogPanel = new JPanel();
		    //dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		    dialogPanel.setBounds(5, 0, 100, 100);
			frame.getContentPane().add(dialogPanel);
			dialogPanel.setLayout(null);
			dialogPanel.setBackground(new Color(255, 255, 255)); // 배경색 설정		    
			dialogFrame.add(dialogPanel);


	        // 변경 라벨
	        JLabel ChangePWLabel = new JLabel("학과 설정");
	        ChangePWLabel.setBounds(135, 11, 150, 30); // Adjust the position and size as needed
	        ChangePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font ChangePWFont = new Font(ChangePWLabel.getFont().getName(), Font.BOLD, 14);
	        ChangePWLabel.setFont(ChangePWFont);
	        dialogPanel.add(ChangePWLabel);  
					
			
			//취소 버튼
	        JButton BackButton = new JButton("취소"); // 버튼 생성 및 텍스트 설정
	        BackButton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
				public void actionPerformed(ActionEvent e) {
					dialogFrame.dispose();
				}
			});
	        BackButton.setBounds(282, 10, 60, 20); // 버튼 위치 및 크기 설정
	        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정
	        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
	        dialogPanel.add(BackButton); // 패널에 버튼 추가
			Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
			BackButton.setFont(memberbuttonFont);
	 		BackButton.setBorder(borderWhite); // 테두리 설정	
			BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			
			
	        
	        // 코드 라벨
	        JLabel universityLabel = new JLabel("학과 코드");
	        universityLabel.setBounds(70, 110, 150, 15); // Adjust the position and size as needed
	        universityLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font labelFont2 = new Font(universityLabel.getFont().getName(), Font.BOLD, 9);
	        universityLabel.setFont(labelFont2);
	        dialogPanel.add(universityLabel);     
	        // 학교 아이디 입력 필드
	        RoundtextField universityField = new RoundtextField();
	        universityField.setBounds(65, 125, 200, 35);
	        universityField.setBackground(new Color(200, 200, 200));
	        universityField.setForeground(new Color(255, 255, 255));
	        dialogPanel.add(universityField);
	        universityField.setColumns(10);
	        universityField.setBorder(borderBlack);
	        universityField.setText("코드 입력");
	        
	        JLabel univerLabel = new JLabel("");
	       
	        univerLabel.setForeground(new Color(0, 0, 255)); // Set label text color
	        univerLabel.setFont(labelFont2);
	        dialogPanel.add(univerLabel);
	        
	        
	        String firstThreeDigits = SearchSchoolcode.substring(0, 3);

	        // 학교 검색 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
			RoundedButton searchButton = new RoundedButton("검색");
	        searchButton.setBounds(260, 132, 50, 20);
	        searchButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
	        searchButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
			Font searchFont = new Font(searchButton.getFont().getName(), Font.PLAIN, 10);
			searchButton.setFont(searchFont);
			dialogPanel.add(searchButton); // 패널에 버튼 추가
	        searchButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String schoolcode = firstThreeDigits + universityField.getText();
	                // 여기서 데이터베이스 조회하여 중복 여부 확인
	                UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);
	                
	                if (universityInfo != null) {
	                    univerLabel.setText(universityInfo.getDepartment());
	                    univerLabel.setForeground(new Color(0, 0, 255));
	                    univerLabel.setBounds(140, 163, 150, 15);
	                } else {
	                    univerLabel.setText("학과 정보를 찾을 수 없습니다.");
	                    univerLabel.setForeground(new Color(255, 0, 0));
	                    univerLabel.setBounds(105, 163, 150, 15);
	                }
	            }

				private boolean checkIfIDExists(String enteredID) {
					// TODO Auto-generated method stub
					return false; 
				}
	        });
	        
					
			
	        RoundedButton confirmButton = new RoundedButton("변경하기");
	        confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	loginclass a = new loginclass();
	            	String schoolcode = firstThreeDigits + universityField.getText();
	            	UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);
	            	  if (universityInfo != null) {
	            		  
	            		DBConnection.ChangeUser(userIDD, "UID", schoolcode);
	            		dialogFrame.dispose();	            		
	            	
	            	  }
	            }
	        });
	        confirmButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
	        confirmButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
	        confirmButton.setBounds(135, 300, 65, 25);
	        dialogPanel.add(confirmButton);		
			
		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);

		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);		
	   }
	   
	   private void PWchangeDialog() {
			// 테두리 스타일 설정
			Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			Border borderBlue = BorderFactory.createLineBorder(new Color(155, 155, 155), 2);
			Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
			Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			
			
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(250, 280, 350, 400);
		    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // 다이얼로그 패널 생성
		    JPanel dialogPanel = new JPanel();
		    //dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		    dialogPanel.setBounds(5, 0, 100, 100);
			frame.getContentPane().add(dialogPanel);
			dialogPanel.setLayout(null);
			dialogPanel.setBackground(new Color(255, 255, 255)); // 배경색 설정		    
			dialogFrame.add(dialogPanel);


	        // 비밀번호 변경 라벨
	        JLabel ChangePWLabel = new JLabel("비밀번호 변경");
	        ChangePWLabel.setBounds(130, 11, 150, 30); // Adjust the position and size as needed
	        ChangePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font ChangePWFont = new Font(ChangePWLabel.getFont().getName(), Font.BOLD, 14);
	        ChangePWLabel.setFont(ChangePWFont);
	        dialogPanel.add(ChangePWLabel);  
			
			
						
			//취소 버튼
	        JButton BackButton = new JButton("취소"); // 버튼 생성 및 텍스트 설정
	        BackButton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
				public void actionPerformed(ActionEvent e) {
					dialogFrame.dispose();
				}
			});
	        BackButton.setBounds(282, 10, 60, 20); // 버튼 위치 및 크기 설정
	        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정
	        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
	        dialogPanel.add(BackButton); // 패널에 버튼 추가
			Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
			BackButton.setFont(memberbuttonFont);
	 		BackButton.setBorder(borderWhite); // 테두리 설정	
			BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			
			
	        // 비밀번호 라벨
	        JLabel usePWLabel = new JLabel("비밀번호");
	        usePWLabel.setBounds(75, 105, 150, 15); // Adjust the position and size as needed
	        usePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        usePWLabel.setFont(memberbuttonFont);
	        dialogPanel.add(usePWLabel);     
	        // 비밀번호 입력 필드
	        RoundPasswordField usePWField = new loginclass.RoundPasswordField();
	        usePWField.setBounds(70, 120, 200, 35);
	        usePWField.setBackground(new Color(200, 200, 200));
	        usePWField.setForeground(new Color(255, 255, 255));
	        dialogPanel.add(usePWField);
	        usePWField.setColumns(10);
	        usePWField.setBorder(borderBlack);
	        

	        // 비밀번호 확인 입력 필드
	        RoundPasswordField usePW2Field = new loginclass.RoundPasswordField();
	        usePW2Field.setBounds(70, 180, 200, 35);
	        usePW2Field.setBackground(new Color(200, 200, 200));
	        usePW2Field.setForeground(new Color(255, 255, 255));
	        dialogPanel.add(usePW2Field);
	        usePW2Field.setColumns(10);
	        usePW2Field.setBorder(borderBlack);


	        // 비밀번호 일치 여부를 표시하는 라벨
	        JLabel passwordMatchLabel = new JLabel("");
	        passwordMatchLabel.setFont(memberbuttonFont);
	        dialogPanel.add(passwordMatchLabel);

	        
	        // 비밀번호 확인 필드에 DocumentListener 추가
	        usePW2Field.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                checkPasswordMatch();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                checkPasswordMatch();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                checkPasswordMatch();
	            }

	            private void checkPasswordMatch() {
	    	        String password1 = usePWField.getText();
	                String password2 = usePW2Field.getText();

	                if (password1.equals(password2)) {
	                    if (password1.length() < 8) {
	                        passwordMatchLabel.setText("최소 8자리 입력");
	                        passwordMatchLabel.setForeground(new Color(255, 0, 0)); // 빨간색
	                        passwordMatchLabel.setBounds(115, 220, 250, 15);
	                    } else {
	                        passwordMatchLabel.setText("비밀번호 일치함");
	                        passwordMatchLabel.setForeground(new Color(0, 0, 255)); // 파란색
	                        passwordMatchLabel.setBounds(118, 220, 250, 15);
	                    }
	                } else {
	                    passwordMatchLabel.setText("비밀번호 일치하지 않음");
	                    passwordMatchLabel.setForeground(new Color(255, 0, 0)); // 빨간색
	                    passwordMatchLabel.setBounds(105, 220, 250, 15);
	                }
	            }
	        });
			
			
		
	        RoundedButton confirmButton = new RoundedButton("변경하기");
	        confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	loginclass a = new loginclass(); // abc 클래스의 생성자 호출 수정
	            		            	            	
	    	        String password1 = usePWField.getText();
	                String password2 = usePW2Field.getText();
	                
	            	if(password1.equals(password2) && password1.length() > 7 ){
	            		
	            		DBConnection.ChangeUser(userIDD, "UserPW", password1);
	            		
	            		
	            		dialogFrame.dispose();	            		
	            	}

	            }
	        });
	        confirmButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
	        confirmButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
	        confirmButton.setBounds(135, 300, 65, 25);
	        dialogPanel.add(confirmButton);
        

		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);

		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);
		}

	   private void NicknamechangeDialog() {
			// 테두리 스타일 설정
			Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			Border borderBlue = BorderFactory.createLineBorder(new Color(155, 155, 155), 2);
			Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
			Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			
			 String SearchNickname = DBConnection.FindUserInformation("users", "Nickname","UserID", userIDD);		
			
			
			
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(250, 280, 350, 400);
		    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // 다이얼로그 패널 생성
		    JPanel dialogPanel = new JPanel();
		    //dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		    dialogPanel.setBounds(5, 0, 100, 100);
			frame.getContentPane().add(dialogPanel);
			dialogPanel.setLayout(null);
			dialogPanel.setBackground(new Color(255, 255, 255)); // 배경색 설정		    
			dialogFrame.add(dialogPanel);


	        // 변경 라벨
	        JLabel ChangePWLabel = new JLabel("닉네임 설정");
	        ChangePWLabel.setBounds(132, 11, 150, 30); // Adjust the position and size as needed
	        ChangePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font ChangePWFont = new Font(ChangePWLabel.getFont().getName(), Font.BOLD, 14);
	        ChangePWLabel.setFont(ChangePWFont);
	        dialogPanel.add(ChangePWLabel);  
					
			
			//취소 버튼
	        JButton BackButton = new JButton("취소"); // 버튼 생성 및 텍스트 설정
	        BackButton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
				public void actionPerformed(ActionEvent e) {
					dialogFrame.dispose();
				}
			});
	        BackButton.setBounds(282, 10, 60, 20); // 버튼 위치 및 크기 설정
	        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정
	        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
	        dialogPanel.add(BackButton); // 패널에 버튼 추가
			Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
			BackButton.setFont(memberbuttonFont);
	 		BackButton.setBorder(borderWhite); // 테두리 설정	
			BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
	        
	        // 별명 라벨
	        JLabel nicknameLabel = new JLabel("별명");
	        nicknameLabel.setBounds(70, 110, 150, 15); // Adjust the position and size as needed
	        nicknameLabel.setForeground(new Color(100, 100, 100)); // Set label text color
	        Font labelFont2 = new Font(nicknameLabel.getFont().getName(), Font.BOLD, 9);
	        nicknameLabel.setFont(labelFont2);
	        dialogPanel.add(nicknameLabel); 
	        
	         // 중복 라벨
	        JLabel NickduplicationLabel = new JLabel("");
	        NickduplicationLabel.setBounds(105, 163, 150, 15); // Adjust the position and size as needed
	        NickduplicationLabel.setForeground(new Color(0, 0, 255)); // Set label text color
	        NickduplicationLabel.setFont(labelFont2);
	        dialogPanel.add(NickduplicationLabel);
	        
	        // 별명입력 입력 필드
	        RoundtextField NicknameField = new RoundtextField();
	        NicknameField.setBounds(65, 125, 200, 35);
	        NicknameField.setBackground(new Color(200, 200, 200));
	        NicknameField.setForeground(new Color(255, 255, 255));
	        dialogPanel.add(NicknameField);
	        NicknameField.setColumns(10);
	        NicknameField.setBorder(borderBlack);
	        NicknameField.setText("별명");
	        
	        // 별명중복 확인 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
			RoundedButton NicknameDuplicationButton = new RoundedButton("중복 확인");
			NicknameDuplicationButton.setBounds(265, 132, 50, 20);
			Font checkFont1 = new Font(NicknameDuplicationButton.getFont().getName(), Font.PLAIN, 10);
			NicknameDuplicationButton.setFont(checkFont1);
			
			NicknameDuplicationButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
			NicknameDuplicationButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
			dialogPanel.add(NicknameDuplicationButton); // 패널에 버튼 추가
	        NicknameDuplicationButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent en) {
	                String Nicknamesave = NicknameField.getText();
	                // 여기서 데이터베이스 조회하여 중복 여부 확인
	                boolean nickDuplicate = DBConnection.checkIfIDExists("Nickname", Nicknamesave);
	                
	                if (nickDuplicate) {
	                    NickduplicationLabel.setText("이미 사용 중인 별명입니다.");
	                    NickduplicationLabel.setForeground(new Color(255, 0, 0));
	                } else {
	                    NickduplicationLabel.setText("사용 가능한 별명입니다.");
	                    NickduplicationLabel.setForeground(new Color(0, 0, 255));
	                }
	            }

				private boolean checkIfIDExists(String Nickname) {
					// TODO Auto-generated method stub
					return false;
				}
	        });	        			
	        RoundedButton confirmButton = new RoundedButton("변경하기");
	        confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	loginclass a = new loginclass(); // abc 클래스의 생성자 호출 수정
	            	String Nickname = NicknameField.getText();
	            	
	            	boolean isDuplicateID = DBConnection.checkIfIDExists("Nickname", Nickname);	                       	
	            	  if (!isDuplicateID) {	            		  
	            		DBConnection.ChangeUser(userIDD, "Nickname", Nickname);
	            		dialogFrame.dispose();	            			            	
	            	  }
	            }
	        });
	        confirmButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
	        confirmButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
	        confirmButton.setBounds(135, 300, 65, 25);
	        dialogPanel.add(confirmButton);					
		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);
		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);		
	   }
	   
	  
	   public void showFrame() {
	        frame.setVisible(true);
	   }
	   
	
}