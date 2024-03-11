package Login;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import DBpackage.DBConnection;
import DBpackage.DBConnection.UniversityInfo;
import Login.loginclass.RoundPasswordField;
import Login.loginclass.RoundedButton;
import Login.loginclass.RoundtextField;

public class joinclass {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					joinclass window = new joinclass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public joinclass() {
		initialize();
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
	   
	   public class RoundedBorder implements Border {
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
			
	/**
	 * Initialize the contents of the frame.
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

		//둥근 페널
		JPanel roundedPanel = new JPanel();
		roundedPanel.setBackground(new Color(255, 255, 255));
		roundedPanel.setBounds(70, 100, 480, 520);
		panel.add(roundedPanel);
		roundedPanel.setLayout(null);
		roundedPanel.setBorder(border); // 테두리 설정
        RoundedBorder roundedBorder = new RoundedBorder(20);
        roundedPanel.setBorder(roundedBorder);

      

        // 회원가입 라벨
        JLabel joinLabel = new JLabel("회원가입");
        joinLabel.setBounds(275, 40, 200, 20); // Adjust the position and size as needed
        joinLabel.setForeground(new Color(0, 0, 0)); // Set label text color
        // Set a larger font for the label
        Font labelFont = new Font(joinLabel.getFont().getName(), Font.BOLD, 20);
        joinLabel.setFont(labelFont);
        panel.add(joinLabel);

        
        // 학교아이디 라벨
        JLabel universityLabel = new JLabel("학교 학과 코드");
        universityLabel.setBounds(145, 85, 150, 15); // Adjust the position and size as needed
        universityLabel.setForeground(new Color(100, 100, 100)); // Set label text color
        Font labelFont2 = new Font(universityLabel.getFont().getName(), Font.BOLD, 9);
        universityLabel.setFont(labelFont2);
        roundedPanel.add(universityLabel);     
        // 학교 아이디 입력 필드
        RoundtextField universityField = new RoundtextField();
        universityField.setBounds(140, 100, 200, 35);
        universityField.setBackground(new Color(200, 200, 200));
        universityField.setForeground(new Color(255, 255, 255));
        roundedPanel.add(universityField);
        universityField.setColumns(10);
        universityField.setBorder(borderBlack);
        universityField.setText("코드 입력");
        
        JLabel univerLabel = new JLabel("");
       
        univerLabel.setForeground(new Color(0, 0, 255)); // Set label text color
        univerLabel.setFont(labelFont2);
        roundedPanel.add(univerLabel);
        
        
        // 학교 검색 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
		RoundedButton searchButton = new RoundedButton("검색");
        searchButton.setBounds(339, 107, 50, 20);
        searchButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        searchButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
		Font searchFont = new Font(searchButton.getFont().getName(), Font.PLAIN, 10);
		searchButton.setFont(searchFont);
        roundedPanel.add(searchButton); // 패널에 버튼 추가
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String schoolcode = universityField.getText();
                // 여기서 데이터베이스 조회하여 중복 여부 확인
                UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);
                
                if (universityInfo != null) {
                    univerLabel.setText(universityInfo.getUName() +"-"+ universityInfo.getDepartment());
                    univerLabel.setForeground(new Color(0, 0, 255));
                    univerLabel.setBounds(182, 138, 150, 15);
                } else {
                    univerLabel.setText("학교 정보를 찾을 수 없습니다.");
                    univerLabel.setForeground(new Color(255, 0, 0));
                    univerLabel.setBounds(182, 138, 150, 15);
                }
            }

			private boolean checkIfIDExists(String enteredID) {
				// TODO Auto-generated method stub
				return false; 
			}
        });
        
        
        // 아이디 라벨
        JLabel useIDLabel = new JLabel("아이디");
        useIDLabel.setBounds(145, 160, 150, 15); // Adjust the position and size as needed
        useIDLabel.setForeground(new Color(100, 100, 100)); // Set label text color
        useIDLabel.setFont(labelFont2);
        roundedPanel.add(useIDLabel);     
        // 중복 라벨
        JLabel duplicationLabel = new JLabel("");
        duplicationLabel.setBounds(183, 215, 150, 15); // Adjust the position and size as needed
        duplicationLabel.setForeground(new Color(0, 0, 255)); // Set label text color
        duplicationLabel.setFont(labelFont2);
        roundedPanel.add(duplicationLabel);


        // 아이디 입력 필드
        RoundtextField useIDField = new RoundtextField();
        useIDField.setBounds(140, 175, 200, 35);
        useIDField.setBackground(new Color(200, 200, 200));
        useIDField.setForeground(new Color(255, 255, 255));
        roundedPanel.add(useIDField);
        useIDField.setColumns(10);
        useIDField.setBorder(borderBlack);
        useIDField.setText("아이디");


        // 중복 확인 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
		RoundedButton checkDuplicationButton = new RoundedButton("중복 확인");
        checkDuplicationButton.setBounds(340, 183, 50, 20);
		Font checkFont = new Font(checkDuplicationButton.getFont().getName(), Font.PLAIN, 10);
		checkDuplicationButton.setFont(checkFont);
		
        checkDuplicationButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        checkDuplicationButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
        roundedPanel.add(checkDuplicationButton); // 패널에 버튼 추가
        checkDuplicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredID = useIDField.getText();
                // 여기서 데이터베이스 조회하여 중복 여부 확인
                boolean isDuplicate = DBConnection.checkIfIDExists("UserID",enteredID);
                
                if (isDuplicate) {
                    duplicationLabel.setText("이미 사용 중인 아이디입니다.");
                    duplicationLabel.setForeground(new Color(255, 0, 0));
                } else {
                    duplicationLabel.setText("사용 가능한 아이디입니다.");
                    duplicationLabel.setForeground(new Color(0, 0, 255));
                }
            }

			private boolean checkIfIDExists(String enteredID) {
				// TODO Auto-generated method stub
				return false;
			}
        });
        
        
        
        
        // 비밀번호 라벨
        JLabel usePWLabel = new JLabel("비밀번호");
        usePWLabel.setBounds(145, 235, 150, 15); // Adjust the position and size as needed
        usePWLabel.setForeground(new Color(100, 100, 100)); // Set label text color
        usePWLabel.setFont(labelFont2);
        roundedPanel.add(usePWLabel);     
        // 비밀번호 입력 필드
        RoundPasswordField usePWField = new loginclass.RoundPasswordField();
        usePWField.setBounds(140, 250, 200, 35);
        usePWField.setBackground(new Color(200, 200, 200));
        usePWField.setForeground(new Color(255, 255, 255));
        roundedPanel.add(usePWField);
        usePWField.setColumns(10);
        usePWField.setBorder(borderBlack);
        

        // 비밀번호 확인 입력 필드
        RoundPasswordField usePW2Field = new loginclass.RoundPasswordField();
        usePW2Field.setBounds(140, 290, 200, 35);
        usePW2Field.setBackground(new Color(200, 200, 200));
        usePW2Field.setForeground(new Color(255, 255, 255));
        roundedPanel.add(usePW2Field);
        usePW2Field.setColumns(10);
        usePW2Field.setBorder(borderBlack);


        // 비밀번호 일치 여부를 표시하는 라벨
        JLabel passwordMatchLabel = new JLabel("");
        passwordMatchLabel.setFont(labelFont2);
        roundedPanel.add(passwordMatchLabel);

        
        
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
                        passwordMatchLabel.setBounds(200, 325, 250, 15);
                    } else {
                        passwordMatchLabel.setText("비밀번호 일치함");
                        passwordMatchLabel.setForeground(new Color(0, 0, 255)); // 파란색
                        passwordMatchLabel.setBounds(203, 325, 250, 15);
                    }
                } else {
                    passwordMatchLabel.setText("비밀번호 일치하지 않음");
                    passwordMatchLabel.setForeground(new Color(255, 0, 0)); // 빨간색
                    passwordMatchLabel.setBounds(190, 325, 250, 15);
                }
            }
        });

        
        // 별명 라벨
        JLabel nicknameLabel = new JLabel("별명");
        nicknameLabel.setBounds(145, 350, 150, 15); // Adjust the position and size as needed
        nicknameLabel.setForeground(new Color(100, 100, 100)); // Set label text color
        nicknameLabel.setFont(labelFont2);
        roundedPanel.add(nicknameLabel); 
        
     // 중복 라벨
        JLabel NickduplicationLabel = new JLabel("");
        NickduplicationLabel.setBounds(183, 405, 150, 15); // Adjust the position and size as needed
        NickduplicationLabel.setForeground(new Color(0, 0, 255)); // Set label text color
        NickduplicationLabel.setFont(labelFont2);
        roundedPanel.add(NickduplicationLabel);
        
        // 별명입력 입력 필드
        RoundtextField NicknameField = new RoundtextField();
        NicknameField.setBounds(140, 365, 200, 35);
        NicknameField.setBackground(new Color(200, 200, 200));
        NicknameField.setForeground(new Color(255, 255, 255));
        roundedPanel.add(NicknameField);
        NicknameField.setColumns(10);
        NicknameField.setBorder(borderBlack);
        NicknameField.setText("별명");
        
        // 별명중복 확인 버튼 (원래 중복 확인 기능은 여기에 넣어도 됨)
		RoundedButton NicknameDuplicationButton = new RoundedButton("중복 확인");
		NicknameDuplicationButton.setBounds(340, 373, 50, 20);
		Font checkFont1 = new Font(NicknameDuplicationButton.getFont().getName(), Font.PLAIN, 10);
		NicknameDuplicationButton.setFont(checkFont1);
		
		NicknameDuplicationButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
		NicknameDuplicationButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
        roundedPanel.add(NicknameDuplicationButton); // 패널에 버튼 추가
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
        
        
        
        
        
        //회원가입 버튼
		RoundedButton OKButton = new RoundedButton("회원가입");
        OKButton.setBounds(208, 440, 65, 20);
        OKButton.setBackground(new Color(255, 0, 0));// 배경색 설정 (빨간색)
        OKButton.setForeground(new Color(255, 255, 255)); // 텍스트 색상(흰색)
        roundedPanel.add(OKButton); // 패널에 버튼 추가
        
        JLabel OKLabel = new JLabel("");
        OKLabel.setFont(labelFont2);
        roundedPanel.add(OKLabel);

        
         // 회원가입 버튼에 ActionListener 추가
        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 입력된 값들 가져오기
                String Nickname = NicknameField.getText();
                String schoolcode = universityField.getText();
                String enteredID = useIDField.getText();
                String password1 = usePWField.getText();
                String password2 = usePW2Field.getText();

                
                // 학교 정보 조회
                UniversityInfo universityInfo = DBConnection.getUniversityInfo(schoolcode);

                // 에러 메시지 라벨 초기화
                OKLabel.setText("");


                // 학교 정보가 없으면 에러 메시지 출력
                if (universityInfo == null) {
                	OKLabel.setText("학교 학과 코드를 확인해 주세요.");
                    OKLabel.setForeground(new Color(255, 0, 0));
                    OKLabel.setBounds(172, 470, 250, 15);
                } else {
                    // 아이디 중복 확인
                    boolean isDuplicateID = DBConnection.checkIfIDExists("UserID" ,enteredID);


                    // 아이디 중복 에러 메시지 출력
                    if (isDuplicateID) {
                    	OKLabel.setText("아이디를 확인해 주세요.");
                    	OKLabel.setForeground(new Color(255, 0, 0));
                    	OKLabel.setBounds(189, 470, 250, 15);
                    }
                    
                    // 비밀번호 확인
                    boolean isPasswordValid = password1.length() >= 8 && password1.equals(password2);
                    // 비밀번호 관련 에러 메시지 출력
                    
                    if (!isPasswordValid && !isDuplicateID) {
                        OKLabel.setText("비밀번호를 확인해 주세요.");
                        OKLabel.setForeground(new Color(255, 0, 0));
                        OKLabel.setBounds(185, 470, 250, 15);
                    }

                    // 모든 조건이 충족되면 회원가입 성공
                    if (!isDuplicateID && isPasswordValid) {
                        // 여기에 회원가입 로직을 추가하면 됩니다.
                    	                  	
                        boolean isRegistrationSuccessful = DBConnection.registerUser
                        		(enteredID, password1, schoolcode, Nickname, "0");
                        // 회원가입 성공 다이얼로그 표시	
                        showSuccessDialog();
        		        frame.dispose();
                    }
                }
            }
        });
        
        
		
		//뒤로가기 버튼
        JButton BackButton = new JButton("뒤로가기"); // 버튼 생성 및 텍스트 설정
        BackButton.addActionListener(new ActionListener() { // 클릭 이벤트 리스너 추가
			public void actionPerformed(ActionEvent e) {
				loginclass log = new loginclass(); // abc 클래스의 생성자 호출 수정
				log.showFrame();
				frame.dispose();
			}
		});
        BackButton.setBounds(530, 30, 90, 30); // 버튼 위치 및 크기 설정
        BackButton.setBackground(new Color(255, 255, 255));// 배경색 설정 (빨간색)
        BackButton.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
		panel.add(BackButton); // 패널에 버튼 추가
		Font memberbuttonFont = new Font(BackButton.getFont().getName(), Font.BOLD, 12);
		BackButton.setFont(memberbuttonFont);
		BackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
			
	}
	
	
	   public void showFrame() {
	        frame.setVisible(true);
	    }
	   
	   private boolean checkIfIDExists(String enteredID) {
		    // 여기에 데이터베이스 조회 로직을 추가하여 아이디 중복 여부 확인
		    // 예시로 'user1234'라는 아이디가 이미 존재한다고 가정
		    return enteredID.equals("user1234");
		}
	   
	   
	   
	   private void showSuccessDialog() {
		    // 다이얼로그 프레임 생성
		    JFrame dialogFrame = new JFrame();
		    dialogFrame.setBounds(300, 300, 300, 150);
		    dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // 다이얼로그 패널 생성
		    JPanel dialogPanel = new JPanel();
		    dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		    dialogPanel.setBounds(5, 0, 260, 95);
			frame.getContentPane().add(dialogPanel);
			dialogPanel.setLayout(null);
			dialogPanel.setBackground(new Color(255, 255, 255)); // 배경색 설정
			dialogPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1)); // 테두리 설정
		    
		    
		    // 다이얼로그 라벨 생성
		    JLabel dialogLabel = new JLabel("회원가입 성공!");
		    dialogLabel.setBounds(102, 30, 200, 35);
		    dialogLabel.setForeground(new Color(0, 0, 0)); // 텍스트 색상(흰색)
		    dialogPanel.add(dialogLabel);

		    // 확인 버튼 생성
		    JButton confirmButton = new JButton("확인");		    
		    confirmButton.setBounds(110, 80, 65, 20);
		    confirmButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
					loginclass log1 = new loginclass();
					log1.showFrame();
					dialogFrame.dispose();
		        }
		    });
		    dialogPanel.add(confirmButton);

		    // 다이얼로그 프레임에 패널 추가
		    dialogFrame.getContentPane().add(dialogPanel);

		    // 다이얼로그 표시
		    dialogFrame.setVisible(true);
		}
	   
	   
	   
}
