package com.coviam.cartMicroServiceTeam_9.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StatusCheckForOrderDTO {
    private boolean status;
    private List<QuantityCheckForOrderDTO> quantityCheckForOrderDTOList;
}
