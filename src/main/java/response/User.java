package response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class User {
    @SerializedName("firstName")
    private String firstName;

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("userUuid")
    private String userUuid;

    @SerializedName("loanApplications")
    private List<Object> loanApplications = null;

    @SerializedName("loansInReview")
    private List<Loan> loansInReview = null;



}
