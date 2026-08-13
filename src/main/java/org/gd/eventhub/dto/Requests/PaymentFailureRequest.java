package org.gd.eventhub.dto.Requests;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFailureRequest {
    @NotBlank
    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String errorCode;

    private String errorDescription;
}
