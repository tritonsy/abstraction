package com.fantasy.abstraction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "QR_INFO")
@Data
public class QrInfo {
    @Id
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    private BigDecimal cost;
    private String fiscalNum;
    private String fiscalDoc;
    private String fiscalAttr;
    private String userId;
}
