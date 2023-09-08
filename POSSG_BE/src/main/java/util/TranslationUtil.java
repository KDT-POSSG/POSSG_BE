package util;

public class TranslationUtil {
	
	// 한국어의 비율이 50% 이상이면 한글 뉴스로 판단
	public static boolean isMostlyKorean(String text) {
	    String koreanText = text.replaceAll("[^가-힣]", "");
	    double koreanRatio = (double) koreanText.length() / text.length();
	    return koreanRatio >= 0.5; // % 선택
	}
}
