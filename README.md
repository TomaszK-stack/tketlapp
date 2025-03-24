An ETL application used for data processing. The main part is located in the core module, while the schedule module is responsible for configuring the schedule. 
The application operates on the principle of retrieving configuration via a REST API. 
Example request:

![image](https://github.com/TomaszK-stack/tketlapp/assets/84187996/732a98fe-1be2-4df5-ac26-b206cba6ee04)


Here I will attempt to describe what ConfigProcessingDTO means because the remaining fields denote input-output operations, which I have described in more detail in Postman in the documentation of individual requests. ConfigProcessingDTO contains a map called configProcessingmap where individual steps to be performed on our data are found. Currently, there are 5 steps available: filter, groupby, nullif, join, unique.
The detailed description of these steps can be found in the file sample_config.json which i added into project.
Postman Api collection: https://www.postman.com/descent-module-astronaut-66671886/workspace/tketlapp/collection/25827206-5a1fbe6c-6bdf-4aa7-a27e-c4bd671df167?action=share&creator=25827206&active-environment=25827206-c254968b-99fe-4488-9f09-c7ebd3144203
![image](https://github.com/TomaszK-stack/tketlapp/assets/84187996/bc441795-373f-4c2f-99a3-aa02c5b4101f)

Above diagram shows communication between user and API, user send post request to one of both api and then data is processed. If everything is okay data is saved to output. Every process is log into database.
