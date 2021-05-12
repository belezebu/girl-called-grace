# girl-called-grace
Application to get brewery information

## Setup

### Security

This application is secured using OAuth2 client credentials flow 

To get a bearer token with scope to read the information from the application:

```
curl --request POST \
  --url https://dev-15361307.okta.com/oauth2/default/v1/token \
  --header 'accept: application/json' \
  --header 'authorization: Basic MG9hcTc0MzBmOGd0MzI2Uks1ZDY6VTAyWUVucDZrM0VjQllwTTFtNUdiLVRBLW5DYko2aVlpYVZsdWRPNg' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data 'grant_type=client_credentials&scope=breweries:read'
```

The credentials are hardcoded in the project at the moment for testing purposes
Should be changed to secrets and injected only in runtime

### Testing

Start the application and go to the [Swagger Docs](http://localhost:8080/documentation/swagger-ui.html)

Test the endpoints providing the bearer token in **Authorize**