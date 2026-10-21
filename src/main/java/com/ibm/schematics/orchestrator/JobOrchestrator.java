package com.ibm.schematics.orchestrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobOrchestrator {
    private static final Logger logger = LoggerFactory.getLogger(JobOrchestrator.class);

    public void validateJob(JobRequest request) {
        if (request.getTemplate() == null) {
            logger.error("Validation failed: template missing for job {}", request.getId());
            throw new IllegalArgumentException("template missing");
        }
    }

    public Job lookupJob(String jobId) {
        try {
            return repository.find(jobId);
        } catch (NotFoundException e) {
            logger.error("Job {} not found", jobId, e);
            throw e;
        }
    }

    public void dispatch(Job job) {
        for (int attempt = 1; attempt <= 5; attempt++) {
            try {
                agentClient.send(job);
                return;
            } catch (TransientException e) {
                logger.error("Dispatch attempt {} failed, retrying", attempt, e);
            }
        }
        logger.critical("Job dispatch exhausted retries for " + job.getId());
    }
}


    public void dispatchAll(List<Job> jobs) {
        for (Job job : jobs) {
            log.info("dispatching job: {}", job);
            dispatch(job);
            log.info("job dispatched: {}", job);
        }
    }
