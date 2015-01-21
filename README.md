#Sample Service Broker App
Sample project to create a service broker. Written primarily using Spring-Boot for simplicity.


## IMPORTANT
You need to have "admin" rigths to register services available in the marketplace.

## Listing all Service Brokers
Run the following command:

`cf service-brokers`

This should list all service brokers available to this organization (where the admin logged in)

## Registering the Service
To register this service broker run the following:

`cf create-service-broker SERVICE_BROKER USERNAME PASSWORD URL`

SERVICE BROKER: is the name of your choice.
USERNAME PASSWORD: Credentials are needed by your code to authenticate against these values. 
URL: the url of your hosted service broker.

## Making your services Public
All registered service brokers are __private__ by default. You can see these with the following command:

`cf service-access`

To change this, you need to modify its access control. This can be done with the following:

`cf enable-service-access YOUR_SERVICE`

This sample service broker app hardcodes the service name "your-sample-service"

## Notes
Be careful not to confuse the name of the CF app (sample-service) defined in the manifest and the service broker (your-sample-service).

## References:
* http://docs.cloudfoundry.org/services/managing-service-brokers.html
* http://docs.cloudfoundry.org/services/access-control.html
