// 파일 위치: ./main/java/HyEnd/Proj/Service/UserService.java

package HyEnd.Proj.Service;

import HyEnd.Proj.Entity.User;
import HyEnd.Proj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 회원가입 로직
    public void registerUser(User user) throws Exception {
        // userId 중복 체크
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("암호화된 비밀번호: " + encodedPassword); // 디버깅 출력
        user.setPassword(encodedPassword);

        // 사용자 정보 저장
        userRepository.save(user);
    }

    // 로그인 로직
    public void login(String userId, String password) throws Exception {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            System.out.println("사용자가 존재하지 않습니다: " + userId);
            throw new Exception("존재하지 않는 아이디입니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다: " + userId);
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        System.out.println("로그인 성공: " + userId);
    }

    // 회원 탈퇴 로직
    public void deleteUser(String userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new Exception("존재하지 않는 사용자입니다.");
        }
        userRepository.delete(user);
    }

    // 회원 정보 조회 로직
    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId);
    }

    // 회원 정보 수정 로직
    public void updateUserInfo(String userId, User updatedUser) throws Exception {
        User existingUser = userRepository.findByUserId(userId);
        if (existingUser == null) {
            throw new Exception("존재하지 않는 사용자입니다.");
        }

        // 수정할 필드 업데이트
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setHeight(updatedUser.getHeight());
        existingUser.setWeight(updatedUser.getWeight());
        existingUser.setSmm(updatedUser.getSmm());
        existingUser.setBfp(updatedUser.getBfp());

        // 비밀번호가 변경되었다면 암호화 후 저장
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        userRepository.save(existingUser);
    }
}
