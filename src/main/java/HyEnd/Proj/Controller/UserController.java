// 파일 위치: ./main/java/HyEnd/Proj/Controller/UserController.java

package HyEnd.Proj.Controller;

import HyEnd.Proj.Entity.User;
import HyEnd.Proj.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 사용자 관련 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 처리 메소드
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입에 실패하였습니다: " + e.getMessage());
        }
    }

    // 로그인 처리 메서드
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
        try {
            userService.login(user.getUserId(), user.getPassword());
            // 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            return ResponseEntity.ok("로그인에 성공하였습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다: " + e.getMessage());
        }
    }

    // 로그아웃 처리 메소드
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        // 세션 확인
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한이 없습니다.");
        }

        // 세션에서 userId 추출
        String userId = (String) session.getAttribute("userId");

        try {
            // 회원탈퇴 처리
            userService.deleteUser(userId);
            session.invalidate(); // 회원탈퇴 후 세션 무효화
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 탈퇴에 실패하였습니다: " + e.getMessage());
        }
    }

    // 회원 정보 조회 메소드
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = (String) session.getAttribute("userId");
        User user = userService.getUserInfo(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 회원 정보 수정 메소드
    @PutMapping("/me")
    public ResponseEntity<String> updateUserInfo(@RequestBody User updatedUser, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = (String) session.getAttribute("userId");
        try {
            userService.updateUserInfo(userId, updatedUser);
            return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 정보 수정에 실패하였습니다: " + e.getMessage());
        }
    }
}
