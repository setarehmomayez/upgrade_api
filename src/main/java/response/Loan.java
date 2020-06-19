package response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Loan {
    @SerializedName("id")
    private Integer id;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("status")
    private String status;

    @SerializedName("productType")
    private String productType;

    @SerializedName("sourceSystem")
    private String sourceSystem;

    @SerializedName("hasOpenBackendCounter")
    private Boolean hasOpenBackendCounter;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("postIssuanceStatus")
    private Object postIssuanceStatus;

}
