EucaAdminSDK
==========

Eucalyptus administration API SDK for java


Compile
======
This project use marven to build and test, to compile and install the SDK, please install marven in your development
environment, after install marven. input below command:

1) Build

\# mvn install -DskipTests

2) Test

\# mvn test

How to Use
=======

There are 4 classes in this SDK

EucaAdminClient:         contains all functiones of eucalyptus administration API
EucaIAMClient:           contains all extend functions of extend AWS IAM API (for creating account and account delegation)
EucaSessionTokenClient:  contains the function of get session token by loginprofile. 

EucaAdminConsole:        contains all functions for specific administration tasks by using EucaAdminClient,EucaIAMClient and EucaSessionTokenClient.


Examples
=====

Please refer to some examples in this project

EucaAdminSDKExample :  the example of using EucaAdminClient
consoleExample:        the example of using EucaAdminConsole
