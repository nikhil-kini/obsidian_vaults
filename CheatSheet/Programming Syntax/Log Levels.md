
| **Level**| **Description**|
|---|---|
|All|For all the levels (required for user defined levels)|
|TRACE|Information events|
|DEBUG|Information that would be useful for debugging the application|
|INFO|Information that highlights the progress of an application|
|WARN|Potentially harmful situations|
|ERROR|Errors that would permit the application to continue running|
|FATAL|Server errors that may abort the application|
|OFF|To disable all the levels|


### Logger Coverage

|**Level Attributes**|**Trace**|**Debug**|**Info**|**Warn**|**Error**|**Fatal**|
|---|---|---|---|---|---|---|
|rootLogger.level=all|yes|yes|yes|yes|yes|yes|
|rootLogger.level=trace|yes|yes|yes|yes|yes|yes|
|rootLogger.level=debug|no|yes|yes|yes|yes|yes|
|rootLogger.level=info|no|no|yes|yes|yes|yes|
|rootLogger.level=warn|no|no|no|yes|yes|yes|
|rootLogger.level=error|no|no|no|no|yes|yes|
|rootLogger.level=fatal|no|no|no|no|no|yes|
|rootLogger.level=off|no|no|no|no|no|no|
