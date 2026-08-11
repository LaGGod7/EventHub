package org.gd.eventhub.Controllers;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.PaymentService;
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
}
