package com.aloha.autoplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.autoplay.domain.CustomUser;
import com.aloha.autoplay.domain.Movies;
import com.aloha.autoplay.domain.Users;
import com.aloha.autoplay.service.MovieService;
import com.aloha.autoplay.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MainController {

    @Autowired private UserService userService;
    @Autowired private MovieService movieService;

    /**
     * 메인 화면
     * @return
     */
    @GetMapping({"", "/"})
    public String home(HttpSession session) {
        String type = (String) session.getAttribute("type");
        String preview = (String) session.getAttribute("preview");
        log.info("type : " + type);
        log.info("preview : " + preview);
        switch (type + preview) {
            case "저시각프리뷰":    return "redirect:/auto-a";
            case "고시각프리뷰":    return "redirect:/auto-b";
            case "저시각썸네일":    return "redirect:/auto-x-a";
            case "고시각썸네일":    return "redirect:/auto-x-b";
        }
        return "redirect:/auto-a";
    }


    /**
     * 프리뷰
     * @return
     */
    @GetMapping("/auto")
    public String auto() {
        return "index";
    }

    /**
     * 저시각 프리뷰
     * @return
     * @throws Exception 
     */
    @GetMapping("/auto-a")
    public String autoA(
        Model model,
        HttpSession session,
        HttpServletRequest request,
        @AuthenticationPrincipal CustomUser customUser,
        @RequestParam(value="id", required = false) String id
    ) throws Exception {
        // 세션 type, preivew 설정
        session.setAttribute("type", "저시각");
        session.setAttribute("preview", "프리뷰");
        // 영화 목록
        model.addAttribute("list", movieService.getListByType("저시각") );

        // 로그인 ⭕, id ⭕
        if( id != null && !id.isEmpty() ) {
            log.info("id : " + id);
            return "index-a";
        }

        // 로그인 여부 체크
        Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        // 로그인 ⭕, id ❌
        if (securityContext != null) {
            // 사용자 정보에서 id 가져오기
            id = customUser.getUser().getId();
            log.info("로그인된 사용자입니다. id : " + id);
            return "redirect:/auto-a?id=" + id;
        }

        // 로그인 ❌
        log.info("로그인되지 않은 사용자입니다.");
        boolean autoLoginResult = userService.autoLogin("저시각프리뷰", request);
        if (autoLoginResult) {
            log.info("자동 로그인 성공");
        } else {
            log.info("자동 로그인 실패");
        }
        return "redirect:/auto-a";
    }

    /**
     * 고시각 프리뷰
     * @return
     */
    @GetMapping("/auto-b")
    public String autoB(
        Model model,
        HttpSession session,
        HttpServletRequest request,
        @AuthenticationPrincipal CustomUser customUser,
        @RequestParam(value="id", required = false) String id
    ) throws Exception {
        session.setAttribute("type", "고시각");
        session.setAttribute("preview", "프리뷰");
        model.addAttribute("list", movieService.getListByType("고시각") );

        // 로그인 ⭕, id ⭕
        if( id != null && !id.isEmpty() ) {
            log.info("id : " + id);
            return "index-b";
        }

        // 로그인 여부 체크
        Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        // 로그인 ⭕, id ❌
        if (securityContext != null) {
            // 사용자 정보에서 id 가져오기
            id = customUser.getUser().getId();
            log.info("로그인된 사용자입니다. id : " + id);
            return "redirect:/auto-b?id=" + id;
        }

        // 로그인 ❌
        log.info("로그인되지 않은 사용자입니다.");
        boolean autoLoginResult = userService.autoLogin("고시각프리뷰", request);
        if (autoLoginResult) {
            log.info("자동 로그인 성공");
        } else {
            log.info("자동 로그인 실패");
        }
        return "redirect:/auto-b";
    }

    /**
     * 썸네일
     * @return
     */
    @GetMapping("/auto-x")
    public String autoX(HttpSession session) {
        return "index-x";
    }


    /**
     * 저시각 썸네일
     * @return
     */
    @GetMapping("/auto-x-a")
    public String autoXA(
        Model model,
        HttpSession session,
        HttpServletRequest request,
        @AuthenticationPrincipal CustomUser customUser,
        @RequestParam(value="id", required = false) String id
    ) throws Exception {
        session.setAttribute("type", "저시각");
        session.setAttribute("preview", "썸네일");
        model.addAttribute("list", movieService.getListByType("저시각") );

        // 로그인 ⭕, id ⭕
        if( id != null && !id.isEmpty() ) {
            log.info("id : " + id);
            return "index-x-a";
        }

        // 로그인 여부 체크
        Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        // 로그인 ⭕, id ❌
        if (securityContext != null) {
            // 사용자 정보에서 id 가져오기
            id = customUser.getUser().getId();
            log.info("로그인된 사용자입니다. id : " + id);
            return "redirect:/auto-x-a?id=" + id;
        }

        // 로그인 ❌
        log.info("로그인되지 않은 사용자입니다.");
        boolean autoLoginResult = userService.autoLogin("저시각썸네일", request);
        if (autoLoginResult) {
            log.info("자동 로그인 성공");
        } else {
            log.info("자동 로그인 실패");
        }
        return "redirect:/auto-x-a";
    }

    /**
     * 고시각 썸네일
     * @return
     */
    @GetMapping("/auto-x-b")
    public String autoXB(
        Model model,
        HttpSession session,
        HttpServletRequest request,
        @AuthenticationPrincipal CustomUser customUser,
        @RequestParam(value="id", required = false) String id
    ) throws Exception {
        session.setAttribute("type", "고시각");
        session.setAttribute("preview", "썸네일");
        model.addAttribute("list", movieService.getListByType("고시각") );

        // 로그인 ⭕, id ⭕
        if( id != null && !id.isEmpty() ) {
            log.info("id : " + id);
            return "index-x-b";
        }

        // 로그인 여부 체크
        Object securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        // 로그인 ⭕, id ❌
        if (securityContext != null) {
            // 사용자 정보에서 id 가져오기
            id = customUser.getUser().getId();
            log.info("로그인된 사용자입니다. id : " + id);
            return "redirect:/auto-x-b?id=" + id;
        }

        // 로그인 ❌
        log.info("로그인되지 않은 사용자입니다.");
        boolean autoLoginResult = userService.autoLogin("고시각썸네일", request);
        if (autoLoginResult) {
            log.info("자동 로그인 성공");
        } else {
            log.info("자동 로그인 실패");
        }
        return "redirect:/auto-x-b";
    }


     /**
     * 회원 가입 화면
     * 🔗 [GET] - /join
     * 📄 join.html
     * @return
     */
    @GetMapping("/join")
    public String join() {
        log.info(":::::::::: 회원 가입 화면 ::::::::::");
        return "join";
    }

    /**
     * 회원 가입 처리
     * 🔗 [POST] - /join
     * ➡   ⭕ /login
     *      ❌ /join?error
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPro(Users user, HttpServletRequest request) throws Exception {
        log.info(":::::::::: 회원 가입 처리 ::::::::::");
        log.info("user : " + user);

        // 암호화 전 비밀번호
        String plainPassword = user.getPassword();
        // 회원 가입 요청
        int result = userService.join(user);
        
        // 회원 가입 성공 시, 바로 로그인
        boolean loginResult = false;
        if( result > 0 ) {
            // 암호화 전 비밀번호 다시 세팅
            // 회원가입 시, 비밀번호 암호화하기 때문에, 

            // ⚡바로로그인 보류
            // user.setPassword(plainPassword);
            // loginResult = userService.login(user, request);
        }
        if (loginResult) {
            return "redirect:/";        // 메인화면으로 이동
        }
        if( result > 0 ) {
            return "redirect:/login";
        }
        
        return "redirect/join?error";
        
    }


    /**
     * 아이디 중복 검사
     * @param username
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/check/{username}")
    public ResponseEntity<Boolean> userCheck(@PathVariable("username") String username) throws Exception {
        log.info("아이디 중복 확인 : " + username);
        Users user = userService.select(username);
        // 아이디 중복
        if( user != null ) {
            log.info("중복된 아이디 입니다 - " + username);
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        // 사용 가능한 아이디입니다.
        log.info("사용 가능한 아이디 입니다." + username);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login(@CookieValue(value="remember-id", required = false) Cookie cookie
                    //    ,@RequestParam(value="autoplay", required = false) Boolean autoplay
                       ,@RequestParam(value="redirect", required = false, defaultValue = "/") String redirect
                       ,Model model ) {
        // @CookieValue(value="쿠키이름", required = 필수여부)
        // - required=true (default)  : 쿠키를 필수로 가져와서 없으면 에러
        // - required=false           : 쿠키 필수 ❌ ➡ 쿠키가 없으면 null, 에러❌
        log.info(":::::::::: 로그인 페이지 ::::::::::");

        // if( autoplay != null && autoplay ) {
        //     model.addAttribute("autoplay", true);
        // } else {
        //     model.addAttribute("autoplay", false);
        // }

        model.addAttribute("redirect", redirect);

        String username = "";
        boolean rememberId = false;
        if( cookie != null ) {
            log.info("CookieName : " + cookie.getName());
            log.info("CookieValue : " + cookie.getValue());
            username = cookie.getValue();
            rememberId = true;
        }
        model.addAttribute("username", username);
        model.addAttribute("rememberId", rememberId);
        return "/login";
    }
    

    /**
     * 본편 영상 재생
     * @return
     * @throws Exception 
     */
    @GetMapping("/movies/{id}")
    public String play(
        @PathVariable("id") String id,
        Model model
    ) throws Exception {
        log.info(":::::::::: 본편 영상 재생 ::::::::::");
        log.info("영상 ID : " + id);
        // 영화
        Movies movie = movieService.getById(id);
        log.info("영화 : " + movie);
        model.addAttribute("movie", movie);
        // 다음 영화
        Movies nextMovie = movieService.getNextMovie(id);
        log.info("다음 영화 : " + nextMovie);
        model.addAttribute("nextMovie", nextMovie);
        // 관련 영화 목록
        model.addAttribute("relatedMovieList", movieService.getRelatedMovie(id));
        return "movies";
    }
    

    /**
     * 세션 등록 시간 체크
     * @return
     */
    @ResponseBody
    @GetMapping("/timer")
    public ResponseEntity<Boolean> checkSessionTimeout(HttpSession session) {
        log.info(":::::::::: 세션 등록 시간 체크 ::::::::::");
        Long sessionStartTime = session.getCreationTime();
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - sessionStartTime;
        log.info("경과 시간(ms) : " + elapsedTime);
        // 경과 시간 분 단위
        long elapsedTimeInMinutes = elapsedTime / (1000 * 60);
        log.info("경과 시간(분) : " + elapsedTimeInMinutes);
        // 10분 = 600,000ms
        boolean isTimeout = elapsedTime > (10 * 60 * 1000); // 10분 초과
        // 세션 만료 여부
        log.info("세션 만료 여부 : " + isTimeout);
        return new ResponseEntity<>(isTimeout, HttpStatus.OK);
    }

}
