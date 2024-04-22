[refer](https://cloud.google.com/docs/authentication/api-keys)

The API key does not identify a [principal](https://cloud.google.com/docs/authentication#principals), nor does it provide any [authorization](https://cloud.google.com/docs/authentication#authorization) information. Therefore, the request does not use Identity and Access Management (IAM) to check whether the caller has permission to perform the requested operation.

An API key has the following components, which you use to manage and use the key:

`String`
The API key string is an encrypted string, for example, `AIzaSyDaGmWKa4JsXZ-HjGw7ISLn_3namBGewQe`. When you use an API key to authenticate, you always use the key's string. API keys do not have an associated JSON file.

`ID`
The API key ID is used by Google Cloud administrative tools to uniquely identify the key. The key ID cannot be used to authenticate. The key ID can be found in the URL of the key's edit page in the Google Cloud console. You can also get the key ID by using the Google Cloud CLI to list the keys in your project.

`Display name`
The display name is an optional, descriptive name for the key, which you can set when you create or update the key.

To manage API keys, you must have the API Keys Admin role (`roles/serviceusage.apiKeysAdmin`) on the project.