package com.hk.board.util;

import java.util.HashMap;
import java.util.Map;

public class Paging {

    // prePageNum: 전페이지의 마지막 번호
    // nextPageNum: 다음페이지의 첫 번째 번호
    // startPage: 시작페이지 번호
    // endPage: 끝나는 페이지 번호
    // pageEndNum: 페이지 그룹의 마지막 번호
    // pcount: 총 페이지 수
    // pNum: 현재 보여줄 페이지 번호
    // pageRange: 한 번에 보여줄 페이지 범위

    public static Map<String, Integer> pagingValue(int pcount, String pNum, int pageRange) {
        Map<String, Integer> map = new HashMap<>();

        // ✅ 빈 문자열이나 null일 경우 기본값 1로 설정
        int pNumber = 1;
        if (pNum != null && !pNum.trim().equals("")) {
            try {
                pNumber = Integer.parseInt(pNum.trim());
            } catch (NumberFormatException e) {
                pNumber = 1; // 숫자 변환이 실패해도 기본값 1
            }
        }

        // 페이지 그룹의 마지막 번호 계산
        int pageEndNum = ((pNumber - 1) / pageRange + 1) == 1
                ? pageRange
                : ((pNumber - 1) / pageRange + 1) * pageRange;

        int prePageNum = pageEndNum - pageRange == 0 ? 1 : pageEndNum - pageRange;
        int nextPageNum = pageEndNum >= pcount ? pcount : pageEndNum + 1;

        int startPage = pageEndNum - (pageRange - 1);
        int endPage = pageEndNum > pcount ? pcount : pageEndNum;

        map.put("prePageNum", prePageNum);
        map.put("nextPageNum", nextPageNum);
        map.put("startPage", startPage);
        map.put("endPage", endPage);

        return map;
    }
}
