package com.ssafy.startcamp.day02.typecasting;

public class BP_11 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        byte b1 = 10;
        byte b2 = 20;
        // @@TODOINLINE: 다음에서 발생하는 오류를 읽고 원인을 말한 후 수정하시오.
        int b3 = b1 + b2;

        int i1 = 10;
        long l1 = 20;
        // @@TODOINLINE: 다음에서 발생하는 오류를 읽고 원인을 말한 후 수정하시오.
        int i2 = (int)(i1 + l1);

        // @@TODOINLINE: 다음에서 발생하는 오류를 읽고 원인을 말한 후 수정하시오.
        float f1 = 10.0f;

        float f2 = (float)(f1 + 20.0);
    }

}
