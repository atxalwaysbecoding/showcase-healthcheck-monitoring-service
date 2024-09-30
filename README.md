# showcase-healhcheck-mock-monitored-service
Showcasing mock services being monitored.  Pushing past my imposter syndrome and practicing what I know.

Brief service purpose: At its core, the Monitoring Service provides the ability to manage (CRUD) HTTP request entities, what are called HealthChecks, that are then regularly executed at a scheduled interval.  Executing an HTTP HealthCheck generates a Run Result.  A Run Result captures the HealthCheck ID, the run URL, the return status code, the response body, the error message if applicable, the start date and time, and the HTTP request duration in milliseconds.  Over time, if a HealthCheck's health is consistently unhealthy, per multiple Run Results, an Alert might be created to raise visibility.

![image](https://github.com/user-attachments/assets/39baceac-d1ed-48f1-8a6d-7555a24d0314)
