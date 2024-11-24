package knu.project.crm.influx.controller;

import knu.project.crm.influx.dto.InfluxResponse;
import knu.project.crm.influx.service.InfluxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer/influx")
public class InfluxController {
    private final InfluxService influxService;

    public InfluxController(InfluxService influxService){
        this.influxService = influxService;
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<List<InfluxResponse>> getInflux(@PathVariable Long shopId){
        return ResponseEntity.ok(influxService.getInflux(shopId));
    }
}
