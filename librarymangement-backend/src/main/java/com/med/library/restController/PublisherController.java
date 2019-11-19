package com.med.library.restController;

import com.med.library.dTo.PublisherDTO;
import com.med.library.service.PublisherService;
import com.med.library.utils.MappingConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.med.library.utils.ValidationError.getErrors;
import static com.med.library.utils.MappingConsts.*;


@RestController
@RequestMapping(MappingConsts.PUBLISHERS)
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> findAllEnabled() {
        List<PublisherDTO> publisherDTOS = publisherService.findAllEnabled();
        return new ResponseEntity<>(publisherDTOS, HttpStatus.OK);
    }

    @GetMapping(PUBLISHER)
    public ResponseEntity<PublisherDTO> findById(@PathVariable("publisherId") long pubId) {
        PublisherDTO pbDto = publisherService.findById(pubId);
        return new ResponseEntity<>(pbDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody PublisherDTO publisherDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return getErrors(bindingResult.getAllErrors());
        PublisherDTO savedPbDto = publisherService.save(publisherDTO);
        return new ResponseEntity<>(savedPbDto, HttpStatus.OK);
    }

    @PostMapping(PUBLISHER)
    public ResponseEntity<String> archive(
            @PathVariable("publisherId") long pubId,
            @RequestParam("enable") boolean enable ) {
        PublisherDTO publisherDTO = publisherService.enable(enable, pubId);
        String message = publisherDTO.isEnabled() ? "Publisher is enabled." : "Publisher is disabled";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(PUBLISHER)
    public ResponseEntity<?> update(
            @PathVariable("publisherId") long pubId,
            @Valid @RequestBody PublisherDTO publisherDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return getErrors(bindingResult.getAllErrors());
        PublisherDTO updatedPbDto = publisherService.update(publisherDTO, pubId);
        return new ResponseEntity<>(updatedPbDto, HttpStatus.OK);
    }

    @DeleteMapping(PUBLISHER)
    public ResponseEntity<String> delete (@PathVariable("publisherId") long pubId) {
        publisherService.delete(pubId);
        return new ResponseEntity<>("Publisher deleted.", HttpStatus.OK);
    }
}
