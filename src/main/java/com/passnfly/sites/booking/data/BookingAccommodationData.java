package com.passnfly.sites.booking.data;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "with", buildMethodName = "create")
@Data
public class BookingAccommodationData {

    String destination;

    String checkin;

    String checkout;

    int numOfChilds;

}
