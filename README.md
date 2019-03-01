# NASA-api-tests
images.nasa.gov API Documentation

API Root:
```
https://images-api.nasa.gov
```
API Endpoints:
```
/search 
/asset/{nasa_id} 
/metadata/{nasa_id} 
/captions/{nasa_id}
```
Performing a search
```
GET /search?q={q}
```
Parameters:

| Name          |  Type         | Description       | Nullable value? | Validation Rule |
| ----------- |:-------------:| ------------------| :-------------: | ------------------ |
|q | string |Free text search terms to compare to all indexed metadata. | YES | Optional String |
|center | string |NASA center which published the media. | YES | Optional String |
|description | string |Terms to search for in “Description” fields. | YES | Optional String |
|description_508 | string |Terms to search for in “508 Description” fields. | YES | Optional String |
|keywords | string |Terms to search for in “Keywords” fields. Separate multiple values with commas. | YES | Optional String |
|location | string |Terms to search for in “Location” fields. | YES | Optional String |
|media_type | string |Media types to restrict the search to. Separate multiple values with commas.| YES | Media String |
|nasa_id | string |The media asset’s NASA ID. | YES | Optional UUID |
|photographer | string |The primary photographer’s name. | YES | Optional String |
|secondary_creator | string |A secondary photographer/videographer’s name. | YES | Optional String |
|title | string | Terms to search for in “Title” fields. | YES | Optional String |
|year_start | string | The start year for results. | NO | Year String |
|year_end | string | The end year for results. | NO | Year String |

Validation rules example:

| Name       |  Format    | Allowed values  | Forbidden values | 
| ---------- |:----------:| ----------------| ---------------- | 
|Mandatory String | String | Any String, Any Alphabet | Broken format (String null, String empty, String spaces), Wrong type (Boolean (true / false), Int) |
|Optional String | String | Any String, Any Alphabet, String empty, String spaces | Wrong type (Boolean (true / false), Int) |
|Year String | String | Format: YYYY | Broken format (String null, String empty, String spaces), Wrong type (Boolean (true / false), Any other date, not YYYY) |
|Media String | String | Available types: “image”, “audio” | Wrong type (Boolean (true / false), Any other not available type) |
|Optional UUID | String | Correct format, Only existing values | Broken format (not UUID String, String null, String empty, String spaces), Wrong type (Boolean (true / false), Int), Not Existing (not existing UUID of correct format (random UUID), Change last symbol in existing UUID) |

Functional test scenario example:

| ID | Scenario name | Scenario steps | Positive test expected result | Negative test expected result |
| --- | ------------- | -------------- | ----------------------------- | ----------------------------- |
| 1  | Search with valid param | GET {baseUrl}/search?q=nasa | Return Status Code = 200, Response body (not empty) format: JSON | no |
| 2  | Search with invalid param for search key (white space before key) | GET {baseUrl}/search? q=nasa | no | Return Status Code 400, With response body: "reason": "Invalid search parameter:  q" format JSON |
| 3  | Search with invalid param: empty value | GET {baseUrl}/search?q=  |  no | Return Status Code 400, With response body: "reason": "Expected 'q' text search parameter or other keywords." format JSON |
| 4  | Search with empty params for key and value | GET {baseUrl}/search? =  |  no | Return Status Code 400, With response body: "reason": "Invalid search parameter:  " format JSON |
| 5  | Search without any params | GET {baseUrl}/search  |  no | Return Status Code 400, With response body: "reason": "Expected 'q' text search parameter or other keywords." format JSON |
| 6  | Search without any params | GET {baseUrl}/search?  |  no | Return Status Code 400, With response body: "reason": "Expected 'q' text search parameter or other keywords." format JSON |
| 7  | Search with invalid params | GET {baseUrl}/search&  |  no | Return Status Code 404, With response body" "404 Not Found. The resource could not be found." format HTML |

Command for test run:
```bash
mvn clean test
```

Test report:
```bash
mvn allure:serve
```
