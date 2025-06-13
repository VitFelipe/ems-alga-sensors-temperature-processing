package com.algaworks.algasensors.temperature.processing.api.controller;

import com.algaworks.algasensors.temperature.processing.api.model.TemperatureLogOutput;
import com.algaworks.algasensors.temperature.processing.commom.TSIDGeneratedUtil;
import io.hypersistence.tsid.TSID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/sensors/{sensorId}/temperature/data")
@Log4j2
public class TemperatureProcessingLogController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> data(@PathVariable TSID sensorId, @RequestBody String data) {
        try {
            if(data == null || data.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o body da requisição não pode ser nula ou vazia");
            }
            Double temperature = Double.parseDouble(data);

          TemperatureLogOutput temperatureLogOutput =  TemperatureLogOutput.builder()
                    .id(UUID.randomUUID())
                    .sedorId(sensorId)
                  .registeredAt(OffsetDateTime.now())
                    .value(temperature)
                    .build();

            log.info(temperatureLogOutput);

            return ResponseEntity.ok().
                    build();
        } catch (NumberFormatException nfe) {
            return ResponseEntity.badRequest().
                    build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().
                    build();
        }
    }

}
