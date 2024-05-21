package swe.backend.dadlock.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private StatusEnum status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> responseSuccess(StatusEnum status, T data, String message) {
        return new ApiResponse<>(status, data, message);
    }

    public static ApiResponse<?> responseSuccessWithNoContent(String message) {
        return new ApiResponse<>(StatusEnum.OK, null, message);
    }

    @Builder
    public ApiResponse(StatusEnum status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
