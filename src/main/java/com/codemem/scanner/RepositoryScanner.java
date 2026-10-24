package com.codemem.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component 
public class RepositoryScanner {
    
    private static final Set<String> IGNORED_DIRECTORIES = Set.of(".git", ".gradle", "build", "target", "node_modules");

    public List<Path> scanRepository(Path repositoryPath) throws IOException{
        
        if(!Files.isDirectory(repositoryPath)){
            throw new IllegalArgumentException("Invalid repository path: " + repositoryPath);
        }

        try(Stream<Path> paths = Files.walk(repositoryPath)){
            return paths.filter(path -> path.toString().endsWith(".java"))
                        .filter(path -> !isIgnored(repositoryPath.relativize(path)))
                        .sorted()
                        .toList();
        }
    }

    private boolean isIgnored(Path relativePath) {
        
        for(Path segment: relativePath){
            if(IGNORED_DIRECTORIES.contains(segment.toString()))
                return true;
        }

        return false;
    }
}
