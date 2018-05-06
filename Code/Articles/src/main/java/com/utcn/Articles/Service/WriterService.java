package com.utcn.Articles.Service;

import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Persistence.Repository.WriterRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class WriterService {

    @Inject
    WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer getWriterByUsername(String username){
        return writerRepository.findByUsername(username);
    }
}
