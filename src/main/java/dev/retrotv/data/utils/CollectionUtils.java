package dev.retrotv.data.utils;

import lombok.NonNull;

import java.util.*;

/**
 * 컬렉션 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 List에서 중복된 데이터를 제거하거나, 중복 여부를 확인하는 기능을 제공합니다.
 * </p>
 *
 * @author yjj8353
 * @since 1.0.0
 */
public final class CollectionUtils {
    private CollectionUtils() {
        throw new UnsupportedOperationException("CollectionUtils는 인스턴스를 생성할 수 없습니다.");
    }

    /**
     * List에서 중복되는 데이터를 제거하고 반환합니다.
     * orgOrder를 true로 설정할 경우 반환 List가 원본 List와 동일한 순서로 유지되며, 중복 데이터는 첫번째 값만 유지됩니다.
     *
     * @param values 중복을 제거할 list
     * @param orgOrder 원본 데이터와의 동일한 순서 유지여부
     * @return 중복이 제거된 list
     */
    public static <T> List<T> removeDuplicates(@NonNull List<T> values, boolean orgOrder) {
        List<T> newValues = new ArrayList<>(new LinkedHashSet<>(values));

        if (orgOrder) {
            List<T> orderValues = new ArrayList<>();
            for (T org : values) {
                Iterator<T> it = newValues.iterator();
                while (it.hasNext()) {
                    T newValue = it.next();
                    if (Objects.equals(org, newValue)) {
                        orderValues.add(newValue);
                        it.remove();
                        break;
                    }
                }
            }
            newValues = orderValues;
        }

        return new ArrayList<>(newValues);
    }

    /**
     * List에 중복된 데이터가 존재하는지 확인하고, 중복여부를 반환합니다.
     *
     * @param values 중복을 확인할 list
     * @return 중복여부
     */
    public static <T> boolean isDuplicated(@NonNull List<T> values) {
        Set<T> set = new HashSet<>(values);
        return values.size() != set.size();
    }
}