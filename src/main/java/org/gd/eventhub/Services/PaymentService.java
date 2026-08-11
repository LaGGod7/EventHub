package org.gd.eventhub.Services;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.gd.eventhub.Entity.Booking;
import org.gd.eventhub.Entity.Payment;
import org.gd.eventhub.Entity.User;
import org.gd.eventhub.Enums.BookingStatus;
import org.gd.eventhub.Enums.PaymentStatus;
import org.gd.eventhub.Exceptions.ForbiddenOperationException;
import org.gd.eventhub.Exceptions.InvalidStatusException;
import org.gd.eventhub.Exceptions.ResourceNotFoundException;
import org.gd.eventhub.Repository.BookingRepository;
import org.gd.eventhub.Repository.PaymentRepository;

import org.gd.eventhub.dto.Response.PaymentResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.razorpay.RazorpayException;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RazorpayClient razorpayClient;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CurrentUserService currentUserService;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;



    public PaymentResponse createPayment(Integer bookingId)  throws RazorpayException {
        Booking booking = bookingRepository.findById(bookingId) .orElseThrow(() ->
                new ResourceNotFoundException("Booking not found"));
        User user = currentUserService.getCurrentUser();
        if(!booking.getUser().getId().equals(user.getId())) {
            throw new ForbiddenOperationException(
                    "You are not allowed to pay for this booking"
            );
        }
        if(booking.getBookingStatus()!= BookingStatus.PAYMENT_PENDING) {
            throw new InvalidStatusException(
                    "Booking is not available for payment"
            );
        }
        Payment existingPayment = paymentRepository.findByBookingId(bookingId)
                .orElse(null);

        if (existingPayment != null) {
            return new PaymentResponse(
                    existingPayment.getRazorpayOrderId(),
                    existingPayment.getAmount(),
                    existingPayment.getCurrency(),
                    razorpayKeyId
            );
        }

        BigDecimal amount = BigDecimal.valueOf(booking.getTotalPrice());
        long amountInPaise = amount
                .multiply(BigDecimal.valueOf(100))
                .longValueExact();

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount",amountInPaise);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt","booking_"+bookingId);

        Order razorpayOrder = razorpayClient.orders.create(orderRequest);

        Payment payment = Payment.builder()
                .booking(booking)
                .razorpayOrderId(razorpayOrder.get("id"))
                .amount(amount)
                .currency("INR")
                .status(PaymentStatus.CREATED)
                .build();
        paymentRepository.save(payment);
        return new PaymentResponse(razorpayOrder.get("id"),amount,"INR",razorpayKeyId);


    }
}
