package tripvibe.tripvibebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//==login test==//
@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDTO<D> {
    private boolean result;
    private String message;
    private D data;
    private String token;

    public  static <D> ResponseDTO<D> setSuccess(String message) {
        return ResponseDTO.set(true, message, null, null);
    }

    public static <D> ResponseDTO<D> setFailed(String message)
    {
        return ResponseDTO.set(false, message, null, null);
    }

    public static <D> ResponseDTO<D> setSuccessData(String message, D data) {
        return ResponseDTO.set(true, message, data, null);
    }
//
//    public static <D> ResponseDTO<D> setFailedData(String message, D data) {
//        return ResponseDTO.set(false, message, data);
//    }

    public boolean getResult() {
        return result;
    }
}