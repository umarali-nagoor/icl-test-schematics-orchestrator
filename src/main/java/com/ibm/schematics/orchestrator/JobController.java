package com.ibm.schematics.orchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    public Job submit(JobRequest request) {
        logger.info("Entering submit");
        try {
            Job job = service.createJob(request);
            logger.info("Exiting submit");
            return job;
        } catch (Exception e) {
            logger.error("submit failed for job", e);
            throw e;
        }
    }
}
