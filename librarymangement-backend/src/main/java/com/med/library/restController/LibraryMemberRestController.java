package com.med.library.restController;

import com.med.library.dTo.LibraryMemberDto;
import com.med.library.service.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.med.library.utils.MappingConsts.MEMBERS;

@RestController
@RequestMapping(MEMBERS)
public class LibraryMemberRestController {

    private LibraryMemberService memberService;

    @Autowired
    public LibraryMemberRestController(LibraryMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<LibraryMemberDto>> findAllEnabled() {
        List<LibraryMemberDto> memberDtos = memberService.findByEnabled(true);
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }
}
