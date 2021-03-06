package br.com.mercadolivre.mutantidentifier.controllers;

import br.com.mercadolivre.mutantidentifier.UrlMapping;
import br.com.mercadolivre.mutantidentifier.model.Stats;
import br.com.mercadolivre.mutantidentifier.reports.ReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "${ReportController.getStats.desc}", notes = "${ReportController.getStats.notes}")
    @GetMapping(UrlMapping.STATS)
    public ResponseEntity<Stats> getStats() {
        final Stats stats = reportService.getStats();
        return ResponseEntity.ok().body(stats);
    }

}
