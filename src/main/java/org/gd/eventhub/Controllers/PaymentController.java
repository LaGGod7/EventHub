package org.gd.eventhub.Controllers;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.PaymentService;
import org.gd.eventhub.dto.Requests.PaymentFailureRequest;
import org.gd.eventhub.dto.Requests.PaymentVerifyRequest;
import org.gd.eventhub.dto.Response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/orders/{bookingId}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable Integer bookingId)throws RazorpayException {
        PaymentResponse paymentResponse = paymentService.createPayment(bookingId);
        return ResponseEntity.ok(paymentResponse);
    }
    @PostMapping("/verify")
    public ResponseEntity<PaymentResponse> verifyPayment(
            @Valid @RequestBody PaymentVerifyRequest request)
            throws RazorpayException {

        PaymentResponse response =
                paymentService.verifyPayment(request);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/failed")
    public ResponseEntity<Void> paymentFailed(
            @Valid @RequestBody PaymentFailureRequest request) throws RazorpayException {

        paymentService.markPaymentFailed(request);

        return ResponseEntity.ok().build();
    }
}
