package com.codemem.service.indexing;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codemem.dto.response.IndexResponse;
import com.codemem.scanner.RepositoryScanner;

@Service 
public class IndexingService {
    
    private final RepositoryScanner repositoryScanner;

    public IndexingService(RepositoryScanner repositoryScanner){
        this.repositoryScanner = repositoryScanner;
    }

    public IndexResponse indexRepository(String repositoryPath) throws IOException{
        Path path = Path.of(repositoryPath).toAbsolutePath().normalize();

        List<Path> javaFiles = repositoryScanner.scanRepository(path);
        
        List<String> relativePaths = javaFiles.stream().map(file -> path.relativize(file).toString()).toList();

        return new IndexResponse(path.toString(),
                                relativePaths.size(),
                                relativePaths);
        
    }
}
