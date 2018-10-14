package br.com.mercadolivre.mutantidentifier.controllers;

import br.com.mercadolivre.mutantidentifier.UrlMapping;
import br.com.mercadolivre.mutantidentifier.analysis.DnaAnalyzerService;
import br.com.mercadolivre.mutantidentifier.analysis.validators.DnaStructureValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DnaAnalyzerController {

    @Autowired
    private DnaAnalyzerService dnaService;

    @Autowired
    private DnaStructureValidator structureValidator;

    @ApiOperation(value = "${DnaAnalyzerController.postDna.desc}", notes = "${DnaAnalyzerController.postDna.notes}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK<br>Is a mutant"),
            @ApiResponse(code = 400, response = String.class, message = "Bad Request" +
                    "<br>Possible causes: " +
                    "<pre>  &bull; " + DnaStructureValidator.MSG_NULL_OR_EMPTY +
                    "<br>  &bull; " + DnaStructureValidator.MSG_NOT_SQUARE + "</pre>"
            ),
            @ApiResponse(code = 403, message = "Forbidden<br>Is not a mutant"),
    })
    @PostMapping(UrlMapping.POST_DNA)
    public ResponseEntity<String> postDna(@RequestBody DnaAnalysisRequest request) {
        final Optional<String> error = structureValidator.validate(request.getDna());

        if (error.isPresent()) {
            return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
        } else {
            return invokeService(request);
        }
    }

    private ResponseEntity<String> invokeService(DnaAnalysisRequest request) {
        return Optional.ofNullable(this.dnaService.isMutant(request.getDna()))
                .filter(Boolean::booleanValue)
                .map(is -> new ResponseEntity<>("", HttpStatus.OK))
                .orElse(new ResponseEntity<>("", HttpStatus.FORBIDDEN));
    }
}
