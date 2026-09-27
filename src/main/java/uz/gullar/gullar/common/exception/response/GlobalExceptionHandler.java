package uz.gullar.gullar.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import uz.gullar.gullar.common.response.ApiErrorResponse;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> details = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() == null
                                ? "Noto'g'ri qiymat"
                                : error.getDefaultMessage(),
                        (firstMessage, secondMessage) -> firstMessage
                ));

        return new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Request ma'lumotlari noto'g'ri",
                request.getRequestURI(),
                details
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ApiErrorResponse handleResourceNotFound(
            NoResourceFoundException exception,
            HttpServletRequest request
    ) {
        return new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "RESOURCE_NOT_FOUND",
                "So'ralgan resurs topilmadi",
                request.getRequestURI(),
                Map.of()
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ApiErrorResponse handleBusinessException(
            BusinessException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = exception.getStatus();

        return new ApiErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                exception.getErrorCode(),
                exception.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleUnexpectedException(
            Exception exception,
            HttpServletRequest request
    ) {
        return new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "Serverda kutilmagan xatolik yuz berdi",
                request.getRequestURI(),
                Map.of()
        );
    }
}