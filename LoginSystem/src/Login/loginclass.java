package Login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.border.Border;

import DBpackage.DBConnection;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;



class UniversityCommunityApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        loginclass login = new loginclass();
        String userIDD = login.getUserIDD(); // loginclass에 userIDD를 가져오는 getter 메소드가 있다고 가정합니다.
        mypageclass myPage = new mypageclass(userIDD);
        myPage.showFrame();
    }
}


public class loginclass {

	
	String userIDD;
	
	private JFrame frame;
	/**
	 * 어플리케이션 실행 메소드
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginclass window = new loginclass();
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
	public loginclass() {
		initialize();
	}
	
	
	   //버튼 디자인
   public class RoundedButton extends JButton {
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
	
	// 텍스트 필드 디자인
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
   
   
   public static class RoundPasswordField extends JPasswordField {
	    public RoundPasswordField() { super(); decorate(); }
	    public RoundPasswordField(int columns) { super(columns); decorate(); }
	    protected void decorate() { setOpaque(false);
	    setHorizontalAlignment(JPasswordField.LEFT);
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        int width = getWidth();
	        int height = getHeight();
	        Graphics2D graphics = (Graphics2D) g;
	        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        graphics.setColor(getBackground());
	        graphics.fillRoundRect(0, 0, width, height, 10, 10);
	        if (!isFocusOwner() && getPassword().length == 0) {
	            graphics.setColor(new Color(255, 255, 255));
	            Font font = getFont().deriveFont(Font.PLAIN, 12f);
	            graphics.setFont(font);
	            FontMetrics fontMetrics = graphics.getFontMetrics();
	            Rectangle stringBounds = fontMetrics.getStringBounds("비밀번호", graphics).getBounds();
	            int textX = 8;
	            int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
	            graphics.drawString("비밀번호", textX, textY);
	        } else {
	            // Mask the actual password
	            super.paintComponent(g);
	        }

	        graphics.dispose();
	    }
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 625, 755);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(borderRed); // 테두리 설정
		
		
		
		
		//그림
        ImageIcon originalImageIcon = new ImageIcon("src/img/22.png");
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBounds(210, 205, 100, 100);
        panel_1.add(imageLabel);

        
        //에브리소통 그림2
        ImageIcon everyImageIcon = new ImageIcon("src/img/every.png");
        Image everyImage = everyImageIcon.getImage();
        Image every1Image = everyImage.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
        ImageIcon every1ImageIcon = new ImageIcon(every1Image);
        JLabel image1Label = new JLabel(every1ImageIcon);
        image1Label.setBounds(290, 205, 120, 100);
        panel_1.add(image1Label);
        
        
		
		
		
		
		//아이디 입력 필드
		RoundtextField IDfield = new RoundtextField(); // 텍스트 필드 생성
		IDfield.setBounds(225, 300, 200, 35); // 텍스트 필드 위치 및 크기 설정
		IDfield.setBackground(new Color(200, 200, 200)); // 배경색 설정
		IDfield.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
		panel_1.add(IDfield); // 패널에 텍스트 필드 추가
		IDfield.setColumns(10); // 텍스트 필드 컬럼 수 설정
		IDfield.setBorder(borderBlack); // 테두리 설정
		IDfield.setText("아이디"); // 기본 텍스트 설정

		
	
		//비번 입력 필드
		RoundPasswordField PWfield = new RoundPasswordField(); // 텍스트 필드 생성
		PWfield.setBounds(225, 340, 200, 35); // 텍스트 필드 위치 및 크기 설정
		PWfield.setBackground(new Color(200, 200, 200)); // 배경색 설정
		PWfield.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
		panel_1.add(PWfield); // 패널에 텍스트 필드 추가
		PWfield.setColumns(10); // 텍스트 필드 컬럼 수 설정
		PWfield.setBorder(borderBlack); // 테두리 설정
		
		
		
		//계정 찾기 버튼
	       JButton findbutton = new JButton("아이디/비밀번호 찾기"); // 버튼 생성 및 텍스트 설정
	       findbutton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
	    	   public void actionPerformed(ActionEvent e) {
	    		   findlogclass f = new findlogclass(); // abc 클래스의 생성자 호출 수정
			        f.showFrame();
			        frame.dispose();	 
			}
		});
	    findbutton.setBounds(300, 420, 130, 30); // 버튼 위치 및 크기 설정
	    findbutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
     findbutton.setForeground(new Color(200, 200, 200)); // 텍스트 색상(흰색)
		panel_1.add(findbutton); // 패널에 버튼 추가
		Font findbuttonFont = new Font(findbutton.getFont().getName(), Font.BOLD, 12);
		findbutton.setFont(findbuttonFont);  
		findbutton.setBorder(borderWhite); // 테두리 설정
		
		
        // 로그인 실패 라벨
        JLabel loginduplicationLabel = new JLabel("");
        loginduplicationLabel.setBounds(245, 280, 200, 15); // Adjust the position and size as needed
        loginduplicationLabel.setForeground(new Color(0, 0, 255)); // Set label text color
        Font loginFont = new Font(loginduplicationLabel.getFont().getName(), Font.BOLD, 10);
        loginduplicationLabel.setFont(loginFont);
        panel_1.add(loginduplicationLabel);
		
		//로그인 버튼
		RoundedButton loginButton = new RoundedButton("로그인"); // 버튼 생성 및 텍스트 설정
		loginButton.setBounds(225, 380, 200, 35); // 버튼 위치 및 크기 설정
		loginButton.setBackground(new Color(255, 0, 0)); // 배경색 설정
		loginButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
		// 테두리 설정
		Border roundedBorder = BorderFactory.createLineBorder(new Color(255, 0, 0), 2, true);
		loginButton.setBorder(roundedBorder);
		panel_1.add(loginButton); // 패널에 버튼 추가
		Font buttonFont = new Font(loginButton.getFont().getName(), Font.PLAIN, 18);
		loginButton.setFont(buttonFont);
		loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userIDD  = IDfield.getText();
                String userPW  =  PWfield.getText();
                // 여기서 데이터베이스 조회하여 중복 여부 확인
                
                boolean loginDuplicate = DBConnection.authenticateUser(userIDD , userPW);
                
                
                if (loginDuplicate) {
                	boardclass bo = new boardclass(userIDD);
    				bo.showFrame();
    				frame.dispose();
                	
                	
                } else {
                    loginduplicationLabel.setText("아이디 또는 비밀번호가 틀렸습니다.");
                    loginduplicationLabel.setForeground(new Color(255, 0, 0));
                }
            }

			private boolean checkIfIDExists(String enteredID) {
				// TODO Auto-generated method stub
				return false;
			}
        });
		
		
		
		
		
		
		
		
		
		//회원가입 버튼
	       JButton joinbutton = new JButton("회원가입"); // 버튼 생성 및 텍스트 설정
	       joinbutton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
	    	   public void actionPerformed(ActionEvent e) {
	    		   joinclass j = new joinclass(); // abc 클래스의 생성자 호출 수정
			        j.showFrame();
			        frame.dispose();	
	    		   
	    		   
	    		   
			}
		});
	    joinbutton.setBounds(287, 450, 70, 30); // 버튼 위치 및 크기 설정
	    joinbutton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        joinbutton.setForeground(new Color(255, 0, 0)); // 텍스트 색상(흰색)
		panel_1.add(joinbutton); // 패널에 버튼 추가
		Font joinbuttonFont = new Font(joinbutton.getFont().getName(), Font.BOLD, 12);
		joinbutton.setFont(joinbuttonFont);  
		joinbutton.setBorder(borderWhite); // 테두리 설정
		

		}
	public void showFrame() {
        frame.setVisible(true);
    }
	
    public String getUserIDD() {
        return userIDD;
    }
}
