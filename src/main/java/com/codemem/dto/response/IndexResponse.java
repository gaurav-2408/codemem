package com.codemem.dto.response;

import java.util.List;

public record IndexResponse(String repositoryPath,
                            int totalJavaFiles,
                            List<String>Files
) {
    
}
