package isJordanBraz.springbasics.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ApiException {
    private final Date timestamp;
    private final Integer status;
    private final HttpStatus error;
    private final String message;
    private final String path;
}
