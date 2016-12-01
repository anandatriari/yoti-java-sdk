Yoti Java SDK
=============

Welcome to the Yoti Java SDK. This repo contains the tools you need to quickly integrate your Java back-end with Yoti, so that your users can share their identity details with your application in a secure and trusted way.    

## An architectural view
To integrate your application with Yoti, your back-end must expose a GET endpoint that Yoti will use to forward tokens.
The endpoint can be configured in Yoti Dashboard when you create/update your application.

The image below shows how your application back-end and Yoti integrate in the context of a Login flow.
Yoti SDK carries out steps 6 through 9 for you, including profile decryption and communication with backend services.

![alt text](login_flow.png "Login flow")


Yoti also allows you to enable user details verification from your mobile app by means of the Android (TBA) and iOS (TBA) SDKs. In that scenario, your Yoti-enabled mobile app is playing both the role of the browser and the Yoti app. By the way, your back-end doesn't need to handle these cases in a significantly different way. You might just decide to handle the `User-Agent` header in order to provide different responses for web and mobile clients.
   

## Enabling the SDK
To import the Yoti SDK inside your project, you can use your favourite dependency management system.
If you are using Maven, you need to add the following dependency:

```xml
<dependency>
	<groupId>com.yoti</groupId>
	<artifactId>java-sdk-impl</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```
If you are using Gradle, here is the dependency to add:

`compile group: 'com.yoti', name: 'java-sdk-impl', version: '1.0-SNAPSHOT'`



## Client initialisation
The YotiClient is the SDK entry point. To initialise it you need include the following snippet inside your endpoint initialisation section:
```java
YotiClient client = YotiClientBuilder.newInstance()
    .forApplication(<YOUR_CLIENT_SDK_ID>)
    .withKeyPair(fromFile(<PATH/TO/YOUR/APPLICATION/KEY_PAIR.pem>)).build();
```
Where:
* `YOUR_CLIENT_SDK_ID` is the identifier generated by Yoti Dashboard when you create your app.
* `PATH/TO/YOUR/APPLICATION/KEY_PAIR.pem` is the path to the pem file your browser generates for you, when you create your app on Yoti Dashboard.


## Profile retrieval
When your application receives a token via the exposed endpoint (it will be assigned to a query string parameter named `token`), you can easily retrieve the user profile by adding the following to your endpoint handler:

```java
ActivityDetails activityDetails = client.getActivityDetails(encryptedToken);
```
Before you inspect the user profile, you might want to check whether the user validation was successful.
This is done as follows:

```java
ActivityDetails activityDetails;
HumanProfile profile;
try {
	activityDetails = client.getActivityDetails(token);
    if(activityDetails.getOutcome().isSuccessful()) {
    	profile = activityDetails.getUserProfile();
    } else {
		// handle unhappy path
	}            
} catch (ProfileException e) {
    LOG.info("Could not get profile", e);
    return "error";
}
``` 

## Handling users
When you retrieve the user profile, you receive a userId generated by Yoti exclusively for your application.
This means that if the same individual logs into another app, Yoti will assign her/him a different id.
You can use such id to verify whether the retrieved profile identifies a new or an existing user.
Here is an example of how this works:

```java
ActivityDetails activityDetails;
try {
	activityDetails = client.getActivityDetails(token);
    if(activityDetails.getOutcome().isSuccessful()) {
    	Optional<YourAppUserClass> user = yourUserSearchMethod(activityDetails.getUserId());
    	if(user.isPresent()) {
    		// handle login
    	} else {
    		// handle registration
    	}
    } else {
		// handle unhappy path
	}            
} catch (ProfileException e) {
    LOG.info("Could not get profile", e);
    return "error";
}
```
Where `yourUserSearchMethod` is a piece of logic in your app that is supposed to find a user, given a userId. 
No matter if the user is a new or an existing one, Yoti will always provide her/his profile, so you don't necessarily need to store it.

The `HumanProfile` class provides a set of methods to retrieve different user attributes. Whether the attributes are present or not depends on the settings you have applied to your app on Yoti Dashboard.

## Modules
The SDK is split into a number of modules for easier use and future extensibility. 
### java-sdk-api
Being the only interface you need to explicitly couple your code to this module exposes the core classes:
#### HumanProfile
The set of attributes the user has configured for the transaction.
#### YotiClientBuilder
Builds a YotiClient instance by automatically selecting the available implementations on the class path.
#### YotiClient
Allows your app to retrieve a user profile, given an encrypted token.
#### KeyPairSource and its implementations
A set of classes responsible for working with different sources (e.g. files, classpath resources, URLs) to load the private/public keypair.
### java-sdk-dummy
Dummy implementation without connectivity to any platform services. Can be used for testing purposes.
### java-sdk-impl
Real SDK implementation that takes care of decrypting the token, fetching the user profile from Yoti servers by issuing a signed request and finally decrypting the fetched profile.
 
## Requirements
* Java 1.6 or higher
* SLF4J 

## Misc
* By default, Yoti SDKs fetch profiles from [https://api.yoti.com/api/v1](https://api.yoti.com/api/v1).
If necessary, this can be overridden by setting the *yoti.api.url* system property.
* Yoti Java SDK uses AES-256 encryption. If you are using the Oracle JDK, this key length is not enabled by default. The following stack overflow question explains how to fix this: [http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters](http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters)
* To find out how to set up your Java project in order to use this SDK, you can check the Spring Boot example in this repo.   

# Authors
* [Andras Bulla](https://github.com/lopihe)
* [Radoslaw Busz](https://github.com/gitplaneta)
* [David Goate](https://github.com/davidgoate)
* Attila Kiss
* [Quirino Zagarese](https://github.com/qzagarese) 


