package netpie.library.example.rest.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class ApiResponse
{
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("topic")
    private String strTopic;

    @SerializedName("payload")
    private String strPayload;

    public List<ApiResponse> api_rep;

    public String getStrPayload()
    {
        return strPayload;
    }

    public String getStrTopic()
    {
        return strTopic;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
