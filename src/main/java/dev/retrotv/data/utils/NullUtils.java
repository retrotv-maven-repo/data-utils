package dev.retrotv.data.utils;

public final class NullUtils {
    private NullUtils() {
        throw new UnsupportedOperationException("NullUtils는 인스턴스를 생성할 수 없습니다.");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
