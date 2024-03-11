package DBpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/UniversityCommunity";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234"; // MySQL 계정 비밀번호

    public static void main(String[] args) {
        retrieveUserData();
    }

    public static void retrieveUserData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String UserID = resultSet.getString("UserID");
                        String UserPW = resultSet.getString("UserPW");
                        String UID = resultSet.getString("UID");
                        String Nickname = resultSet.getString("Nickname");
                        String LimitDate = resultSet.getString("LimitDate");
                        // 학교 및 학과 정보 조회
                        UniversityInfo universityInfo = getUniversityInfo(UID);

                        System.out.println(", 아이디: " + UserID + ", 비번: " + UserPW +
                                ", 학교코드: " + UID + ", 학교: " + universityInfo.getUName() +
                                ", 학과: " + universityInfo.getDepartment() +
                                ", 별명: " + Nickname + ", 제한날짜: " + LimitDate);
                        
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //유저정보 중복확인 메소드
    public static boolean checkIfIDExists(String where,String what) {
        boolean isDuplicate = false;
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT COUNT(*) FROM users WHERE " + where +" = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, what);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        isDuplicate = (count > 0);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }
        return isDuplicate;
    }
    
    
    
    public static class UniversityInfo {
        private String uName;
        private String department;

        public UniversityInfo(String uName, String department) {
            this.uName = uName;
            this.department = department;
        }

        public String getUName() {
            return uName;
        }

        public String getDepartment() {
            return department;
        }
    }

    //학교정보 및 학과
    public static UniversityInfo getUniversityInfo(String uid) {
        UniversityInfo universityInfo = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT UName, department FROM university WHERE UID = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, uid);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String uName = resultSet.getString("UName");
                        String department = resultSet.getString("department");
                        universityInfo = new UniversityInfo(uName, department);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }

        return universityInfo;
    }

    //디비 추가하느 코드
    public static boolean registerUser(String userID, String userPW, String uid, String nickname, String i) {
        boolean isRegistrationSuccessful = false;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (UserID, UserPW, UID, Nickname, LimitDate) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userID);
                preparedStatement.setString(2, userPW);
                preparedStatement.setString(3, uid);
                preparedStatement.setString(4, nickname);
                preparedStatement.setString(5, i);

                int rowsAffected = preparedStatement.executeUpdate();
                isRegistrationSuccessful = (rowsAffected > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }

        return isRegistrationSuccessful;
    }
   
    
    //게시물 추가하느 코드
    public static boolean registerPost(String BulletinID, String UserID, String UID,
    		String BoardID, String BulletinTitle, String Bcontent, String Bdate) {
        boolean isRegistrationSuccessful = false;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO bulletin (BulletinID, UserID, UID, BoardID, BulletinTitle, Bcontent, Bdate) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, BulletinID);
                preparedStatement.setString(2, UserID);
                preparedStatement.setString(3, UID);
                preparedStatement.setString(4, BoardID);
                preparedStatement.setString(5, BulletinTitle);
                preparedStatement.setString(6, Bcontent);
                preparedStatement.setString(7, Bdate);

                int rowsAffected = preparedStatement.executeUpdate();
                isRegistrationSuccessful = (rowsAffected > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }

        return isRegistrationSuccessful;
    }
   
    
    
 // 로그인 중복확인 및 인증 메소드
    public static boolean authenticateUser(String ID, String PW) {
        boolean isAuthenticated = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sqlAuth = "SELECT COUNT(*) FROM users WHERE UserID = ? AND UserPW = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAuth)) {
                preparedStatement.setString(1, ID);
                preparedStatement.setString(2, PW);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        isAuthenticated = (count > 0);
                    }
                }
            }
        } catch (SQLException log) {
            log.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }
        return isAuthenticated;
    }

    //원하는거 디비에서 검색하는 메소드
    public static String FindUserInformation(String table, String want, String what, String ID) {
        String result = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // 쿼리를 수정하여 바인딩 및 조건 오류를 수정함
            String sql = "SELECT " + want + " FROM " + table + " WHERE " + what + " = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, ID);  // UserID를 ?에 바인딩
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getString(want);
                    } else {
                        System.out.println("No results found for query: " + sql);
                    }
                }
            }
        } catch (SQLException user) {
            user.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }
        return result;
    }

    
    //사용자 정보 바꾸기
    public static boolean ChangeUser(String userID, String what, String change) {
        boolean changeSuccessful = false;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // 사용자 정보 업데이트 쿼리
            String sql = "UPDATE users SET " + what + " = ? WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, change);
                preparedStatement.setString(2, userID);

                // 쿼리 실행
                int affectedRows = preparedStatement.executeUpdate();

                // 변경이 성공적으로 이루어졌는지 확인
                if (affectedRows > 0) {
                    changeSuccessful = true;
                    System.out.println("사용자 정보 변경 성공!");
                } else {
                    System.out.println("사용자 정보 변경 실패: 해당하는 사용자를 찾을 수 없음");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }

        return changeSuccessful;
    }

    
    //게시물아이디 만드는 메소드
    public static String FindPostID(String table, String want) {
    	 String maxBulletinID = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // 쿼리를 수정하여 바인딩 및 조건 오류를 수정함
            String sql = "SELECT MAX(" + want + ") AS MaxBulletinID FROM " + table;
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                    	maxBulletinID = resultSet.getString("MaxBulletinID");
                    } else {
                        System.out.println("No results found for query: " + sql);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }
        return maxBulletinID;
    }

    //특정 컬럼의 개수를 반환하는 메소드
    public static int countRowsByColumnValue(String table, String column, String value) {
        int rowCount = 0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // 쿼리를 수정하여 바인딩 및 조건 오류를 수정함
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, value);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        rowCount = resultSet.getInt(1);
                    } else {
                        System.out.println("No results found for query: " + sql);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리를 여기에 추가하시기 바랍니다.
        }

        return rowCount;
    }
    
    
    
    
}


