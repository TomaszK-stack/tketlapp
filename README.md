An ETL application used for data processing. The main part is located in the core module, while the schedule module is responsible for configuring the schedule. 
The application operates on the principle of retrieving configuration via a REST API. 
Example request:

![image](https://github.com/TomaszK-stack/tketlapp/assets/84187996/732a98fe-1be2-4df5-ac26-b206cba6ee04)


Here I will attempt to describe what ConfigProcessingDTO means because the remaining fields denote input-output operations, which I have described in more detail in Postman in the documentation of individual requests. ConfigProcessingDTO contains a map called configProcessingmap where individual steps to be performed on our data are found. Currently, there are 5 steps available: filter, groupby, nullif, join, unique.
The detailed description of these steps can be found in the file sample_config.json which i added into project.
