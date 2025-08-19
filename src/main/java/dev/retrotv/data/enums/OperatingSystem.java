package dev.retrotv.data.enums;

/**
 * 운영체제를 표현하기 위한 열거형 클래스입니다.
 *
 * @author  yjj8353
 * @since   1.0.0
 */
public enum OperatingSystem {
    WINDOWS("Windows"),
    LINUX("Linux"),
    UNIX("Unix"),
    DARWIN("Darwin"),
    SOLARIS("Solaris"),
    OTHERS("Others");

    private final String label;

    OperatingSystem(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
