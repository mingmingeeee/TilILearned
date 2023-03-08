package com.example.security1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class SseController {

    //  ConcurrentHashMap은 읽기 작업에는 여러 쓰레드가 동시에 읽을 수 있지만, 쓰기 작업에는 특정 세그먼트 or 버킷에 대한 Lock을 사용
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/sse")
    public SseEmitter sse() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            // 연결
            sseEmitter.send(SseEmitter.event().name("connect"));
            // .name("connect"); => 해당 이벤트 이름 지정
        } catch (IOException e) {
            e.printStackTrace();
        }

        emitters.put("data", sseEmitter);

        // callback: SseEmitter를 관리하는 다른 스레드에서 실행
        // => 브라우저에서 재연결 요청 보냄 => 새로운 Emitter 객체 다시 생성함 => emitter 제거해주어야 함

        // 비동기 요청이 완료
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(sseEmitter);
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitter.complete();;
        });
        sseEmitter.onError((e) -> this.emitters.remove(sseEmitter));

        return sseEmitter;
    }

    @GetMapping("/data")
    public String data() {
        return "data";
    }

}
