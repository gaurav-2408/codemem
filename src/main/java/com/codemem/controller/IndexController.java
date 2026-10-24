package com.codemem.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemem.dto.request.IndexRequest;
import com.codemem.dto.response.IndexResponse;
import com.codemem.service.indexing.IndexingService;

@RestController 
@RequestMapping ("/api/index")
public class IndexController {
    
    private final IndexingService indexingService;

    public IndexController(IndexingService indexingService){
        this.indexingService = indexingService;
    }

    @PostMapping 
    public ResponseEntity<IndexResponse>indexRepository(@RequestBody IndexRequest indexRequest) throws IOException{

        IndexResponse response = indexingService.indexRepository(indexRequest.repositoryPath());

        return ResponseEntity.ok(response);
    }
}
