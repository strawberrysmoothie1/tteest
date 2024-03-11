package Login;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import DBpackage.DBConnection;
import DBpackage.DBConnection.UniversityInfo;
import Login.mypageclass.RoundtextField;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class freeboardclass {
	static String userIDD;
	static String boardID;
	JFrame frame;
	/**
	 * 어플리케이션 실행 메소드
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					freeboardclass window = new freeboardclass(userIDD, boardID);
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
	public freeboardclass(String userIDD, String boardID) {
        this.userIDD = userIDD;
        this.boardID = boardID;
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
				btnClickMe.setBounds(210, 32, 100, 45); // 버튼 위치 및 크기 설정
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
		        Schedule.setBounds(320, 32, 100, 45); // 버튼 위치 및 크기 설정
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
						mypageclass my = new mypageclass(userIDD);
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
		
		
				
				 String SearchBoardname = DBConnection.FindUserInformation("board", "BoardName","BoardID", boardID);
		        // 게시판 라벨
		        JLabel BoardnameLabel = new JLabel(SearchBoardname);
		        BoardnameLabel.setBounds(253, 95, 150, 30); // Adjust the position and size as needed
		        BoardnameLabel.setForeground(new Color(100, 100, 100)); // Set label text color
		        Font labelFont2 = new Font(BoardnameLabel.getFont().getName(), Font.BOLD, 22);
		        BoardnameLabel.setFont(labelFont2);
		        panel.add(BoardnameLabel);    
		        
		        //글쓰기 버튼
		        RoundedButton logoutbutton = new RoundedButton("글쓰기");
		        logoutbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	NewPostDialog();
		            }
		        });
		        logoutbutton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
		        logoutbutton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
		        logoutbutton.setBounds(470, 105, 70, 25);
		        panel.add(logoutbutton);
		        

				 //패널 생성
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setBounds(73, 140, 480, 500);
				panel.add(panel_1);
				panel_1.setLayout(null);
				panel_1.setBorder(border); // 테두리 설정
		        RoundedBorder roundedBorder = new RoundedBorder(20);
		        panel_1.setBorder(roundedBorder);
					
		        //게시판의 게시물 수
			    //int countculum = DBConnection.countRowsByColumnValue("bulletin", "BoardID", boardID);
			    	    	
		        String SearchBulletinID= DBConnection.FindPostID("bulletin", "BulletinID");
		        int currentBulletinID = Integer.parseInt(SearchBulletinID);
		        
		        int COUNT = 1;
			    
			    	for (int a = 1; a <= currentBulletinID; a++ ) {
			    		
			    		String ShowTitle=null;
			    		String BulletinID = String.valueOf(a);
				        String SearchBoardID = DBConnection.FindUserInformation("bulletin", "BoardID", "BulletinID", BulletinID);
				        String SearchTitle = DBConnection.FindUserInformation("bulletin", "BulletinTitle", "BulletinID", BulletinID);
				        if(SearchBoardID == boardID) {
				        	ShowTitle = SearchTitle;
					    	RoundedButton  postbutton = new RoundedButton (ShowTitle);
					        postbutton.addActionListener(new ActionListener() {
					            public void actionPerformed(ActionEvent e) {
					            	NewPostDialog();
					            }
					        });
					        postbutton.setBackground(new Color(200, 200, 200));// 배경색 설정 (빨간색)
					        postbutton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
					        postbutton.setBounds(3, 5 + (52*COUNT), 474, 50);
					        panel_1.add(postbutton);
				        	COUNT++;
				        }
			    	}

			    	
			        JLabel BoardnameLel = new JLabel(SearchBulletinID);
			        BoardnameLel.setBounds(253, 70, 150, 30); // Adjust the position and size as needed
			        BoardnameLel.setForeground(new Color(100, 100, 100)); // Set label text color
			        panel.add(BoardnameLel);  
		        
		        
		        
		
	}
	
	   
	   public class RoundtextField extends JTextField {
		      private int Y;

			public RoundtextField() { super(); decorate(); } 
		      public RoundtextField(String text) { super(text); decorate(); } 
		      public RoundtextField(int Y) {
		          super();
		          this.Y = Y;
		          decorate();
		      }
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
		          int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent() - Y;
		          graphics.setColor(getForeground());
		          graphics.setFont(getFont());
		          graphics.drawString(getText(), textX, textY);
		          graphics.dispose();
		          super.paintComponent(g);
		      }
	      }
	   

	   private void NewPostDialog() {
			// 테두리 스타일 설정
			Border borderRed = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			Border borderGray = BorderFactory.createLineBorder(new Color(240, 240, 240), 1);
			Border borderWhite = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
			Border borderBlack = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
			Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
			
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(200, 273, 450, 500);
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
	        JLabel ChangePWLabel = new JLabel("게시물 작성");
	        ChangePWLabel.setBounds(180, 11, 150, 30); // Adjust the position and size as needed
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
	        BackButton.setBounds(382, 10, 60, 20); // 버튼 위치 및 크기 설정
	        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정
	        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
	        dialogPanel.add(BackButton); // 패널에 버튼 추가
			Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
			BackButton.setFont(memberbuttonFont);
	 		BackButton.setBorder(borderWhite); // 테두리 설정	
			BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			

			//제목 필드
	        RoundtextField TitleField = new RoundtextField();
	        TitleField.setBounds(50, 75, 340, 35);
	        TitleField.setBackground(new Color(225, 225, 225));
	        TitleField.setForeground(new Color(0, 0, 0));
	        dialogPanel.add(TitleField);
	        TitleField.setColumns(10);
	        TitleField.setBorder(borderBlack);
	        TitleField.setText("제목 입력");
	        
	        
	        
			JPanel Contentpanel = new JPanel();
			Contentpanel.setBackground(new Color(240, 240, 240));
			Contentpanel.setBounds(50, 130, 340, 250);
			dialogPanel.add(Contentpanel);
			Contentpanel.setLayout(null);
			Contentpanel.setBorder(borderGray); // 테두리 설정
	        RoundedBorder roundedBorder = new RoundedBorder(20);
	        Contentpanel.setBorder(roundedBorder);
	        
	        
			//내용 필드
	        JTextArea ContentField = new JTextArea();
	        ContentField.setBounds(5, 5, 330, 240);
	        ContentField.setBackground(new Color(240, 240, 240));
	        ContentField.setForeground(new Color(0, 0, 0));
	        ContentField.setLineWrap(true);
	        ContentField.setWrapStyleWord(true);
	        
	        
	        JLabel TextLabel = new JLabel("");
			Font TextFont = new Font(BackButton.getFont().getName(), Font.BOLD, 11);
	        TextLabel.setFont(TextFont);
	        dialogPanel.add(TextLabel);
	        TextLabel.setBounds(340, 385, 100, 15);	        
	        // DocumentListener를 사용하여 내용이 변경될 때마다 이벤트 처리
	        ContentField.getDocument().addDocumentListener(new DocumentListener() {
	        	
	            private void checkCharacterLimit() {
	                if (ContentField.getText().length() > 400) {
	                    String truncatedText = ContentField.getText().substring(0, 400);
	                    ContentField.setText(truncatedText);
	                }
	            }    	
	        	        	
		        private void updateCharacterCount() {
		            int characterCount = ContentField.getText().length();
		            // 원하는 위치에 텍스트 개수를 보여주는 JLabel 또는 다른 방법으로 표시하면 됩니다.
		            if(characterCount > 400) {
		            	characterCount=400;
		            }
		            TextLabel.setText(characterCount + "/400" );
		        }
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                updateCharacterCount();
	                checkCharacterLimit();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                updateCharacterCount();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                updateCharacterCount();
	            }
	        });        
	        Contentpanel.add(ContentField);
	        ContentField.setColumns(10);
	        ContentField.setBorder(borderGray);
	        ContentField.setText("내용 입력");
			
	
	        String SearchSchoolcode = DBConnection.FindUserInformation("users", "UID", "UserID", userIDD);
	        String SearchBulletinID= DBConnection.FindPostID("bulletin", "BulletinID");
	        int currentBulletinID = Integer.parseInt(SearchBulletinID);
	        int newBulletinID = currentBulletinID + 1;
	        String newBulletinIDString = String.valueOf(newBulletinID);

	        
	        RoundedButton confirmButton = new RoundedButton("작성하기");
	        confirmButton.addActionListener(new ActionListener() {
	        	
	        	
	            public void actionPerformed(ActionEvent e) {
	            	loginclass a = new loginclass(); //
	            	String Title = TitleField.getText();
	            	String Content = ContentField.getText();
	                String currentDateTime = DateTimeUtil();
	            	DBConnection.registerPost( newBulletinIDString, userIDD, SearchSchoolcode,
	            			boardID, Title, Content, currentDateTime);
	            		dialogFrame.dispose();	            		

	            }
	        });
	        confirmButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
	        confirmButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
	        confirmButton.setBounds(185, 400, 65, 25);
	        dialogPanel.add(confirmButton);		
			
		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);

		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);		
	   }

	
	
	    public String DateTimeUtil () {
	        // 현재 날짜와 시간을 가져오기
	        LocalDateTime currentDateTime = LocalDateTime.now();

	        // DateTimeFormatter를 사용하여 원하는 형식으로 포맷팅
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

	        // 포맷된 현재 날짜와 시간 반환
	        return currentDateTime.format(formatter);
	    }

	public void showFrame() {
	        frame.setVisible(true);
	   }
	
}
