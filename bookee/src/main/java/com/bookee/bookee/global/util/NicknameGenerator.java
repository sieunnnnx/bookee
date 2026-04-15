package com.bookee.bookee.global.util;

import java.util.List;
import java.util.Random;

/**
 * 랜덤 닉네임 생성기
 * <p>
 * "형용사 + 동물" 조합으로 닉네임 생성 (예) "조용한 고양이", "귀여운 여우"
 */
public class NicknameGenerator {

    private static final List<String> ADJECTIVES = List.of(
            "책에 빠진", "조용히 읽는", "페이지를 넘기는", "밤에 읽는", "서재에 있는",
            "글을 쓰는", "이야기를 모으는", "문장을 좋아하는", "기록하는", "천천히 읽는"
    );

    private static final List<String> ANIMALS = List.of(
            "고양이", "여우", "토끼", "곰", "늑대",
            "사자", "판다", "햄스터", "부엉이", "다람쥐"
    );

    private static final Random RANDOM = new Random();

    /**
     * 랜덤 닉네임 생성
     *
     * @return 생성된 닉네임 (형용사 + 동물)
     */
    public static String generate() {
        String adjective = ADJECTIVES.get(RANDOM.nextInt(ADJECTIVES.size()));
        String animal = ANIMALS.get(RANDOM.nextInt(ANIMALS.size()));

        return adjective + " " + animal;
    }
}