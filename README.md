# speechapi-response-parser
Parses response of Google Speech API and consolidates texts in a simple text file.

Usage of this tool is very nominal. It is here to quickly walk you through Google Speech API.

## Pre-requisite

1. Google Speech API:
 
    Following few sections briefs on how to use Google Speech API - trigger a request and get response.
    For details follow Google's documentation.

2. Java 1.8

3. Maven

### Set up Google Cloud Account & follow steps to use Speech API
1. https://cloud.google.com/

   Create an account using your Gmail and setup payment.

   Google cloud dashboard - https://console.cloud.google.com/home/dashboard

2. Basics of Speech API:

    https://cloud.google.com/speech/
    https://cloud.google.com/speech/docs/

3. Follow step by step guild from How-to https://cloud.google.com/speech/docs/how-to.

    In the how-to guide, the first is authentication. Go through "Using a service account" section.
    
### Convert the audio file in supported format

Todo
 
### Google cloud storage

Ref: https://console.cloud.google.com/storage/browser

1. Create a bucket into Google cloud storage

2. Upload the file audio file into the bucket    
     
### Request and response

I used Postman (https://www.getpostman.com/) to make following API calls.

#### access_token

Get access_token using 'gcloud' tool. 

Ref: https://cloud.google.com/sdk/

#### Authorization
    Bearer Token: <access_token>​

#### Headers
    Content-Type: application/json

#### Request for speech api to create transcript for the audio file previously uploaded into the cloud storage

Make the following request to trigger a long running Speech API on your audio file. 

Request parameters:

    URL: POST https://speech.googleapis.com/v1/speech:longrunningrecognize
    
    Body:
    {
        "config": {
            "encoding": "FLAC",
            "sampleRateHertz": 16000,
            "languageCode": "en-US"
        },
        "audio": {
            "uri": "gs://<bucket_name>/<file>.flac"
        }
    }

Note:

1. The field values could be different as per your requirement 
2. Note down the value of "name" from the response to the above request.


#### Request to get the transcript

After the above request has been submitted it could be while before it completes.

To get the transcript or progress of conversion make the following GET API call:

Request:

    URL: GET https://speech.googleapis.com/v1/operations/<name>​
    
Note: 

1. Replace <name> with value received after the first API call.
2. Copy the response in a json file say transcript.json. To be used in the tool next.


## Usage of this tool
Once the conversion if complete, the above request will return the transcript in a json format. Use this tool to create 
a consolidated text file from the json response.

    $ cd /path/to/the/folder/containing/pom.xml
    $ mvn clean package
    $ java -jar  target/speechapi-response-parser-1.0.jar /path/to/transcript.json
