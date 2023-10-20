package onboarding.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import onboarding.todo.dto.UserJoinRequest;
import onboarding.todo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper; //자바 오브젝트를 json으로 만들어줌

    @Test
    @DisplayName("회원가입 성공")
    void join_success() throws Exception {
        String email = "2g1210@naver.com";
        String password = "1q2w3e4r";

        mockMvc.perform(post("/auth/signup")
                //SpringSecurity를 돌리면서 Test를 사용하려면
                //.with(csrf())를 넣어줘야하고, 시큐리티 테스트를 임포트해야하지만 과감히 생략!
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(email, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - email 중복")
    void join_fail() throws Exception {
        String email = "2g1210@naver.com";
        String password = "1q2w3e4r";

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 email이 중복됩니다"));

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(email, password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}