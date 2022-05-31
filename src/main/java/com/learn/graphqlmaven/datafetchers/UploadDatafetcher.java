package com.learn.graphqlmaven.datafetchers;

import java.io.IOException;

import com.acme.DgsConstants;
import com.learn.graphqlmaven.storage.FileSystemStorageService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class UploadDatafetcher {

    @Autowired
    private FileSystemStorageService storageService;
    
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UploadScriptWithMultipartPOST)
    public boolean uploadScript(DataFetchingEnvironment dfe) throws IOException {

        MultipartFile file = dfe.getArgument("input");
        try {
            storageService.store(file);

            String content = new String(file.getBytes());
            return ! content.isEmpty();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}
