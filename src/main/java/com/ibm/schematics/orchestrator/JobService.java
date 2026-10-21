package com.ibm.schematics.orchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    public Job createJob(JobRequest request) {
        try {
            return repository.save(new Job(request));
        } catch (Exception e) {
            logger.error("createJob failed", e);
            throw e;
        }
    }
}


    public void validateAndLog(Job job) {
        if (job.getId() == null) {
            log.error("job validation failed: missing id for job {}", job);
            throw new ValidationException("missing id");
        }
        if (job.getType() == null) {
            log.error("job validation failed: missing type for job {}", job);
            throw new ValidationException("missing type");
        }
    }
