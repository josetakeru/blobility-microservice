<h1>TIER Blobility microservice</h1>

This microservice gets all the objects of an AWS account's buckets within a given region and lists them on endpoint */list-objects* of your domain.

ENV VARS that must be set:

* AWS_ACCESS_KEY_ID: Your AWS access key ID
* AWS_SECRET_ACCESS_KEY: Your AWS secret access key
* REGION: The region where you want to look e.g. eu-west-1