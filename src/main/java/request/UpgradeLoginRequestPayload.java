package request;

import lombok.Data;

@Data
public class UpgradeLoginRequestPayload {
    private String username;
    private String password;

}
