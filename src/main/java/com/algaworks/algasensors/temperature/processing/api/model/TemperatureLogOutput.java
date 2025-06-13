package com.algaworks.algasensors.temperature.processing.api.model;

import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureLogOutput {
    private UUID id;
    private TSID sedorId;
    private OffsetDateTime registeredAt;
    private Double value;
}
