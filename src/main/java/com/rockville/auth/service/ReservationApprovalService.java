package com.rockville.auth.service;

import com.rockville.auth.model.domain.ReservationApproval;
import com.rockville.auth.model.dto.CustomerRequest;
import com.rockville.auth.model.dto.CustomerResponse;
import com.rockville.auth.model.dto.ReservationApprovalRequest;
import com.rockville.auth.model.dto.ReservationApprovalResponse;

import java.util.List;

public interface ReservationApprovalService {
    List<ReservationApprovalResponse> getReservationApprovalByIdAndType(String reservationId, String type);
    ReservationApprovalResponse addReservationApproval(ReservationApprovalRequest request);
}
